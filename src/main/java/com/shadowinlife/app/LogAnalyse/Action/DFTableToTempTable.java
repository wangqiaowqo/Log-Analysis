package com.shadowinlife.app.LogAnalyse.Action;

import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.hive.HiveContext;

public class DFTableToTempTable {
    public static void ExcuteSQL(HiveContext sqlContext, String Temp_Table_Name, String sql) {
        try {
            System.out.println("TEMP:" + Temp_Table_Name +" "+ sql);
            DataFrame Temp_Table_DF = sqlContext.sql(sql);
            for(Row r: Temp_Table_DF.collect()) {
                System.out.println("temp: " + r.mkString(" | "));
            }
            sqlContext.registerDataFrameAsTable(Temp_Table_DF, Temp_Table_Name);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
