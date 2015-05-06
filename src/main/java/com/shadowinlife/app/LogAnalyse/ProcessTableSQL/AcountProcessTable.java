package com.shadowinlife.app.LogAnalyse.ProcessTableSQL;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.hive.HiveContext;

import static org.apache.spark.sql.functions.*;

import com.shadowinlife.app.LogAnalyse.SQLModelFactory.RoleLogin;
import com.shadowinlife.app.LogAnalyse.SQLModelFactory.RoleLogout;

/**
 * 
 * @author shadowinlife
 * @since 2015-4-22
 * 
 *        Execute every hour to produce the fat_tbaccount
+---------------+-----------------------------------+----------+--+
|   col_name    |             data_type             | comment  |
+---------------+-----------------------------------+----------+--+
| dtstatdate    | date                              |          |
| iaccounttype  | int                               |          |
| suin          | string                            |          |
| iregtime      | timestamp                         |          |
| igameid       | int                               |          |
| iworldid      | int                               |          |
| iroleid       | int                               |          |
| iroleregtime  | timestamp                         |          |
| ilastacttime  | timestamp                         |          |
| idayacti      | struct<header:int,tailer:bigint>  |          |
| iweekacti     | struct<header:int,tailer:bigint>  |          |
| imonthacti    | struct<header:int,tailer:bigint>  |          |
| igroup        | int                               |          |
| ilevel        | bigint                            |          |
| iviplevel     | bigint                            |          |
| itimes        | bigint                            |          |
| ionlinetime   | bigint                            |          |
+---------------+-----------------------------------+----------+--+
 */

public class AcountProcessTable {
    // create daily user act table
    private static String tbUser_process_table_sql = 
            "SELECT "
            + "IF(logout_id is null, login_id, logout_id) AS id, "
            + "IF(tbLogout_iOnlineTime is null, 0, tbLogout_iOnlineTime) AS iOnlineTime,"
            + "IF(tbLogout_iRoleLevel is null,tbLogin_iRoleLevel, tbLogout_iRoleLevel) AS iRoleLevel, "
            + "IF(tbLogout_vClientIp is null, tbLogin_vClientIp, tbLogout_vClientIp) AS ip, "
            + "IF(in_times is null, 0, in_times) AS intime," 
            + "IF(out_times is null, 0, out_times) AS outtime,"
            
            + "(CASE WHEN login_regTime IS NULL THEN logout_regTime "
            + "WHEN logout_regTime IS NULL THEN login_regTime "
            + "WHEN login_regTime<logout_regTime THEN login_regTime "
            + "ELSE logout_regTime END) AS regTime,"
            
            + "(CASE WHEN logout_dtEventTime IS NULL THEN login_dtEventTime "
            + "WHEN login_dtEventTime IS NULL THEN logout_dtEventTime "
            + "WHEN logout_dtEventTime>login_dtEventTime THEN logout_dtEventTime "
            + "ELSE login_dtEventTime END) AS acttime "
            
            + "FROM tbLogin FULL OUTER JOIN tbLogout ON tbLogin.login_id=tbLogout.logout_id";
    // USER NOT ACTIVITY
    private static String tbUser_unact_account_table = "INSERT INTO TABLE fat_tbaccount "
            + "SELECT '%s', "
            + "T1.iaccounttype,"
            + "T1.suin,"
            + "T1.iregtime,"
            + "T1.igameid,"
            + "T1.iworldid,"
            + "T1.iroleid,"
            + "T1.iroleregtime,"
            + "T1.ilastacttime,"
            + "shiftleft(T1.idayacti),"
            + "T1.iweekacti," //TODO
            + "T1.imonthacti," //TODO
            + "T1.igroup,"
            + "T1.ilevel,"
            + "T1.iviplevel,"
            + "0,"
            + "0 "
            + "FROM (SELECT * FROM fat_tbaccount WHERE dtStatDate=date_add('%s',-1)) T1 LEFT JOIN "
            + "loginProcessTable T2 ON T1.suin = T2.id "
            + "WHERE T2.id IS NULL";

    // USER ACTIVITY 
    private static String tbUser_act_account_table = "INSERT INTO TABLE fat_tbaccount "
            + "SELECT '%s',"
            + "1,"
            + "T1.id,"
            + "IF(T2.iregtime IS NULL, T1.regTime, T2.iregtime),"
            + "1,"
            + "1,"
            + "1,"
            + "null, "
            + "T1.acttime,"
            + "shiftact(T2.idayacti),"
            + "T2.iweekacti,"
            + "T2.imonthacti,"
            + "0,"
            + "T1.iRoleLevel, "
            + "1,"
            + "IF(T1.outtime is null, T1.intime, T1.outtime),"
            + "T1.iOnlinetime "
            + "FROM loginProcessTable T1 LEFT JOIN "
            + "(SELECT * FROM fat_tbaccount WHERE dtStatDate=date_add('%s',-1)) T2 "
            + "ON T1.id=T2.suin";

    public static boolean process(HiveContext sqlContext, JavaRDD<String[]> loginFile,
            JavaRDD<String[]> logoutFile, String date) {

        try {
            // Create RDD from login FILES
            JavaRDD<RoleLogin> loginLogs = loginFile.map(new Function<String[], RoleLogin>() {

                private static final long serialVersionUID = -8639862064479870422L;

                @Override
                public com.shadowinlife.app.LogAnalyse.SQLModelFactory.RoleLogin call(String[] line) {

                    return RoleLogin.parseFromLogFile(line);

                }

            });

            // Create RDD from logout FILES
            JavaRDD<RoleLogout> logoutLogs = logoutFile.map(new Function<String[], RoleLogout>() {

                private static final long serialVersionUID = 9145640452810492525L;

                @Override
                public com.shadowinlife.app.LogAnalyse.SQLModelFactory.RoleLogout call(String[] line) {

                    return RoleLogout.parseFromLogFile(line);

                }

            });

            // Convert all the values into the spark table
            DataFrame schemaLoginRDD = sqlContext.createDataFrame(loginLogs, RoleLogin.class);
            DataFrame schemaLogoutRDD = sqlContext.createDataFrame(logoutLogs, RoleLogout.class);

            // Dump out the group data of user login and logout
            DataFrame userLogin = schemaLoginRDD.groupBy("iUin").agg(col("iUin").as("login_id"),
                    max(col("iRoleLevel")).as("tbLogin_iRoleLevel"),
                    max(col("vClientIp")).as("tbLogin_vClientIp"),
                    count(col("iUin")).as("in_times"),
                    max(col("dtEventTime")).as("login_dtEventTime"),
                    min(col("dtEventTime")).as("login_regTime"));

            DataFrame userLogout = schemaLogoutRDD.groupBy("iUin").agg(col("iUin").as("logout_id"),
                    sum(col("iOnlineTime")).as("tbLogout_iOnlineTime"),
                    max(col("iRoleLevel")).as("tbLogout_iRoleLevel"),
                    max(col("vClientIp")).as("tbLogout_vClientIp"),
                    count(col("iUin")).as("out_times"),
                    max(col("dtEventTime")).as("logout_dtEventTime"),
                    min(col("dtEventTime")).as("logout_regtime"));

            // Register temple tables to execute analysis SQL
            userLogin.registerTempTable("tbLogin");
            userLogout.registerTempTable("tbLogout");

            // Execute the analysis SQL
            DataFrame temp_RDD = sqlContext.sql(tbUser_process_table_sql);

            // Register the result RDD into hive
            sqlContext.registerDataFrameAsTable(temp_RDD, "loginProcessTable");

            // Initialization hive UDF
            sqlContext.sql("use dbprocess");
            sqlContext.sql("ADD JAR hdfs://10-4-28-24:8020//udf.jar");

            //create a temp table with every hour data
            // sqlContext.sql("INSERT INTO TABLE account_daily_fat(suin,ionlinetime,ilevel,sip,) SELECT * FROM loginProcessTable");

            // Shiftleft all user on the column 'dayact' 'weekact' 'monthact' 
            sqlContext.sql(String.format(tbUser_unact_account_table, date, date));
            sqlContext.sql(String.format(tbUser_act_account_table, date, date));
            sqlContext.dropTempTable("loginProcessTable");
            sqlContext.dropTempTable("tbLogin");
            sqlContext.dropTempTable("tbLogout");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    
    public static void ModifyProcessTableWithoutLogFile(HiveContext sqlContext, String date){
        String hql = "INSERT INTO TABLE fat_tbaccount "
                + "SELECT '%s', "
                + "iaccounttype,"
                + "suin,"
                + "iregtime,"
                + "igameid,"
                + "iworldid,"
                + "iroleid,"
                + "iroleregtime,"
                + "ilastacttime,"
                + "shiftleft(idayacti),"
                + "shiftleft(iweekacti),"
                + "shiftleft(imonthacti),"
                + "igroup,"
                + "ilevel,"
                + "iviplevel,"
                + "0,"
                + "0 "
                + "FROM fat_tbaccount WHERE dtStatDate=date_add('%s',-1)";
        
        // Initialization hive UDF
        sqlContext.sql("use dbprocess");
        sqlContext.sql("ADD JAR hdfs://10-4-28-24:8020//udf.jar");
        sqlContext.sql(String.format(hql, date, date));
    }
}
