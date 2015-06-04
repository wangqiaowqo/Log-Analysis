package com.shadowinlife.app.LogAnalyse.Action;

import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.hive.HiveContext;

public class DFTableToTempTable {
    public static void ExcuteSQL(HiveContext sqlContext, String Temp_Table_Name, String sql) {
        try {
            DataFrame Temp_Table_DF = sqlContext.sql(sql);
            sqlContext.registerDataFrameAsTable(Temp_Table_DF, Temp_Table_Name);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
