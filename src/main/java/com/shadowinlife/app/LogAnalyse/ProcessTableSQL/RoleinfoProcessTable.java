package com.shadowinlife.app.LogAnalyse.ProcessTableSQL;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.api.java.JavaSQLContext;
import org.apache.spark.sql.api.java.JavaSchemaRDD;
import org.apache.spark.sql.api.java.Row;

import com.shadowinlife.app.LogAnalyse.SQLModelFactory.RoleStatus;

public class RoleinfoProcessTable {

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
        JavaRDD<RoleStatus> accessLogs = sc.textFile(logFile).map(RoleStatus::parseFromLogFile);
        JavaSchemaRDD schemaRDD = sqlContext.applySchema(accessLogs, RoleStatus.class);
        schemaRDD.registerTempTable("tbRoleStatus");

        sqlContext.sqlContext().cacheTable("tbRoleStatus");
        String path = "hdfs://mycluster/process_table";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH");
        path += "/" + "tbRoleStatus" + dateFormat.format(new Date());
        // sqlContext.sql(CONSTANT.tbRolestatus_process_table_sql).saveAsParquetFile(path);
        List<Row> list = sqlContext.sql(CONSTANT.tbRolestatus_process_table_sql).collect();
        for (Row r : list) {
            for(int i=0; i<r.length();i++) {
                System.out.print(r.get(i).toString()+" ");
            }
            System.out.println();
        }
        sc.stop();
        sc.close();
    }

}
