package com.shadowinlife.app.LogAnalyse.ProcessTableSQL;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.hive.HiveContext;

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
            SQLContext sqlContext = new SQLContext(sc);
            HiveContext hiveContext = new HiveContext(sc.sc());

            // Create RDD from login FILES
            JavaRDD<RoleLogin> loginLogs = loginFile.map(new Function<String[], RoleLogin>() {

                private static final long serialVersionUID = -8639862064479870422L;

                @Override
                public com.shadowinlife.app.LogAnalyse.SQLModelFactory.RoleLogin call(String[] line) {
                    try {
                        return RoleLogin.parseFromLogFile(line);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }

            });

            // Create RDD from logout FILES
            JavaRDD<RoleLogout> logoutLogs = logoutFile.map(new Function<String[], RoleLogout>() {

                private static final long serialVersionUID = 9145640452810492525L;

                @Override
                public com.shadowinlife.app.LogAnalyse.SQLModelFactory.RoleLogout call(String[] line) {
                    try {
                        return RoleLogout.parseFromLogFile(line);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }

            });

            // Convert all the values into the spark table
            DataFrame schemaLoginRDD = sqlContext.createDataFrame(loginLogs, RoleLogin.class);
            DataFrame schemaLogoutRDD = sqlContext.createDataFrame(logoutLogs, RoleLogout.class);

            // Register temple tables to execute analysis SQL
            schemaLoginRDD.registerTempTable("tbLogin");
            schemaLogoutRDD.registerTempTable("tbLogout");

            // Execute the analysis SQL
            DataFrame login_Temp_RDD = sqlContext.sql(CONSTANT.tblogin_process_table_sql);

            // Register the result RDD into hive
            hiveContext.registerDataFrameAsTable(login_Temp_RDD, "loginProcessTable");

            // Persist data into hive table
            hiveContext.sql("INSERT INTO TABLE dbprocess.test2 SELECT * FROM loginProcessTable");

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
