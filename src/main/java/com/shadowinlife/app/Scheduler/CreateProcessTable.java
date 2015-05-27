package com.shadowinlife.app.Scheduler;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.hive.HiveContext;
import org.apache.spark.sql.types.DataTypes;

import scala.Tuple2;

import com.shadowinlife.app.LogAnalyse.ProcessTableSQL.AcountProcessTable;
import com.shadowinlife.app.LogAnalyse.ProcessTableSQL.ChongzhiProcessTable;
import com.shadowinlife.app.LogAnalyse.ProcessTableSQL.MoneyFlowProcessTable;
import com.shadowinlife.app.LogAnalyse.ProcessTableSQL.TaskProcessTable;

public class CreateProcessTable {
    public static void FatTableConstruct(HiveContext sqlContext, String tableName,
            JavaPairRDD<String, String[]> hadoopFile, String date, String iworldid) {
        
        sqlContext.udf().register("ConvertNull", new UDF1<Integer, Integer>() {     
            private static final long serialVersionUID = 1L;
            @Override
            public Integer call(Integer value) throws Exception {
                if (value == null)
                    return -1;
                return value;
            }
        }, DataTypes.IntegerType);
        
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
                    "oss_dm_dajian_tbTask");
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
