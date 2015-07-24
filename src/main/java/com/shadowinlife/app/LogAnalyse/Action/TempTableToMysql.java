package com.shadowinlife.app.LogAnalyse.Action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.hive.HiveContext;

public class TempTableToMysql {
    private static final Logger logger = LogManager.getLogger();
    public static void ExcuteFinalSQL(HiveContext sc, String url, String table, String sql) {
        try {
            DataFrame dfFinal = sc.sql(sql);     
            dfFinal.insertIntoJDBC(url, table, false);
            dfFinal.unpersist();
        } catch (Exception e) {
            logger.catching(e);
            e.printStackTrace();
        }
    }
}
