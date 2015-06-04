package com.shadowinlife.app.LogAnalyse.Action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.hive.HiveContext;

public class ReadParquetToDF {
    public static void ReadParquet(HiveContext sc, String BeginTime, String EndTime,
            String[] GameId, String[] AccountType, String[] WorldId, String Table) {

        DataFrame df = sc.parquetFile("/LOG/BASE/" + Table.trim() + ".parquet");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd/HH");
        Timestamp bTime = Timestamp.valueOf(BeginTime);
        Timestamp eTime = Timestamp.valueOf(EndTime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(bTime.getTime());

        while (calendar.getTimeInMillis() < eTime.getTime()) {
            for (String iGameId : GameId) {
                for (String iAccountType : AccountType) {
                    for (String iWorldId : WorldId) {
                        String ParquetFilePath = "/LOG/" + iGameId.trim() + "/" + iAccountType.trim() + "/"
                                + iWorldId.trim() + "/" + format.format(calendar.getTime()) + "/" + Table.trim()
                                + ".parquet";
                        try {
                            DataFrame tmp = sc.parquetFile(ParquetFilePath);
                            df = df.unionAll(tmp);
                            tmp.unpersist();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        calendar.add(Calendar.HOUR_OF_DAY, 1);
                    }
                }
            }
        }

        sc.registerDataFrameAsTable(df, Table.trim());
    }
}
