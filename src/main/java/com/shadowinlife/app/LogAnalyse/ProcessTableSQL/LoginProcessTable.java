package com.shadowinlife.app.LogAnalyse.ProcessTableSQL;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.hive.HiveContext;

import com.shadowinlife.app.LogAnalyse.SQLModelFactory.RoleLogin;

public class LoginProcessTable {

    public static boolean process(JavaSparkContext sc, JavaRDD<String> logFile) {
       
        try {
            SQLContext sqlContext = new SQLContext(sc);
            HiveContext hiveContext = new HiveContext(sc.sc());

            JavaRDD<RoleLogin> accessLogs = logFile.map(new Function<String, RoleLogin>() {
                /**
                 * Return RoleLogin object from every line
                 */
                private static final long serialVersionUID = -8639862064479870422L;

                @Override
                public com.shadowinlife.app.LogAnalyse.SQLModelFactory.RoleLogin call(String v1) {
                    try {
                        return RoleLogin.parseFromLogFile(v1);
                    } catch (Exception e) {

                        e.printStackTrace();
                        return null;
                    }
                }

            });
            // convert all the values into the spark table
            DataFrame schemaRDD = sqlContext.createDataFrame(accessLogs, RoleLogin.class);
            // Register a temple table to execute analysis sql
            schemaRDD.registerTempTable("tbLogin");

            // execute the analysis sql
            DataFrame login_Temp_RDD = sqlContext.sql(CONSTANT.tblogin_process_table_sql);

            // Register the result rdd into hive
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
