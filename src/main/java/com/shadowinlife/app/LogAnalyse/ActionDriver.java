package com.shadowinlife.app.LogAnalyse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

import org.apache.spark.sql.hive.HiveContext;

import com.shadowinlife.app.LogAnalyse.Action.TempTableToMysql;
import com.shadowinlife.app.LogAnalyse.Action.DFTableToTempTable;
import com.shadowinlife.app.LogAnalyse.Action.ReadParquetToDF;

public class ActionDriver {
    // 初始化Log4j
    private static final Logger logger = LogManager.getLogger();

    // 用于解析不长, 把步长转化为时间
    private static Integer[] TranslateStep(String[] inputStep) {
        Integer[] result = new Integer[2];
        // 利用javascript引擎来针对一个表达式进行计算,结果是从标记时间移动的小时数
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");

        try {
            result[0] = ((Double) engine.eval(inputStep[0])).intValue();
            result[1] = ((Double) engine.eval(inputStep[1])).intValue();
            return result;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 用于解析%DATE%+/-Num来标记某些需要写入的时间
    private static String TranslateSQL(String inputSQL, String CurseTime) {

        int index = inputSQL.indexOf("%DATE%");
        while (index != -1) {
            String day = "";
            int end_Index = inputSQL.indexOf("'", index);
            index = index + 6;
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(Timestamp.valueOf(CurseTime).getTime());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            // 判断是否是%DATE+/-N-UM这个格式
            if (index < end_Index) {
                day = inputSQL.substring(index, end_Index);
                c.add(Calendar.DAY_OF_MONTH, Integer.valueOf(day));
                // 由于+是正则表达式的保留字符,需要转义
                day = day.replace("+", "\\+");
            }

            inputSQL = inputSQL.replaceFirst("%DATE%" + day, format.format(c.getTime()));
            index = inputSQL.indexOf("%DATE%");
        }
        return inputSQL;
    }

    public static void Scheduler(HiveContext sc, List<Map<String, List<String[]>>> l, String date,
            String Action, String intWorldId) {
        logger.debug("Action Name:" + Action);
        for (Map<String, List<String[]>> m : l) {

            // 根据ActionName判断是否执行这个Action
            String ActionName = m.get("Name").get(0)[0];
            logger.debug("Configuration Action Name: " + ActionName);

            if (Action != null && !Action.contains(ActionName)) {
                continue;
            }

            // 从XML中解析出配置的Action的执行项
            String[] AccountType = m.get("AccountType").get(0);
            String[] GameId = m.get("GameId").get(0);

            // worldid可以用入参来替换
            String[] WorldId;
            if (intWorldId == null) {
                WorldId = m.get("WorldId").get(0);
            } else {
                WorldId = new String[1];
                WorldId[0] = intWorldId;
            }

            // 判断周期是否正确
            // 判断所指示的日期是否是周日
            String[] Period = m.get("Period").get(0);
            String CurseTime = date + " 00:00:00";
            TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(Timestamp.valueOf(CurseTime).getTime());
            if (Period[0].equalsIgnoreCase("Week") && c.get(Calendar.DAY_OF_WEEK) != 0) {
                continue;
            }

            // 判断所指示的日期是一个月的最后一天
            c.add(Calendar.DAY_OF_MONTH, 1);
            if (Period[0].equalsIgnoreCase("Month") && c.get(Calendar.DAY_OF_MONTH) != 1) {
                continue;
            }

            String[] Step = m.get("Step").get(0);
            String BeginTime, EndTime, EndFilterTime;
            if (date != null) {
                Timestamp bTime = Timestamp.valueOf(CurseTime);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                Calendar calendar = Calendar.getInstance();

                // 时间区间的开始
                calendar.setTimeInMillis(bTime.getTime());

                Integer[] StepHours = TranslateStep(Step);
                calendar.add(Calendar.HOUR_OF_DAY, StepHours[0]);
                System.out.println(calendar.getTime());
                BeginTime = format.format(calendar.getTime());

                // 时间区间的结束
                calendar.setTimeInMillis(bTime.getTime());
                calendar.add(Calendar.HOUR_OF_DAY, StepHours[1]);

                EndFilterTime = format.format(new Date(calendar.getTimeInMillis()));

                calendar.add(Calendar.HOUR_OF_DAY, 2);
                EndTime = format.format(new Date(calendar.getTimeInMillis()));
                System.out.println("gongmeng " + BeginTime + " " + EndFilterTime + " " + EndTime);
            } else {
                return;
            }

            // 读取配置表中要求使用的Table到内存
            String[] Tables = m.get("Table").get(0);
            List<String> talbename = new ArrayList<String>();
            boolean FileExisted;
            for (String Table : Tables) {
                if (Table.equalsIgnoreCase("") || Table == null)
                    continue;
                String strWhere = "SELECT * FROM temp WHERE `dtEventTime`>='" + BeginTime
                        + "' AND `dtEventTime`<'" + EndFilterTime + "'";
                talbename.add(Table);
                
                ReadParquetToDF.ReadParquet(sc, BeginTime, EndTime, GameId, AccountType, WorldId, Table,
                        strWhere);
                
            }

            // 依次执行配置表中要求执行的SQL生成内存中中间表
            // index_0放置的是中间表名称, index_1位置是生成这个中间表使用的SQL语句
            List<String[]> SQLlist = m.get("Sql");
            for (String[] sql : SQLlist) {
                if (sql.length == 0 || sql == null)
                    continue;
                talbename.add(sql[0]);
                DFTableToTempTable.ExcuteSQL(sc, sql[0], TranslateSQL(sql[1], CurseTime));
            }

            // 依次执行配置表中的出库的语句
            // Index_0位置放的是目标出库名, Index_1位置放的是spark结果表的表名 Index_2放的是产生这个结果表的语句
            List<String[]> Finallist = m.get("Final");
            for (String[] sql : Finallist) {
                TempTableToMysql
                        .ExcuteFinalSQL(sc, sql[0], sql[1], TranslateSQL(sql[2], CurseTime));
            }

            // 清理临时表
            for (String t : talbename) {
                if (t != null && !t.equalsIgnoreCase(""))
                    sc.dropTempTable(t);
            }

        }

    }
}
