package com.shadowinlife.app.LogAnalyse;

import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.hive.HiveContext;

import scala.Tuple2;

import com.shadowinlife.app.OssTableSQL.UserAccountAnalysis;
import com.shadowinlife.app.Scheduler.CreateProcessTable;
import com.shadowinlife.app.Tools.LogLineSplit;
import com.shadowinlife.app.Tools.RegexPathFilter;
import com.shadowinlife.app.Tools.SplitAction;

/**
 * @author shadowinlife
 * @since 2014-04-03
 * 
 *        Main class of the analyse app
 */

public class MainAPP {

    public static void main(String[] args) {
        
        String HDFSNameNode = null; /* hdfs://namenode:8020/ */
        String mode = null;
        String date = null;
        String Flag = null;
        String iWorldId = null, iGameId = null, iAccountType = null;
        Path path = null;
        String targetFile = null;
        String tableName = null;
        
        for (int i = 0; i < args.length; i = i + 2) {
            if (!args[i].contains("--")) {
                System.out.println("Wrong Parameter\n --help for all parameters");
                return;
            }
            if (args[i].contains("help")) {
                System.out.println("--HDFS  url for hdfs\n"
                        + "--MODE  MODE_NAME  IF MODE=NULL Dont't Create Oss Table\n" 
                        + "--MYSQLURL for MySql\n"
                        + "--DATE Index Field Date\n"   
                        + "--GAMEID Index Filed Gameid\n"
                        + "--WORLDID Index Filed WORLDID\n"
                        + "--ACCOUNTTYPE Index Filed ACCOUNTTYPE\n" 
                        + "--FLAG  FLAG=ALL Will Create All Oss Table Directly\n"
                        + " FLAG=OSSTABLE will Create Oss Table Based on the MODE_VALUE\n"
                        + "--TABLE Consist the table name");
                return;
            }
            
            switch (args[i]) {
            case "--HDFS":
                HDFSNameNode = args[i+1];
                break;
            case "--MODE":
                mode = args[i + 1];
                break;
            case "--TAG":
                Flag = args[i + 1];
                break;
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
            case "--TABLE":
                tableName = args[i + 1];
                break;
            }
        }

        if (iWorldId.equalsIgnoreCase("2")) {
            path = new Path(HDFSNameNode + "/logsplit37/");
            targetFile = HDFSNameNode + "/logsplit37/*/" + tableName + date + "/*";
        } else {
            path = new Path(HDFSNameNode + "/logsplit/");
            targetFile = HDFSNameNode + "/logsplit/*/" + tableName + date + "/*";
        }

        SparkConf conf = new SparkConf().setAppName("Log Analyzer");
        JavaSparkContext sc = new JavaSparkContext(conf);
        HiveContext sqlContext = new HiveContext(sc.sc());

        try {

            if (Flag.equalsIgnoreCase("ALL")) {
                UserAccountAnalysis.create_tbRegisterUser(sqlContext, "pay", date);
                UserAccountAnalysis.create_tbRegisterUser(sqlContext, "deposit", date);
                UserAccountAnalysis.create_tbRegisterUser(sqlContext, "login", date);
            }
            if (Flag.equalsIgnoreCase("OSSTABLE")) {
                UserAccountAnalysis.create_tbRegisterUser(sqlContext, mode, date);

            } else {

                RegexPathFilter regexPathFilter = new RegexPathFilter("(.*)" + tableName + date
                        + "(.*)");

                if (!regexPathFilter.accept(path)) {
                    CreateProcessTable.FatTableWithoutFile(sqlContext, tableName, date, iWorldId);
                } else {
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

                    // Create Fat Process Table
                    CreateProcessTable.FatTableConstruct(sqlContext, tableName, hadoopFile, date,
                            iWorldId);
                }
            }
            if (!mode.equalsIgnoreCase("NULL"))
                UserAccountAnalysis.create_tbRegisterUser(sqlContext, mode, date);
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        sc.stop();
        sc.close();
    }
}
