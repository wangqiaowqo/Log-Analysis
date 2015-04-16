package com.shadowinlife.app.LogAnalyse.ProcessTableSQL;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;

import com.shadowinlife.app.LogAnalyse.SQLModelFactory.RoleLogin;

public class LoginProcessTable {

    public static boolean process(JavaSparkContext sc, JavaRDD<String> logFile) {
        // Initialize the Spark context.
        try {
            SQLContext sqlContext = new SQLContext(sc);

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
            DataFrame schemaRDD = sqlContext.createDataFrame(accessLogs, RoleLogin.class);
            schemaRDD.registerTempTable("tbLogin");
            sqlContext.cacheTable("tbLogin");  
            sqlContext.sql(CONSTANT.tblogin_process_table_sql).saveAsTable("Rolelogin_Process_Table");
            return true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }

    }
}
