package com.shadowinlife.app.LogAnalyse.ProcessTableSQL;

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.count;
import static org.apache.spark.sql.functions.max;
import static org.apache.spark.sql.functions.min;

import java.sql.Date;
import java.util.Calendar;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.hive.HiveContext;

import com.shadowinlife.app.LogAnalyse.SQLModelFactory.ChongZhi;

/**
+--------------------------+-----------------------------------+-----------------------+--+
|         col_name         |             data_type             |        comment        |
+--------------------------+-----------------------------------+-----------------------+--+
| dtstatdate               | date                              |                       |
| iaccounttype             | int                               |                       |
| suin                     | string                            |                       |
| ifirstchongzhitime       | timestamp                         |                       |
| igameid                  | int                               |                       |
| iworldid                 | int                               |                       |
| iroleid                  | int                               |                       |
| ilastchongzhitime        | timestamp                         |                       |
| idayacti                 | struct<header:int,tailer:bigint>  |                       |
| iweekacti                | struct<header:int,tailer:bigint>  |                       |
| imonthacti               | struct<header:int,tailer:bigint>  |                       |
| igroup                   | int                               |                       |
| ilevel                   | bigint                            |                       |
| iviplevel                | bigint                            |                       |
| itimes                   | bigint                            |                       |
| ichongzhisum             | bigint                            |                       |
| index_iaccounttype       | int                               |                       |
| index_dtstatdate         | bigint                            |                       |
| index_igameid            | int                               |                       |
| index_iworldid           | int                               |                       |
|                          | NULL                              | NULL                  |
| # Partition Information  | NULL                              | NULL                  |
| # col_name               | data_type                         | comment               |
|                          | NULL                              | NULL                  |
| index_iaccounttype       | int                               |                       |
| index_dtstatdate         | bigint                            |                       |
| index_igameid            | int                               |                       |
| index_iworldid           | int                               |                       |
+--------------------------+-----------------------------------+-----------------------+--+

 */

public class ChongzhiProcessTable {
	
	
	
 // create daily user chongzhi table
    private static String tbChongZhi_process_table_sql = 
            "SELECT iUin AS id,"
            + "MIN(dtEventTime) AS FirstTime,"
            + "MAX(dtEventTime) AS ActTime,"
            + "SUM(*) AS iTimes,"
            + "SUM(iPayDelta) AS TotalPay,"
            
            
            + "FROM ChongZhiLog GROUP BY suin";
    // USER NOT ACTIVITY 
    private static String tbChongZhi_unact_account_table = "INSERT OVERWRITE TABLE fat_chongzhi_user "
            + "PARTITION(index_iaccounttype,index_dtstatdate,index_igameid,index_iworldid) "
            + "SELECT '%s', "
            + "T1.iaccounttype,"
            + "T1.suin,"
            + "T1.ifirstTime,"
            + "T1.igameid,"
            + "T1.iworldid,"
            + "T1.iroleid,"
            + "T1.irolefirstTime,"
            + "T1.ilastacttime,"
            + "shiftleft(T1.idayacti),"
            + "T1.iWeekActi," // iWeekacti
            + "T1.iMonthActi," //iMonthacti
            + "T1.igroup,"
            + "T1.ilevel,"
            + "T1.iviplevel,"
            + "0," // times
            + "0," //onlinetime
            + "T1.iaccounttype AS index_iaccounttype,"
            + "DATE2LONG('%s') AS index_dtstatdate,"
            + "T1.igameid AS index_igameid,"
            + "T1.iworldid AS index_iworldid "
            + "FROM (SELECT * FROM fat_chongzhi_user WHERE index_dtStatDate=(DATE2LONG('%s')-1)) T1 LEFT JOIN "
            + "ChongZhiProcessTable T2 ON T1.suin = T2.id "
            + "WHERE T2.id IS NULL";

    // USER ACTIVITY 
    private static String tbChongZhi_act_account_table = "INSERT OVERWRITE TABLE fat_chongzhi_user "
            + "PARTITION(index_iaccounttype,index_dtstatdate,index_igameid,index_iworldid) "
            + "SELECT '%s',"
            + "1," //acounttype
            + "T2.id,"
            + "IF(T1.ifirstTime IS NULL, T2.firstTime, T1.ifirstTime),"
            + "1," //gameid
            + "1," //worldid
            + "1," //roleid
            + "null, " //rolefirstTime
            + "T2.acttime,"
            + "shiftact(T1.idayacti),"
            + "T1.iWeekActi," //iWeekacti
            + "T1.iMonthActi," //iMonthacti
            + "0," //group
            + "T2.iRoleLevel, "
            + "1," //iviplevel
            + "IF(T2.outtime is null, T2.intime, T2.outtime),"
            + "T2.iOnlinetime,"
            + "1 AS index_iaccounttype,"
            + "DATE2LONG('%s') AS index_dtstatdate,"
            + "1 AS index_igameid,"
            + "1 AS index_iworldid "
            + "FROM ChongZhiProcessTable T2 LEFT JOIN "
            + "(SELECT * FROM fat_chongzhi_user WHERE index_dtStatDate=(DATE2LONG('%s')-1)) T1 "
            + "ON T2.id=T1.suin";
    //Shift iweek in sunday, shift imonth in last day of month
    private static String shift_fatTable = "INSERT OVERWRITE TABLE fat_chongzhi_user "
            + "PARTITION(index_iaccounttype,index_dtstatdate,index_igameid,index_iworldid) "
            + "SELECT T1.dtstatdate, "
            + "T1.iaccounttype,"
            + "T1.suin,"
            + "T1.ifirstchongzhitime,"
            + "T1.igameid,"
            + "T1.iworldid,"
            + "T1.iroleid,"
            + "T1.ilastchongzhitime,"
            + "T1.idayacti,"
            + "%s," // iWeekacti
            + "%s," //iMonthacti
            + "T1.igroup,"
            + "T1.ilevel,"
            + "T1.iviplevel,"
            + "T1.iTimes," // times
            + "T1.ichongzhisum," 
            + "T1.index_iaccounttype,"
            + "T1.index_dtstatdate,"
            + "T1.index_igameid,"
            + "T1.index_iworldid FROM fat_chongzhi_user T1 WHERE index_dtstatdate=date2long('%s')";

    public static boolean process(HiveContext sqlContext, JavaRDD<String[]> chongZhiFile,
            String date) {
        
        try {
            
            // Create RDD from login FILES
            JavaRDD<ChongZhi> chongZhiLogs = chongZhiFile.map(new Function<String[], ChongZhi>() {

				private static final long serialVersionUID = 1L;

				@Override
                public ChongZhi call(String[] line) {
                    
                    return ChongZhi.parseFromLogFile(line);

                }

            });
            
            // Convert all the values into the spark table
            DataFrame schemaLoginRDD = sqlContext.createDataFrame(chongZhiLogs, ChongZhi.class);
            
            schemaLoginRDD.registerTempTable("ChongZhiLog");

            // Execute the daily analysis SQL
            DataFrame temp_RDD = sqlContext.sql(tbChongZhi_process_table_sql);

            // Register the result RDD into hive
            sqlContext.registerDataFrameAsTable(temp_RDD, "ChongZhiProcessTable");

            // Initialization hive UDF
            sqlContext.sql("use dbprocess");
            sqlContext.sql("ADD JAR hdfs://10-4-28-24:8020//udf.jar");
            
            /*
             * IF the day is Sunday,check last 7days of user iactivity and shift iweekacti 
             * IF the day is last day of month, check daycount of last month
             */
            Calendar c = Calendar.getInstance();
            c.setTime(Date.valueOf(date));
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
            c.add(Calendar.DATE, 1);
            String iWeekActi = "T1.iweekacti";
            String iMonthActi = "T1.imonthacti";

            sqlContext.sql(String.format(tbChongZhi_unact_account_table, date, date, date));
            sqlContext.sql(String.format(tbChongZhi_act_account_table, date, date, date));
            
            if(dayOfWeek == 1) {
                iWeekActi = "IF(useractivity(T1.iDayActi,7)=1,shiftact(T1.iweekacti),shiftleft(T1.iweekacti))";
            }
            
            if(c.get(Calendar.DAY_OF_MONTH) == 1){
                iMonthActi = String.format(
                        "IF(useractivity(T1.iDayActi,%s)=1,shiftact(T1.imonthacti),shiftleft(T1.imonthacti))",
                        dayOfMonth);
            }
            sqlContext.sql(String.format(shift_fatTable, iWeekActi, iMonthActi, date));
            
            sqlContext.dropTempTable("ChongZhiProcessTable");
            sqlContext.dropTempTable("tbLogin");
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    
    public static void ModifyProcessTableWithoutLogFile(HiveContext sqlContext, String date){
        String hql = "INSERT OVERWRITE TABLE fat_chongzhi_user "
                + "PARTITION(index_iaccounttype,index_dtstatdate,index_igameid,index_iworldid) "
                + "SELECT '%s', "
                + "iaccounttype,"
                + "suin,"
                + "ifirstchongzhitime,"
                + "igameid,"
                + "iworldid,"
                + "iroleid,"
                + "ilastchongzhitime,"	
                + "shiftleft(idayacti),"
                + "%s,"
                + "%s,"
                + "igroup,"
                + "ilevel,"
                + "iviplevel,"
                + "0," //times
                + "0," //chongzhisum
                + "iaccounttype AS index_iaccounttype,"
                + "DATE2LONG('%s') AS index_dtstatdate,"
                + "igameid AS index_igameid,"
                + "iworldid AS index_iworldid "
                + "FROM fat_chongzhi_user WHERE index_dtStatDate=(DATE2LONG('%s')-1)";
        
        Calendar c = Calendar.getInstance();
        c.setTime(Date.valueOf(date));
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        c.add(Calendar.DATE, 1);
        String iWeekActi = "iweekacti";
        String iMonthActi = "imonthacti";
        
        if(dayOfWeek == 1) {
            iWeekActi = "IF(useractivity(iDayActi,6)=1,shiftact(iweekacti),shiftleft(iweekacti))";
        }
        
        if(c.get(Calendar.DAY_OF_MONTH) == 1){
            iMonthActi = String.format(
                    "IF(useractivity(iDayActi,%s)=1,shiftact(imonthacti),shiftleft(imonthacti))",
                    dayOfMonth-1);
        }
        // Initialization hive UDF
        sqlContext.sql("use dbprocess");
        sqlContext.sql("ADD JAR hdfs://10-4-28-24:8020//udf.jar");
        
        sqlContext.sql(String.format(hql, date, iWeekActi, iMonthActi, date, date));
    }
}
