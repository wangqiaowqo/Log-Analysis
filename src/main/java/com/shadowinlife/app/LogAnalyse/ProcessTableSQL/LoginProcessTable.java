package com.shadowinlife.app.LogAnalyse.ProcessTableSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
        schemaRDD.registerTempTable("tbLogin");

        sqlContext.sqlContext().cacheTable("tbLogin");
        List<Row> list = sqlContext.sql(CONSTANT.tblogin_process_table_sql).collect();
        
        /**
         * insert every row to the HIVE
         */
        try {
            Class.forName("org.apache.hadoop.hive.jdbc.HiveDriver");
            Connection con = DriverManager.getConnection("jdbc:hive://localhost:10000/dbProcess", "",
                    "");
            Statement stmt = con.createStatement();
            
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        sc.stop();
        sc.close();
    }
}
