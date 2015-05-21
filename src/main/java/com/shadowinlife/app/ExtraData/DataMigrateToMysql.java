package com.shadowinlife.app.ExtraData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.hive.HiveContext;

public class DataMigrateToMysql {

    public static void main(String args[]) {
        
        String databaseName = null, url = null, date = null, 
                gameid = null, accounttype = null, worldid = null, 
                mode = null, table = null;
        String strWhere = " 1=1";
        for (int i = 0; i < args.length; i = i + 2) {
            if (!args[i].contains("--")) {
                System.out.println("Wrong Parameter\n --help for all parameters");
                return;
            }
            if (args[i].contains("help")) {
                System.out.println("--CONF Read configuration file and ignore other parameters\n"
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
            case "CONF":
                String confFile = args[i+1];
                break;
            case "DB":
                databaseName = args[i + 1];
                break;
            case "URL":
                url = args[i + 1];
                break;
            case "DATE":
                date = args[i + 1];
                strWhere = strWhere + " AND index_dtstatdate=DATE2LONG('" + date + "')";
                break;
            case "GAMEID":
                gameid = args[i + 1];
                strWhere = strWhere + " AND igameid=" + gameid;
                break;
            case "WORLDID":
                worldid = args[i + 1];
                strWhere = strWhere + " AND iworld=" + worldid;
                break;
            case "ACCOUNTTYPE":
                accounttype = args[i + 1];
                strWhere = strWhere + " AND iaccounttype=" + accounttype;
                break;
            case "MODE":
                mode = args[i + 1];
                break;
            case "TABLE":
                table = " " + args[i + 1];
                break;

            }
        }
        if(date==null || (mode==null&table==null)){
            System.out.println("DATE CAN NOT BE NULL\n  MODE OR TABLE CAN NOT BE NULL");
            return;
        }
        SparkConf conf = new SparkConf().setAppName("Extra Data");
        JavaSparkContext sc = new JavaSparkContext(conf);
        HiveContext sqlContext = new HiveContext(sc.sc());
        if (mode != null) {
            iHive_TO_Mysql(sqlContext, mode, strWhere);
        }
        else if (table != null){
            DeliveryHiveDB Process = new DeliveryHiveDB();
            Process.DeliveryByTableName(sqlContext, databaseName, table, url, strWhere);
        } else {
            DeliveryHiveDB Process = new DeliveryHiveDB();
            Process.DeliveryByDBNames(sqlContext, databaseName, url, strWhere);
        }
        sc.stop();
        sc.close();
    }

    public static void iHive_TO_Mysql(HiveContext sqlContext, String mode,
            String strWhere) {
        sqlContext.sql("use dbprocess");
        sqlContext.sql("ADD JAR hdfs://10-4-28-24:8020//udf.jar");

        String mySqlURL = "jdbc:mysql://10-4-28-24:3306/dbDJOssResult?user=oss&password=oss";
        Connection conn;
        String strTableName;
        String sql;
        Statement stmt;
        try {
            conn = DriverManager.getConnection(mySqlURL);
            stmt = conn.createStatement();
        } catch (SQLException e1) {
            e1.printStackTrace();
            return;
        }

        try {
            strTableName = "oss_dm_dajian_" + mode + "_tbRegisterUser";
            sql = "delete from " + strTableName + " " + strWhere;
            stmt.executeUpdate(sql);

            DataFrame oss_dm_mode_tbRegisterUser = sqlContext
                    .sql("select dtstatdate,iperiod,igameid,iaccounttype,iworld,iregnum,"
                            + "iactivity,ilostnum,ibacknum,itimes,ionlinetime from oss_dm_" + mode
                            + "_tbRegisterUser" + strWhere);

            oss_dm_mode_tbRegisterUser.insertIntoJDBC(mySqlURL, strTableName, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            strTableName = "oss_dm_dajian_" + mode + "_tbRegisterUserTypeDis";
            sql = "delete from " + strTableName + " " + strWhere;
            stmt.executeUpdate(sql);
            DataFrame oss_dm_mode_tbRegisterUserTypeDis = sqlContext
                    .sql("select dtstatdate,stype,iperiod,igameid,iaccounttype,iworld,stypevalue,iregnum "
                            + "FROM oss_dm_" + mode + "_tbregisterusertypedis" + strWhere);
            oss_dm_mode_tbRegisterUserTypeDis.insertIntoJDBC(mySqlURL, strTableName, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            strTableName = "oss_dm_dajian_" + mode + "_tbDayNewRegTypeDis";
            sql = "delete from " + strTableName + " " + strWhere;
            stmt.executeUpdate(sql);
            DataFrame oss_dm_mode_tbDayNewRegTypeDis = sqlContext
                    .sql("select dtstatdate,stype,igameid,iaccounttype,iworld,stypevalue,iregnum "
                            + "from oss_dm_" + mode + "_tbdaynewregtypedis" + strWhere);
            oss_dm_mode_tbDayNewRegTypeDis.insertIntoJDBC(mySqlURL, "oss_dm_dajian_" + mode
                    + "_tbDayNewRegTypeDis", false);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            strTableName = "oss_dm_dajian_" + mode + "_tbActivityScaleDis";
            sql = "delete from " + strTableName + " " + strWhere;
            stmt.executeUpdate(sql);
            DataFrame oss_dm_mode_tbactivityscaledis = sqlContext
                    .sql("select dtstatdate,ssourceuser,iperiod,igameid,"
                            + "iaccounttype,iworld,ilevel,iactivityday,iactivitynum "
                            + "from oss_dm_" + mode + "_tbactivityscaledis" + strWhere);
            oss_dm_mode_tbactivityscaledis.insertIntoJDBC(mySqlURL, "oss_dm_dajian_" + mode
                    + "_tbActivityScaleDis ", false);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            strTableName = "oss_dm_dajian_" + mode + "_tbDayUserActivityTypeDis";
            sql = "delete from " + strTableName + " " + strWhere;
            stmt.executeUpdate(sql);
            DataFrame oss_dm_mode_tbdayuseractivitytypedis = sqlContext
                    .sql("select dtstatdate,stype,igameid,iaccounttype,iworld,stypevalue,idayactivitynum "
                            + "from oss_dm_" + mode + "_tbdayuseractivitytypedis" + strWhere);
            oss_dm_mode_tbdayuseractivitytypedis.insertIntoJDBC(mySqlURL, "oss_dm_dajian_" + mode
                    + "_tbDayUserActivityTypeDis", false);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            strTableName = "oss_dm_dajian_" + mode + "_tbStayScaleDis";
            sql = "delete from " + strTableName + " " + strWhere;
            stmt.executeUpdate(sql);
            DataFrame oss_dm_mode_tbstayscaledis = sqlContext
                    .sql("select dtstatdate,ssourceuser,iperiod,igameid,"
                            + "iaccounttype,iworld,ilevel,ilookday,iactivitynum from oss_dm_"
                            + mode + "_tbstayscaledis" + strWhere);
            oss_dm_mode_tbstayscaledis.insertIntoJDBC(mySqlURL, "oss_dm_dajian_" + mode
                    + "_tbStayScaleDis", false);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            strTableName = "oss_dm_dajian_" + mode + "_tbUserActivityTypeDis";
            sql = "delete from " + strTableName + " " + strWhere;
            stmt.executeUpdate(sql);
            DataFrame oss_dm_mode_tbuseractivitytypedis = sqlContext
                    .sql("select dtstatdate,stype,stypevalue,iperiod,igameid,iaccounttype,iworld,"
                            + "iactivitynum,ilostnum,ibacknum from oss_dm_" + mode
                            + "_tbuseractivitytypedis" + strWhere);
            oss_dm_mode_tbuseractivitytypedis.insertIntoJDBC(mySqlURL, "oss_dm_dajian_" + mode
                    + "_tbUserActivityTypeDis", false);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
