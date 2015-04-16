package com.shadowinlife.app.LogAnalyse;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.hadoop.mapred.lib.MultipleTextOutputFormat;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

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
        String targetFile = args[0];
        String dstFile = args[1];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd/HH");
        LocalDateTime dateTime = LocalDateTime.now(ZoneId.of("UTC+7"));
        targetFile = args[0] + "/" + dateTime.format(formatter);
        dstFile = args[1] + "/" + dateTime.format(formatter);
        SparkConf conf = new SparkConf().setAppName("Log Analyzer");
        JavaSparkContext sc = new JavaSparkContext(conf);
        try {
            JavaRDD<String> logLines = sc.textFile(targetFile);
            JavaRDD<FileSplit> fileSplit = logLines.map(FileSplit::parseFromLogFile).cache();

            JavaPairRDD<String, String> hadoopFile = fileSplit.mapToPair(f -> new Tuple2<>(f
                    .getKeyName(), f.getLineValues()));

            hadoopFile.saveAsHadoopFile(dstFile, String.class, String.class,
                    RDDMultipleTextOutputFormat.class);
            JavaRDD<String> roleLoginRDD = hadoopFile.filter(
                    f -> f._1.equalsIgnoreCase("RoleLogin")).values();
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
