package com.shadowinlife.app.Tools;

import java.util.Calendar;
import java.util.List;

import org.apache.hadoop.mapred.lib.MultipleTextOutputFormat;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;


public class SplitAction {
    static class RDDMultipleTextOutputFormat extends MultipleTextOutputFormat<String, String> {
        @Override
        protected String generateFileNameForKeyValue(String key, String value, String name) {

            return key.toString() + "/" + name;
        }
    }

    public static void split( JavaSparkContext sc, String targetFile, String path) {
        
        // 1) create a java calendar instance
        Calendar calendar = Calendar.getInstance();
         
        // 2) get a java.util.Date from the calendar instance.
        //    this date will represent the current instant, or "now".
        java.util.Date now = calendar.getTime();
         
        // 3) a java current time (now) instance
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
        path = path + "/" + currentTimestamp.getTime();
   
        
        try {
            // Read origin log file
            JavaRDD<String> logLines = sc.textFile(targetFile);

            // TODO The algorithm used to split the file should be changed

            // Split origin file into key-value model
            JavaPairRDD<String, String> hadoopFile = logLines
                    .mapToPair(new PairFunction<String, String, String>() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public Tuple2<String, String> call(String line) throws Exception {
                            FileSplit temp = FileSplit.parseLogFileToKV(line);
                            return new Tuple2<String, String>(temp.getDtEvetnTime(), temp
                                    .getFileValue());
                        }
                    });
            System.out.println("gongmeng:"+logLines.count());
            List<String> keys=hadoopFile.keys().distinct().collect();
            for(String key:keys) {
                System.out.println(key);
            }
            hadoopFile.saveAsHadoopFile(path, String.class, String.class,
                    RDDMultipleTextOutputFormat.class);
           

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
