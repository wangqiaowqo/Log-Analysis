package com.shadowinlife.app.Split;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

import com.shadowinlife.app.LogAnalyse.SQLModelFactory.*;
import com.shadowinlife.app.Tools.LogLineSplit;

import scala.Tuple2;

public class Main {

    public static void main(String[] args) {
        String date = null;
        String iGameId = "*";
        String iWorldId = "*";
        String iAccountType = "*";
        String NameNode = null;
        String time = null;
        String tablename = null;
        for (int i = 0; i < args.length; i = i + 2) {
            if (!args[i].contains("--")) {
                System.out.println("Wrong Parameter\n --help for all parameters");
                return;
            }
            if (args[i].contains("help")) {
                System.out.println("--HDFS   Name Node  NOT NULL\n"
                        + "--DATE Index Field Date  NOT NULL\n" + "--GAMEID Index Filed Gameid\n"
                        + "--WORLDID Index Filed WORLDID\n"
                        + "--ACCOUNTTYPE Index Filed ACCOUNTTYPE\n"
                        + "--TABLE split actualy Table\n"
                        + "--TIME Hours of the Field\n");
                return;
            }
            switch (args[i]) {
            case "--HDFS":
                NameNode = args[i + 1];
            case "--DATE":
                date = args[i + 1];
                break;
            case "--GAMEID":
                iGameId = args[i + 1];
                break;
            case "--WORLDID":
                iWorldId = args[i + 1];
                break;
            case "--ACCOUNTTYPE":
                iAccountType = args[i + 1];
                break;
            case "--Table":
                tablename = args[i + 1];
                break;
            case "--TIME":
                time = args[i+1];
                break;
            }
        }

        String targetFile = NameNode + "/LOG/" + iGameId + "/" + iAccountType + "/" + iWorldId
                + "/" + date + "/" + time + "/*";
        String dstPath = "/LOG/" + iGameId + "/" + iAccountType + "/" + iWorldId
                + "/" + date + "/" + time + "/";

        
        SparkConf conf = new SparkConf().setAppName("Log Filter");
        JavaSparkContext sc = new JavaSparkContext(conf);

        try {
            // Read origin log file
            JavaRDD<String> logLines = sc.textFile(targetFile);

            // Split origin file into key-value model
            JavaPairRDD<String, String[]> hadoopFile = logLines
                    .mapToPair(new PairFunction<String, String, String[]>() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public Tuple2<String, String[]> call(String line) throws Exception {
                            LogLineSplit temp = LogLineSplit.parseFromLogFile(line);
                            return new Tuple2<String, String[]>(temp.getKeyName(), temp
                                    .getLineValues());
                        }
                    });

            SQLContext sqlContext = new SQLContext(sc);
            
            
            Class c = Class.forName("com.shadowinlife.app.LogAnalyse.SQLModelFactory.RoleLogin");
            SaveParquet<BaseBean> sq = new SaveParquet<BaseBean>();
            sq.set((BaseBean)c.newInstance());
            sq.LogToParquet(sqlContext, hadoopFile, "RoleLogin", dstPath);
            
            sc.stop();
            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
