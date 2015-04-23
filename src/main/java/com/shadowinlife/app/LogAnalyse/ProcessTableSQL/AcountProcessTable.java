package com.shadowinlife.app.LogAnalyse.ProcessTableSQL;

import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
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
                    count(col("iUin")).as("in_times"));
            DataFrame userLogout = schemaLogoutRDD.groupBy("iUin").agg(col("iUin").as("logout_id"),
                    sum(col("iOnlineTime")).as("tbLogout_iOnlineTime"),
                    max(col("iRoleLevel")).as("tbLogout_iRoleLevel"),
                    max(col("vClientIp")).as("tbLogout_vClientIp"),
                    count(col("iUin")).as("out_times"));

            // Register temple tables to execute analysis SQL
            userLogin.registerTempTable("tbLogin");
            userLogout.registerTempTable("tbLogout");

            // Execute the analysis SQL
            DataFrame login_Temp_RDD = sqlContext.sql(CONSTANT.tbUser_process_table_sql);
            Row[] a = login_Temp_RDD.collect();
            for (Row r : a) {
                System.out.println(r.get(0).toString() + " " + r.get(1).toString() + " "
                        + r.get(2).toString() + " " + r.get(3).toString() + " "
                        + r.get(4).toString() + " " + r.get(5).toString());
            }
            // Register the result RDD into hive
            // sqlContext.registerDataFrameAsTable(login_Temp_RDD,
            // "loginProcessTable");

            // Persist data into hive table
            // sqlContext.sql("INSERT INTO TABLE dbprocess.test2 SELECT * FROM loginProcessTable");

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
