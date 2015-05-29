package com.shadowinlife.app.Scheduler;

import org.apache.hadoop.fs.Path;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.hive.HiveContext;
import org.apache.spark.sql.types.DataTypes;

import scala.Tuple2;

import com.shadowinlife.app.LogAnalyse.ProcessTableSQL.AcountProcessTable;
import com.shadowinlife.app.LogAnalyse.ProcessTableSQL.ChongzhiProcessTable;
import com.shadowinlife.app.LogAnalyse.ProcessTableSQL.MoneyFlowProcessTable;
import com.shadowinlife.app.LogAnalyse.ProcessTableSQL.TaskProcessTable;
import com.shadowinlife.app.Tools.LogLineSplit;
import com.shadowinlife.app.Tools.RegexPathFilter;

public class CreateProcessTable {
    public static void FatTableConstruct(JavaSparkContext sc, HiveContext sqlContext, String HDFSNameNode,
            String tableName, String date, String iworldid) {

        sqlContext.udf().register("ConvertNull", new UDF1<Integer, Integer>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Integer call(Integer value) throws Exception {
                if (value == null)
                    return -1;
                return value;
            }
        }, DataTypes.IntegerType);

        Path path = null;
        String targetFile = null;
        JavaPairRDD<String, String[]> hadoopFile = null;
        RegexPathFilter filter = new RegexPathFilter("(.*)" + tableName + date
                + "(.*)");
        
        
        if (iworldid != null && iworldid.equalsIgnoreCase("2")) {
            path = new Path(HDFSNameNode + "/logsplit37/");
            targetFile = HDFSNameNode + "/logsplit37/*/" + tableName + date + "/*";
        } else {
            path = new Path(HDFSNameNode + "/logsplit/");
            targetFile = HDFSNameNode + "/logsplit/*/" + tableName + date + "/*";
        }
        
       
        if (!filter.accept(path)) {
            FatTableWithoutFile(sqlContext, tableName, date, iworldid);
            return;
        } else {
            // Read origin log file

            JavaRDD<String> logLines = sc.textFile(targetFile);

            // Split origin file into key-value model
            hadoopFile = logLines
                    .mapToPair(new PairFunction<String, String, String[]>() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public Tuple2<String, String[]> call(String line) throws Exception {
                            LogLineSplit temp = LogLineSplit.parseFromLogFile(line);
                            return new Tuple2<String, String[]>(temp.getKeyName(), temp
                                    .getLineValues());
                        }
                    });
        }
        
        switch (tableName) {
        case "RoleLogin":
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

            AcountProcessTable.process(sqlContext, roleLoginRDD, roleLogoutRDD, date, iworldid);
            break;

        case "ChongZhi":
            JavaRDD<String[]> chongZhiRDD = hadoopFile.values();
            ChongzhiProcessTable.process(sqlContext, chongZhiRDD, date, iworldid);
            break;

        case "MoneyFlow":
            JavaRDD<String[]> moneyFlowRDD = hadoopFile.values();
            MoneyFlowProcessTable.process(sqlContext, moneyFlowRDD, date, iworldid);
            break;
        case "Task":
            // Filter origin file into different RDD
            JavaRDD<String[]> rddTaskStart = hadoopFile.filter(
                    new Function<Tuple2<String, String[]>, Boolean>() {

                        private static final long serialVersionUID = 1L;

                        @Override
                        public Boolean call(Tuple2<String, String[]> f) throws Exception {
                            if (f._1.contains("TaskStart")) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    }).values();

            JavaRDD<String[]> rddTaskFinished = hadoopFile.filter(
                    new Function<Tuple2<String, String[]>, Boolean>() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public Boolean call(Tuple2<String, String[]> f) throws Exception {
                            if (f._1.contains("TaskFinished")) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    }).values();

            TaskProcessTable.process(sqlContext, rddTaskStart, rddTaskFinished, date, iworldid,
                    "jdbc:mysql://10-4-28-24:3306/dbDJOssResult?user=oss&password=oss",
                    "oss_dm_tbTask");
            break;
        }
    }

    public static void FatTableWithoutFile(HiveContext sqlContext, String tableName, String date,
            String iworldid) {
        switch (tableName) {
        case "RoleLogin":
            AcountProcessTable.ModifyProcessTableWithoutLogFile(sqlContext, date, iworldid);
            break;

        case "ChongZhi":
            ChongzhiProcessTable.ModifyProcessTableWithoutLogFile(sqlContext, date, iworldid);
            break;

        case "MoneyFlow":
            MoneyFlowProcessTable.ModifyProcessTableWithoutLogFile(sqlContext, date, iworldid);
            break;

        }
    }

}
