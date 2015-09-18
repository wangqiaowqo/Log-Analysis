package com.shadowinlife.app.LogAnalyse.Action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import org.apache.hadoop.fs.FileSystem;

import org.apache.hadoop.fs.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.spark.sql.DataFrame;

import org.apache.spark.sql.hive.HiveContext;

public class ReadParquetToDF {
    private static final Logger logger = LogManager.getLogger();

    public static boolean ReadParquet(HiveContext sqlContext, String BeginTime, String EndTime,
            String[] GameId, String[] AccountType, String[] WorldId, String Table) {

        DataFrame df = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Timestamp bTime = Timestamp.valueOf(BeginTime);
        Timestamp eTime = Timestamp.valueOf(EndTime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(bTime.getTime());
        
        while (calendar.getTimeInMillis() < eTime.getTime()) {
            for (String iGameId : GameId) {
                for (String iAccountType : AccountType) {
                    for (String iWorldId : WorldId) {
                        String ParquetFilePath = "/LOG/" + iGameId.trim() + "/"
                                + iAccountType.trim() + "/" + iWorldId.trim() + "/"
                                + format.format(calendar.getTime()) + "/" + Table.trim()
                                + ".parquet";
                        try {
                            FileSystem fs = FileSystem.get(sqlContext.sparkContext()
                                    .hadoopConfiguration());
                            if (!fs.exists(new Path(ParquetFilePath))) {
                                System.out.println(ParquetFilePath + " not existed");
                                continue;
                            }

                            DataFrame tmp = sqlContext.parquetFile(ParquetFilePath);
                           
                            if (df == null) {
                                df = tmp;
                            } else {
                                df = df.unionAll(tmp);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            logger.catching(e);
                        }
                    }
                }
                
            }
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        
        if (df == null) {
            System.out.println(Table + " File Pointer is NULL");
            return false;
        }
       
        sqlContext.registerDataFrameAsTable(df, Table.trim());

        return true;
    }
}
