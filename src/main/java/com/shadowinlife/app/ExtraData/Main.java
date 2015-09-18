package com.shadowinlife.app.ExtraData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.hive.HiveContext;

public class Main {

    public static void main(String args[]) {

        String databaseName = null, url = null, date = null, gameid = null, accounttype = null, worldid = null, mode = null, table = null;

        String strWhere = " WHERE 1=1";
        String mySQLWhere = " WHERE 1=1";
        // 读取入参
        for (int i = 0; i < args.length; i = i + 2) {
            if (!args[i].contains("--")) {
                System.out.println("Wrong Parameter\n --help for all parameters");
                return;
            }
            if (args[i].contains("help")) {
                System.out.println("--CONF Read configuration file\n"
                        + "--DB  DataBase name of Hive\n" + "--URL url for MySql\n"
                        + "--DATE Index Field Date\n" + "--GAMEID Index Filed Gameid\n"
                        + "--WORLDID Index Filed WORLDID\n"
                        + "--ACCOUNTTYPE Index Filed ACCOUNTTYPE\n" + "--MODE Mode of table\n"
                        + "--TABLE Name of a actual hive table");
                return;
            }
            switch (args[i]) {

            case "--DB":
                databaseName = args[i + 1];
                break;
            case "--URL":
                url = args[i + 1];
                System.out.println(url);
                break;
            case "--DATE":
                date = args[i + 1];
                strWhere = strWhere + " AND index_dtstatdate=DATE2LONG('" + date + "')";
                mySQLWhere = mySQLWhere + " AND dtStatDate='" + date + "'";
                break;
            case "--GAMEID":
                gameid = args[i + 1];
                strWhere = strWhere + " AND igameid=" + gameid;
                mySQLWhere = mySQLWhere + " AND iGameID=" + gameid;
                break;
            case "--WORLDID":
                worldid = args[i + 1];
                strWhere = strWhere + " AND iworld=" + worldid;
                mySQLWhere = mySQLWhere + " AND iWorldID=" + worldid;
                break;
            case "--ACCOUNTTYPE":
                accounttype = args[i + 1];
                strWhere = strWhere + " AND iaccounttype=" + accounttype;
                mySQLWhere = mySQLWhere + " AND iAccountType=" + accounttype;
                break;
            case "--MODE":
                mode = args[i + 1];
                break;
            case "--TABLE":
                table = " " + args[i + 1];
                break;

            }
        }
        if (date == null || (mode == null && table == null && databaseName == null)) {
            System.out.println(" date  NOT be NULL\n " + "OR mode OR table NOT be NULL");
            return;
        }

        // 初始化spark
        SparkConf conf = new SparkConf().setAppName("Extra Data");
        JavaSparkContext sc = new JavaSparkContext(conf);
        HiveContext sqlContext = new HiveContext(sc.sc());
        // 选择入参中指定的数据库
        sqlContext.sql("use " + databaseName);
        sqlContext.sql("ADD JAR hdfs://10-4-28-24:8020//udf.jar");
        // 初始化一个映射表
        Map<String, String> confMap = new HashMap<String, String>();
        // 得到HIVE数据库中的所有表的名字
        DataFrame hiveTables = sqlContext.sql("show tables");
        // 初始化一个ArrayList来保存hive的结果表 表名
        List<String> hiveArray = new ArrayList<String>();
        for (Row r : hiveTables.collect()) {
            if (r.getString(0).contains("oss_dm_")) {
                hiveArray.add(r.getString(0));
            }

        }
        // 得到mysql中所有表的名字
        // 初始化一个ArrayList来保存mysql的结果表 表名
        List<String> mysqlArray = new ArrayList<String>();
        try {
            // 建立一个Mysql连接
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SHOW TABLES");
            while (rs.next()) {
                if (rs.getString(1).contains("oss_dm_")) {
                    mysqlArray.add(rs.getString(1));
                }
            }
            conn.close();

        } catch (SQLException e1) {
            e1.printStackTrace();
            return;
        }
        // 建立HIVE到MySql的映射表
        for (String hTable : hiveArray) {
            for (String mTable : mysqlArray) {
                if (hTable.equalsIgnoreCase(mTable)) {
                    confMap.put(hTable, mTable);
                    break;
                }
            }
        }

        if (mode != null) {
            for (String hTable : hiveArray) {
                if (hTable.contains(mode)) {
                    DeliveryHiveDB.DeliveryByTableName(sqlContext, hTable, url, strWhere,
                            mySQLWhere, confMap);
                }
            }
        } else if (table != null) {

            // 出库单表的数据
            DeliveryHiveDB.DeliveryByTableName(sqlContext, table, url, strWhere, mySQLWhere,
                    confMap);
        } else {
            // 出库整个HIVE数据库
            for (String hTable : hiveArray) {
                System.out.println(hTable);
                DeliveryHiveDB.DeliveryByTableName(sqlContext, hTable, url, strWhere, mySQLWhere,
                        confMap);
            }
        }
        sc.stop();
        sc.close();
    }
}
