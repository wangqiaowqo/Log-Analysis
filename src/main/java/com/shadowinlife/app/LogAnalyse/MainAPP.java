package com.shadowinlife.app.LogAnalyse;

import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.hive.HiveContext;

import com.shadowinlife.app.LogAnalyse.ProcessTableSQL.AcountProcessTable;
import com.shadowinlife.app.LogAnalyse.ProcessTableSQL.ChongzhiProcessTable;
import com.shadowinlife.app.OssTableSQL.DataMigrateToMysql;
import com.shadowinlife.app.OssTableSQL.UserAccountAnalysis;

import scala.Tuple2;

/**
 * @author shadowinlife
 * @since 2014-04-03
 * 
 *        Main class of the analyse app
 */

public class MainAPP {

    public static void main(String[] args) {
        if (args.length < 5) {
            System.out
                    .println("args[0]---FileTarget \n args[1]----mode \n args[2]---TableName \n  args[3]----date\n args[4]---1 for split file");
        }

        // Assemble path of the origin log files
        String nameNode = args[0]; /* hdfs://namenode:8020/ */
        String mode = args[1];
        String tableName = args[2];
        String date = args[3];
        String oozie = args[4];

        SparkConf conf = new SparkConf().setAppName("Log Analyzer");
        JavaSparkContext sc = new JavaSparkContext(conf);
        HiveContext sqlContext = new HiveContext(sc.sc());

        if (oozie != null && oozie.equalsIgnoreCase("1")) {
            SplitAction.split(sc, "hdfs://10-4-28-24:8020/logdata/" + date + "/*/*", "/logsplit");
        }
        date = "20" + date;
        try {

            RegexPathFilter regexPathFilter = new RegexPathFilter("(.*)" + tableName + date
                    + "(.*)");
            Path path = new Path(nameNode + "/logsplit/");
            if (!regexPathFilter.accept(path)) {
                AcountProcessTable.ModifyProcessTableWithoutLogFile(sqlContext, date);
            } else {
                // Read origin log file
                String targetFile = nameNode + "/logsplit/*/" + tableName + date + "/*";
                JavaRDD<String> logLines = sc.textFile(targetFile);

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

                if (tableName.equalsIgnoreCase("RoleLogin")) {
                    // Filter origin file into different RDD
                    JavaRDD<String[]> roleLoginRDD = hadoopFile.filter(
                            new Function<Tuple2<String, String[]>, Boolean>() {

                                private static final long serialVersionUID = 1L;

                                @Override
                                public Boolean call(Tuple2<String, String[]> f) throws Exception {
                                    if (!f._1.contains("RoleLogout")) {
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
                                    if (f._1.contains("RoleLogout")) {
                                        return true;
                                    } else {
                                        return false;
                                    }
                                }
                            }).values();

                    AcountProcessTable.process(sqlContext, roleLoginRDD, roleLogoutRDD, date);
                   
                } else {
                    JavaRDD<String[]> chongZhiRDD = hadoopFile.values();
                    ChongzhiProcessTable.process(sqlContext, chongZhiRDD, date);
                }
            }
            UserAccountAnalysis.create_tbRegisterUser(sqlContext, mode, date);
            DataMigrateToMysql.iHive_TO_Mysql(sqlContext, date, mode);
        } catch (NullPointerException e) {
            AcountProcessTable.process(sqlContext, null, null, date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        sc.stop();
        sc.close();
    }
}
