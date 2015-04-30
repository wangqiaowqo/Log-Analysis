package com.shadowinlife.app.LogAnalyse.ProcessTableSQL;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
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
 *        Execute every hour to produce the account process table
 */
public class AcountProcessTable {

    public static boolean process(JavaSparkContext sc, JavaRDD<String[]> loginFile,
            JavaRDD<String[]> logoutFile) {

        SimpleDateFormat formatter = new SimpleDateFormat("yy-MM-dd");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+7:00"));
        String date = formatter.format(new Date());
        try {
            // Initialization SparkSQL
            HiveContext sqlContext = new HiveContext(sc.sc());

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
                    max(col("dtEventTime")).as("login_dtEventTime"));

            DataFrame userLogout = schemaLogoutRDD.groupBy("iUin").agg(col("iUin").as("logout_id"),
                    sum(col("iOnlineTime")).as("tbLogout_iOnlineTime"),
                    max(col("iRoleLevel")).as("tbLogout_iRoleLevel"),
                    max(col("vClientIp")).as("tbLogout_vClientIp"),
                    count(col("iUin")).as("out_times"),
                    max(col("dtEventTime")).as("logout_dtEventTime"));

            // Register temple tables to execute analysis SQL
            userLogin.registerTempTable("tbLogin");
            userLogout.registerTempTable("tbLogout");

            // Execute the analysis SQL
            DataFrame temp_RDD = sqlContext.sql(CONSTANT.tbUser_process_table_sql);

            // Register the result RDD into hive
            sqlContext.registerDataFrameAsTable(temp_RDD, "loginProcessTable");

            // Initialization hive UDF
            sqlContext.sql("use dbprocess");
            sqlContext.sql("ADD JAR hdfs://10-4-28-24:8020//udf.jar");

            // sqlContext.sql("INSERT INTO TABLE account_daily_fat(suin,ionlinetime,ilevel,sip,) SELECT * FROM loginProcessTable");

            // Shiftleft all user on the column 'dayact' 'weekact' 'monthact'
            
            sqlContext.sql(String.format(CONSTANT.tbUser_unact_account_table, date));
            sqlContext.sql(String.format(CONSTANT.tbUser_act_account_table, date));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
