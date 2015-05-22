package com.shadowinlife.app.ExtraData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.hive.HiveContext;

public class Main {

    public static void main(String args[]) {
        
        String databaseName = null, url = null, date = null, 
                gameid = null, accounttype = null, worldid = null, 
                mode = null, table = null, confFile=null;
        String line;
        FileReader fileReader;
        Map<String,String> confMap = new HashMap<String, String>();
        String strWhere = " WHERE 1=1";
        String mySQLWhere = " WHERE 1=1";
        for (int i = 0; i < args.length; i = i + 2) {
            if (!args[i].contains("--")) {
                System.out.println("Wrong Parameter\n --help for all parameters");
                return;
            }
            if (args[i].contains("help")) {
                System.out.println("--CONF Read configuration file\n"
                        + "--DB  DataBase name of Hive\n" 
                        + "--URL url for MySql\n"
                        + "--DATE Index Field Date\n"   
                        + "--GAMEID Index Filed Gameid\n"
                        + "--WORLDID Index Filed WORLDID\n"
                        + "--ACCOUNTTYPE Index Filed ACCOUNTTYPE\n" 
                        + "--MODE Mode of table\n"
                        + "--TABLE Name of a actual hive table");
                return;
            }
            switch (args[i]) {
            case "--CONF":
                confFile = args[i+1];
                break;
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
                mySQLWhere = mySQLWhere + " AND dtStatDate='"+date+"'";
                break;
            case "--GAMEID":
                gameid = args[i + 1];
                strWhere = strWhere + " AND igameid=" + gameid;
                mySQLWhere = mySQLWhere + " AND iGameID="+gameid;
                break;
            case "--WORLDID":
                worldid = args[i + 1];
                strWhere = strWhere + " AND iworld=" + worldid;
                mySQLWhere = mySQLWhere + " AND iWorldID="+worldid;
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
        if(date==null || confFile==null||(mode==null&table==null)){
            System.out.println(" date  NOT be NULL\n  mode OR table NOT be NULL");
            return;
        }
        
        try {
            fileReader = new FileReader(confFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                String[] kv = line.split(" ");
                confMap.put(kv[0], kv[1]);
            }
            
            // Always close files.
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
                  
        SparkConf conf = new SparkConf().setAppName("Extra Data");
        JavaSparkContext sc = new JavaSparkContext(conf);
        HiveContext sqlContext = new HiveContext(sc.sc());
        
        sqlContext.sql("use "+databaseName);
        sqlContext.sql("ADD JAR hdfs://10-4-28-24:8020//udf.jar");
        
        if (mode != null) {
            DeliveryTableByMode.DeliveryByMode(sqlContext, url, mode, strWhere);
        }
        else if (table != null){
            DeliveryHiveDB Process = new DeliveryHiveDB();
            Process.DeliveryByTableName(sqlContext, table, url, strWhere, mySQLWhere, confMap);
        } else {
            DeliveryHiveDB Process = new DeliveryHiveDB();
            Process.DeliveryByDBNames(sqlContext, databaseName, url, strWhere, mySQLWhere, confMap);
        }
        sc.stop();
        sc.close();
    }

    
}
