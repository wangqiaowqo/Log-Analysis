package com.shadowinlife.app.Split;

import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.SQLContext;

import com.shadowinlife.app.Tools.ReadConfigurationFile;

import scala.Tuple2;

public class Main {

    public static void main(String[] args) {
        String path = null;
        String date = null;
        String iGameId = null;
        String iWorldId = null;
        String iAccountType = null;
        String NameNode = null;
        String time = "";

        for (int i = 0; i < args.length; i = i + 2) {
            if (!args[i].contains("--")) {
                System.out.println("Wrong Parameter\n --help for all parameters");
                return;
            }
            if (args[i].contains("help")) {
                System.out.println("--HDFS   Name Node  NOT NULL\n"
                        + "--DATE Index Field Date  NOT NULL\n" + "--GAMEID Index Filed Gameid\n"
                        + "--WORLDID Index Filed WORLDID\n"
                        + "--ACCOUNTTYPE Index Filed ACCOUNTTYPE\n" + "--TIME Hours of the Field\n"
                        + "--CONF configuration files for javabean class");
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
            case "--TIME":
                time = args[i + 1];
                break;
            case "--CONF":
                path = args[i + 1];
                break;
            }
        }

        String targetFile = NameNode + "/LOG/" + iGameId + "/" + iAccountType + "/" + iWorldId
                + "/" + date + "/" + time + "/*";
        String dstPath = "/LOG/" + iGameId + "/" + iAccountType + "/" + iWorldId + "/" + date + "/"
                + time + "/";

        SparkConf sparkConf = new SparkConf().setAppName("Log Filter");
        JavaSparkContext sc = new JavaSparkContext(sparkConf);
        SQLContext sqlContext = new SQLContext(sc);

        try {
            
            //RoleLogin|2015-07-24 14:14:11|1437656813793|37wan-ShenZhen-201505061100|55b1d7b1e4b0a2808feb2007|1048634||1|1|1|0|0| | |192.168.1.51|1|0|0|0|{"gold":0,"crystal":0,"bindCrystal":0}|2015-07-24 14:14:11|0| 

            
            // 读取原始日志文件
            JavaRDD<String> logLines = sc.textFile("test");

            // 根据每一行日志的头切分成key value形式
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

            // 读取每种日志的表结构
            String[] TableNames = {"RoleLogin"} ;
            
            // 根据表结构把value部分装配成parquet并持久化, schemaStruct[0] 是表明, 后面是表结构
            for (String TableName : TableNames) {
                System.out.println("gongmeng Table Name: " + TableName);
                String[] SchemaTypes = {
                        "datetime" ,
                        "bigint"   ,
                        "varchar"  ,
                        "varchar"  ,
                        "bigint"      ,
                        "varchar"  ,
                        "bigint"      ,
                        "bigint"      ,
                        "bigint"      ,
                        "bigint"      ,
                        "bigint"      ,
                        "varchar"  ,
                        "varchar"  ,
                        "varchar"  ,
                        "varchar"  ,
                        "bigint"      ,
                        "bigint"      ,
                        "bigint"      ,
                        "Map"     ,
                        "datetime" ,
                        "bigint"      ,};
                String[] SchemaNames = {
                        "dtEventTime"          ,          
                        "iEventId"             ,
                        "vVersionId"           ,
                        "vUin"                 ,
                        "iRoleId"              ,
                        "vRoleName"            ,
                        "iRoleJob"             ,
                        "iRoleGender"          ,
                        "iRoleLevel"           ,
                        "iRoleVipLevel"        ,
                        "iRoleReputationLevel" ,
                        "vRoleElse1"           ,
                        "vRoleElse2"           ,
                        "vClientIp"            ,
                        "vZoneId"              ,
                        "iExp"                 ,
                        "iReputation"          ,
                        "iEnergy"              ,
                        "jMoney"               ,
                        "dtCreateTime"         ,
                        "lOnlineTotalTime"     };
                

                SaveParquet.LogToParquet(sqlContext, hadoopFile, TableName, SchemaNames,
                        SchemaTypes, dstPath, iGameId, iAccountType, iWorldId);
            }
            sc.stop();
            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
