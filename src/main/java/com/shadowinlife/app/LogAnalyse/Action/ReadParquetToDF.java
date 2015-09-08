package com.shadowinlife.app.LogAnalyse.Action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.hive.HiveContext;

public class ReadParquetToDF {
    private static final Logger logger = LogManager.getLogger();

    public static boolean ReadParquet(HiveContext sqlContext, String BeginTime, String EndTime,
            String[] GameId, String[] AccountType, String[] WorldId, String Table, String WhereSQL) {

        DataFrame df = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd/HH");
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
                            DataFrame tmp = sqlContext.parquetFile(ParquetFilePath);
                            
                            if (df == null) {
                                df = tmp;
                            } else {
                                df = df.unionAll(tmp);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            calendar.add(Calendar.HOUR_OF_DAY, 1);
        }
        if(df == null){
            //TODO 当某张表不存在一条数据的时候应该构造一个全空的表
            return false;
        }
        df.registerTempTable("temp");
        DataFrame dfFilted = sqlContext.sql(WhereSQL); 
       
        sqlContext.registerDataFrameAsTable(dfFilted, Table.trim());
        sqlContext.dropTempTable("temp");
        return true;
    }
}
