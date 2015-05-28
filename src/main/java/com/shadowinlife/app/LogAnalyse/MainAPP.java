package com.shadowinlife.app.LogAnalyse;

import org.apache.hadoop.fs.Path;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.hive.HiveContext;

import com.shadowinlife.app.OssTableSQL.UserAccountAnalysis;
import com.shadowinlife.app.Scheduler.CreateProcessTable;

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
        String tableName = null;

        for (int i = 0; i < args.length; i = i + 2) {
           
            //入参格式监测
            if (!args[i].contains("--")) {
                System.out.println("Wrong Parameter\n --help for all parameters");
                return;
            }
            
            //帮助文件
            if (args[i].contains("help")) {
                System.out.println("--HDFS  url for hdfs\n"
                        + "--MODE  MODE_NAME  IF MODE=NULL Dont't Create Oss Table\n"
                        + "--MYSQLURL for MySql\n" + "--DATE Index Field Date\n"
                        + "--GAMEID Index Filed Gameid\n" + "--WORLDID Index Filed WORLDID\n"
                        + "--ACCOUNTTYPE Index Filed ACCOUNTTYPE\n"
                        + "--TAG   ALL 即计算fat表，也计算结果表， mode为空则所有模式都计算\n"
                        + "        OSS  只计算结果表，mode为空计算所有种类的结果表\n"
                        + "        FAT 只计算Fat表，Mode为空则计算所有种类的中间表\n");
                return;
            }
            
            //获取入参
            switch (args[i]) {
            case "--HDFS":
                HDFSNameNode = args[i + 1];
                break;
            case "--MODE":
                mode = args[i + 1];
                switch (mode) {
                case "pay":
                    tableName = "MoneyFlow";
                    break;
                case "deposit":
                    tableName = "ChongZhi";
                    break;
                case "login":
                    tableName = "RoleLogin";
                    break;
                default:
                    tableName = mode;
                }
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

            }
        }
       
        //入参合法性监测
        if (date == null
                || Flag == null
                || (Flag.equalsIgnoreCase("ALL") || Flag.equalsIgnoreCase("FAT")
                        && HDFSNameNode == null && iWorldId == null)) {
            System.out.println("Parameter is illegal, see --help");
        }
       
        //初始化基本环境
        SparkConf conf = new SparkConf().setAppName("Log Analyzer");
        JavaSparkContext sc = new JavaSparkContext(conf);
        HiveContext sqlContext = new HiveContext(sc.sc());
        
        //根据入参调度程序
        try {
            if ((Flag.equalsIgnoreCase("ALL") || Flag.equalsIgnoreCase("FAT")) && mode != null) {

                CreateProcessTable.FatTableConstruct(sc, sqlContext, HDFSNameNode, tableName, date,
                        iWorldId);

            } else if ((Flag.equalsIgnoreCase("ALL") || Flag.equalsIgnoreCase("FAT"))
                    && mode == null) { 
                    CreateProcessTable.FatTableConstruct(sc, sqlContext, HDFSNameNode, "RoleLogin", date,
                            iWorldId);
                    CreateProcessTable.FatTableConstruct(sc, sqlContext, HDFSNameNode, "Task", date,
                            iWorldId);
                    CreateProcessTable.FatTableConstruct(sc, sqlContext, HDFSNameNode, "MoneyFlow", date,
                            iWorldId);
                    CreateProcessTable.FatTableConstruct(sc, sqlContext, HDFSNameNode, "ChongZhi", date,
                            iWorldId);

            } else if ((Flag.equalsIgnoreCase("ALL") || Flag.equalsIgnoreCase("OSS"))
                    && mode == null) {
                UserAccountAnalysis.create_tbRegisterUser(sqlContext, "pay", date);
                UserAccountAnalysis.create_tbRegisterUser(sqlContext, "deposit", date);
                UserAccountAnalysis.create_tbRegisterUser(sqlContext, "login", date);
            } else if ((Flag.equalsIgnoreCase("ALL") || Flag.equalsIgnoreCase("OSS"))
                    && mode != null) {
                UserAccountAnalysis.create_tbRegisterUser(sqlContext, mode, date);

            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        sc.stop();
        sc.close();
    }
}
