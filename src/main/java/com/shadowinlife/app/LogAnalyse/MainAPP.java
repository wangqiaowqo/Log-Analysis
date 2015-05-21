package com.shadowinlife.app.LogAnalyse;

import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.hive.HiveContext;

import com.shadowinlife.app.ExtraData.DataMigrateToMysql;
import com.shadowinlife.app.LogAnalyse.ProcessTableSQL.AcountProcessTable;
import com.shadowinlife.app.LogAnalyse.ProcessTableSQL.ChongzhiProcessTable;
import com.shadowinlife.app.LogAnalyse.ProcessTableSQL.MoneyFlowProcessTable;
import com.shadowinlife.app.OssTableSQL.UserAccountAnalysis;
import com.shadowinlife.app.Scheduler.CreateProcessTable;
import com.shadowinlife.app.Tools.LogLineSplit;
import com.shadowinlife.app.Tools.RegexPathFilter;
import com.shadowinlife.app.Tools.SplitAction;

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
            System.out.println("args[0]---FileTarget \n " + "args[1]----Mode \n "
                    + "args[2]---vFlagName \n  " + "args[3]----Date\n "
                    + "args[4]---1 for split file" + "args[5]---iworldid");
        }

        // Assemble path of the origin log files
        String nameNode = args[0]; /* hdfs://namenode:8020/ */
        String mode = args[1];
        String tableName = args[2];
        String date = args[3];
        String oozie = args[4];
        String iworldid = args[5];
        Path path;
        String targetFile;
        date = "20" + date;
        if(iworldid.equalsIgnoreCase("2")){
            path = new Path(nameNode + "/logsplit37/");
            targetFile = nameNode + "/logsplit37/*/" + tableName + date + "/*";
        }
        else {
            path = new Path(nameNode + "/logsplit/");
            targetFile = nameNode + "/logsplit/*/" + tableName + date + "/*";
        }
        SparkConf conf = new SparkConf().setAppName("Log Analyzer");
        JavaSparkContext sc = new JavaSparkContext(conf);
        HiveContext sqlContext = new HiveContext(sc.sc());

        if (oozie != null && oozie.equalsIgnoreCase("1")) {
            SplitAction.split(sc, "hdfs://10-4-28-24:8020/logdata37/" + date + "/*/*", "/logsplit37");
        }
        
        try {

            RegexPathFilter regexPathFilter = new RegexPathFilter("(.*)" + tableName + date
                    + "(.*)");
            
            if (!regexPathFilter.accept(path)) {
                CreateProcessTable.FatTableWithoutFile(sqlContext, tableName, date, iworldid);
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
                CreateProcessTable.FatTableConstruct(sqlContext, tableName, hadoopFile, date, iworldid);
            }
            UserAccountAnalysis.create_tbRegisterUser(sqlContext, mode, date);
            //DataMigrateToMysql.iHive_TO_Mysql(sqlContext, date, mode, iworldid);
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        sc.stop();
        sc.close();
    }
}
