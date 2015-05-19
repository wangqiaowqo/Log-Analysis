package com.shadowinlife.app.LogAnalyse;

import java.util.Calendar;
import java.util.List;

import org.apache.hadoop.mapred.lib.MultipleTextOutputFormat;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;

import com.shadowinlife.app.Tools.FileSplit;

import scala.Tuple2;

public class SplitApp {

    static class RDDMultipleTextOutputFormat extends MultipleTextOutputFormat<String, String> {
        @Override
        protected String generateFileNameForKeyValue(String key, String value, String name) {

            return key.toString() + "/" + name;
        }
    }

    public static void main(String[] args) {
        String targetFile = args[0];
        String path = args[1];
        
        // 1) create a java calendar instance
        Calendar calendar = Calendar.getInstance();
         
        // 2) get a java.util.Date from the calendar instance.
        //    this date will represent the current instant, or "now".
        java.util.Date now = calendar.getTime();
         
        // 3) a java current time (now) instance
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
        path = path + "/" + currentTimestamp.getTime();
        SparkConf conf = new SparkConf().setAppName("Log Filter");
        JavaSparkContext sc = new JavaSparkContext(conf);
        if (args.length < 2) {
            System.out.println("arg1 is the path to src file, arg2 is the path to dst file/split");
        }

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
            sc.stop();
            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
