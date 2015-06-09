package com.shadowinlife.app.LogAnalyse;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.hive.HiveContext;

import com.shadowinlife.app.LogAnalyse.Action.TempTableToMysql;
import com.shadowinlife.app.LogAnalyse.Action.DFTableToTempTable;
import com.shadowinlife.app.LogAnalyse.Action.ReadParquetToDF;

public class ActionDriver {
    public static void Scheduler(HiveContext sc, List<Map<String, List<String[]>>> l, String date, String Action) {
        for (Map<String, List<String[]>> m : l) {
            String[] AccountType = m.get("AccountType").get(0);
            String[] GameId = m.get("GameId").get(0);
            String[] WorldId = m.get("WorldId").get(0);
            String BeginTime = m.get("Date").get(0)[0] + " 00:00:00";
            String EndTime = m.get("Date").get(0)[1] + " 02:00:00";
            String ActionName = m.get("Name").get(0)[0];
            if(Action!=null && !Action.contains(ActionName)) {
                continue;
            }
            List<String> talbename = new ArrayList<String>();

            if (date != null) {
                BeginTime = date + " 00:00:00";
                Timestamp bTime = Timestamp.valueOf(BeginTime);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(bTime.getTime());
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                EndTime = format.format(calendar.getTime()) + " 02:00:00";
            }
            String[] Tables = m.get("Table").get(0);

            for (String Table : Tables) {
                System.out.println("Tring to load:" + Table);
                String curTime = m.get("Date").get(0)[1] + " 00:00:00";
                String strWhere = "SELECT * FROM temp WHERE `dtEventTime`>='" + BeginTime
                        + "' AND `dtEventTime`<'"+curTime+"'";
                talbename.add(Table);
                ReadParquetToDF.ReadParquet(sc, BeginTime, EndTime, GameId, AccountType, WorldId,
                        Table,strWhere);
            }

            List<String[]> SQLlist = m.get("Sql");
            for (String[] sql : SQLlist) {
                talbename.add(sql[0]);
                DFTableToTempTable.ExcuteSQL(sc, sql[0], sql[1]);
            }

            List<String[]> Finallist = m.get("Final");
            for (String[] sql : Finallist) {
                TempTableToMysql.ExcuteFinalSQL(sc, sql[0], sql[1], sql[2]);
            }
            for (String t : talbename) {
                sc.dropTempTable(t);
            }

        }

    }
}
