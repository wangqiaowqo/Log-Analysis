package com.shadowinlife.app.LogAnalyse;

import org.apache.hadoop.mapred.lib.MultipleTextOutputFormat;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.hive.HiveContext;

import com.shadowinlife.app.LogAnalyse.ProcessTableSQL.AcountProcessTable;

import scala.Tuple2;

/**
 * @author shadowinlife
 * @since 2014-04-03
 * 
 *        Main class of the analyse app
 */

public class MainAPP {

    static class RDDMultipleTextOutputFormat extends MultipleTextOutputFormat<String, String> {
        @Override
        protected String generateFileNameForKeyValue(String key, String value, String name) {

            return key.toString() + "/" + name;
        }
    }

    public static void main(String[] args) {
        // Assemble path of the origin log files
        String targetFile = "hdfs://10-4-28-24:8020/logdata/" + args[0] + "/*/*";

        SparkConf conf = new SparkConf().setAppName("Log Analyzer");
        JavaSparkContext sc = new JavaSparkContext(conf);
        try {
            // Read origin log file
            JavaRDD<String> logLines = sc.textFile(targetFile);

            // TODO The algorithm used to split the file should be changed

            // Split origin file into key-value model
            JavaPairRDD<String, String[]> hadoopFile = logLines
                    .mapToPair(new PairFunction<String, String, String[]>() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public Tuple2<String, String[]> call(String line) throws Exception {
                            FileSplit temp = FileSplit.parseFromLogFile(line);
                            return new Tuple2<String, String[]>(temp.getKeyName(), temp
                                    .getLineValues());
                        }
                    });

            // Filter origin file into different RDD
            JavaRDD<String[]> roleLoginRDD = hadoopFile.filter(
                    new Function<Tuple2<String, String[]>, Boolean>() {

                        private static final long serialVersionUID = 1L;

                        @Override
                        public Boolean call(Tuple2<String, String[]> f) throws Exception {
                            if (f._1.equalsIgnoreCase("RoleLogin")) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    }).values();

            JavaRDD<String[]> roleLogoutRDD = hadoopFile.filter(
                    new Function<Tuple2<String, String[]>, Boolean>() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public Boolean call(Tuple2<String, String[]> f) throws Exception {
                            if (f._1.equalsIgnoreCase("RoleLogout")) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    }).values();
            HiveContext sqlContext = new HiveContext(sc.sc());
            AcountProcessTable.process(sqlContext, roleLoginRDD, roleLogoutRDD, "20"+args[0]);
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        sc.stop();
        sc.close();
    }
}
