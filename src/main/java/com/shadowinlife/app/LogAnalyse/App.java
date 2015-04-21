package com.shadowinlife.app.LogAnalyse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.hadoop.mapred.lib.MultipleTextOutputFormat;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;

import com.shadowinlife.app.LogAnalyse.ProcessTableSQL.LoginProcessTable;

import scala.Tuple2;

/**
 * @author shadowinlife
 * @since 2014-04-03
 */

public class App {

    static class RDDMultipleTextOutputFormat extends MultipleTextOutputFormat<String, String> {
        @Override
        protected String generateFileNameForKeyValue(String key, String value, String name) {

            return key.toString() + "/" + name;
        }
    }

    public static void main(String[] args) {
        
        SimpleDateFormat formatter = new SimpleDateFormat("yy-MM-dd/HH");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+7:00"));
        Date now = new Date();
        String targetFile = "hdfs://10-4-18-185:8020/logdata/" + formatter.format(now);
      
        SparkConf conf = new SparkConf().setAppName("Log Analyzer");
        JavaSparkContext sc = new JavaSparkContext(conf);
        try {
            System.out.println(targetFile);
            // READ LOG FILE
            JavaRDD<String> logLines = sc.textFile(targetFile);
            JavaRDD<FileSplit> fileSplit = logLines.map(new Function<String, FileSplit>() {

                private static final long serialVersionUID = 1L;

                @Override
                public FileSplit call(String line) throws Exception {
                    return FileSplit.parseFromLogFile(line);
                }
            }).cache();

            JavaPairRDD<String, String> hadoopFile = fileSplit
                    .mapToPair(new PairFunction<FileSplit, String, String>() {

                        private static final long serialVersionUID = 1L;

                        @Override
                        public Tuple2<String, String> call(FileSplit fileSplit) throws Exception {
                            return new Tuple2<String, String>(fileSplit.getKeyName(), fileSplit
                                    .getLineValues());
                        }
                    });
            hadoopFile.groupByKey();
            
            JavaRDD<String> roleLoginRDD = hadoopFile.filter(
                    new Function<Tuple2<String, String>, Boolean>() {

                        private static final long serialVersionUID = 1L;

                        @Override
                        public Boolean call(Tuple2<String, String> f) throws Exception {
                            // split log file and get the values header by --
                            if (f._1.equalsIgnoreCase("RoleLogin")) {
                                return true;
                            } else {
                                return false;
                            }
                        }

                    }).values();

            LoginProcessTable.process(sc, roleLoginRDD);
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        sc.stop();
        sc.close();
    }
}
