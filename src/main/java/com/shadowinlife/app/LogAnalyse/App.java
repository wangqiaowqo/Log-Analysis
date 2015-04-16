package com.shadowinlife.app.LogAnalyse;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.mapred.lib.MultipleTextOutputFormat;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

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
        String targetFile = args[0];
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH");
        String path = args[1];
        path += "/" + dateFormat.format(new Date());

        SparkConf conf = new SparkConf().setAppName("Log Analyzer");
        JavaSparkContext sc = new JavaSparkContext(conf);
       

        JavaRDD<String> logLines = sc.textFile(targetFile);
        JavaRDD<FileSplit> fileSplit = logLines.map(FileSplit::parseFromLogFile).cache();

        JavaPairRDD<String, String> hadoopFile = fileSplit.mapToPair(f -> new Tuple2<>(f
                .getKeyName(), f.getLineValues()));

        hadoopFile.saveAsHadoopFile(path, String.class, String.class,
                RDDMultipleTextOutputFormat.class);

        sc.stop();
        sc.close();
    }
}
