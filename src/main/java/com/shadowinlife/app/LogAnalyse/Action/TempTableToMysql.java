package com.shadowinlife.app.LogAnalyse.Action;

import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.hive.HiveContext;

public class TempTableToMysql {
    public static void ExcuteFinalSQL(HiveContext sc, String url, String table, String sql) {
        try {
            DataFrame dfFinal = sc.sql(sql);
            System.out.println(url + " " + table + " " + sql);
            for (Row r : dfFinal.collect()) {
                System.out.println("final:" + r.mkString(" | "));
            }
            // dfFinal.insertIntoJDBC(url, table, false);
            dfFinal.unpersist();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
