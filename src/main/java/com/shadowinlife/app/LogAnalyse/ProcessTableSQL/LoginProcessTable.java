package com.shadowinlife.app.LogAnalyse.ProcessTableSQL;

import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.api.java.JavaSQLContext;
import org.apache.spark.sql.api.java.JavaSchemaRDD;
import org.apache.spark.sql.api.java.Row;

import com.shadowinlife.app.LogAnalyse.SQLModelFactory.RoleLogin;

public class LoginProcessTable {
    public static void main(String[] args) {
        // Initialize the Spark context.
        SparkConf conf = new SparkConf().setAppName("Log Analyzer SQL");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaSQLContext sqlContext = new JavaSQLContext(sc);

        if (args.length == 0) {
            System.out.println("Must specify an access logs file.");
            System.exit(-1);
        }
        String logFile = args[0];
        JavaRDD<RoleLogin> accessLogs = sc.textFile(logFile).map(RoleLogin::parseFromLogFile);
        JavaSchemaRDD schemaRDD = sqlContext.applySchema(accessLogs, RoleLogin.class);
        schemaRDD.registerTempTable("logs");
        sqlContext.sqlContext().cacheTable("logs");
        List<Row> list = sqlContext.sql("SELECT iUin FROM logs GROUP BY iUin").collect();
        for (Row r : list) {
            System.out.println(r.getString(0));
        }

        sc.stop();
        sc.close();
    }
}
