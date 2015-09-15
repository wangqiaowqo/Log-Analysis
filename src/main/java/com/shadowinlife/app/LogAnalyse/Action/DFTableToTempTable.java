package com.shadowinlife.app.LogAnalyse.Action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.hive.HiveContext;

public class DFTableToTempTable {
    private static final Logger logger = LogManager.getLogger();

    public static DataFrame ExcuteSQL(HiveContext sqlContext, String Temp_Table_Name, String sql) {
        try {
            // System.out.println("TEMP:" + Temp_Table_Name +" "+ sql);
            logger.debug(Temp_Table_Name + " " + sql);
            DataFrame Temp_Table_DF = sqlContext.sql(sql);

            if (Temp_Table_Name.trim().equalsIgnoreCase("")) {
                Temp_Table_DF.unpersist();
                return null;
            } else {
                Temp_Table_DF.persist();
                sqlContext.registerDataFrameAsTable(Temp_Table_DF, Temp_Table_Name);
                return Temp_Table_DF;
            }
        } catch (Exception e) {
            logger.catching(e);
            e.printStackTrace();
            return null;
        }
    }
}
