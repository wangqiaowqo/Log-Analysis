package com.shadowinlife.app.OssTableSQL;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.hive.HiveContext;

public class DataMigrateToMysql {
    public static void main(String args[]) {
        SparkConf conf = new SparkConf().setAppName("Log Analyzer");
        JavaSparkContext sc = new JavaSparkContext(conf);
        HiveContext sqlContext = new HiveContext(sc.sc());

        sc.stop();
        sc.close();
    }

    public static void iHive_TO_Mysql(HiveContext sqlContext, String date, String mode, String iworldid) {
        sqlContext.sql("use dbprocess");
        sqlContext.sql("ADD JAR hdfs://10-4-28-24:8020//udf.jar");
        String strWhere = " WHERE index_dtstatdate=DATE2LONG('" + date + "') AND iworldid="+iworldid;
        String mySqlURL = "jdbc:mysql://10-4-28-24:3306/dbDJOssResult?user=oss&password=oss";

        try {
            DataFrame oss_dm_login_tbRegisterUser = sqlContext
                    .sql("select dtstatdate,iperiod,igameid,iaccounttype,iworld,iregnum,"
                            + "iactivity,ilostnum,ibacknum,itimes,ionlinetime from oss_dm_login_tbRegisterUser"
                            + strWhere);
            oss_dm_login_tbRegisterUser.insertIntoJDBC(mySqlURL, "oss_dm_dajian_" + mode
                    + "_tbRegisterUser", false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            DataFrame oss_dm_login_tbRegisterUserTypeDis = sqlContext
                    .sql("select dtstatdate,stype,iperiod,igameid,iaccounttype,iworld,stypevalue,iregnum "
                            + "FROM oss_dm_login_tbregisterusertypedis" + strWhere);
            oss_dm_login_tbRegisterUserTypeDis.insertIntoJDBC(mySqlURL, "oss_dm_dajian_" + mode
                    + "_tbRegisterUserTypeDis", false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            DataFrame oss_dm_login_tbDayNewRegTypeDis = sqlContext
                    .sql("select dtstatdate,stype,igameid,iaccounttype,iworld,stypevalue,iregnum "
                            + "from oss_dm_login_tbdaynewregtypedis" + strWhere);
            oss_dm_login_tbDayNewRegTypeDis.insertIntoJDBC(mySqlURL, "oss_dm_dajian_" + mode
                    + "_tbDayNewRegTypeDis", false);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            DataFrame oss_dm_login_tbactivityscaledis = sqlContext
                    .sql("select dtstatdate,ssourceuser,iperiod,igameid,"
                            + "iaccounttype,iworld,ilevel,iactivityday,iactivitynum "
                            + "from oss_dm_login_tbactivityscaledis" + strWhere);
            oss_dm_login_tbactivityscaledis.insertIntoJDBC(mySqlURL, "oss_dm_dajian_" + mode
                    + "_tbActivityScaleDis ", false);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            DataFrame oss_dm_login_tbdayuseractivitytypedis = sqlContext
                    .sql("select dtstatdate,stype,igameid,iaccounttype,iworld,stypevalue,idayactivitynum "
                            + "from oss_dm_login_tbdayuseractivitytypedis" + strWhere);
            oss_dm_login_tbdayuseractivitytypedis.insertIntoJDBC(mySqlURL, "oss_dm_dajian_" + mode
                    + "_tbDayUserActivityTypeDis", false);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            DataFrame oss_dm_login_tbstayscaledis = sqlContext
                    .sql("select dtstatdate,ssourceuser,iperiod,igameid,"
                            + "iaccounttype,iworld,ilevel,ilookday,iactivitynum from oss_dm_login_tbstayscaledis"
                            + strWhere);
            oss_dm_login_tbstayscaledis.insertIntoJDBC(mySqlURL, "oss_dm_dajian_" + mode
                    + "_tbStayScaleDis", false);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            DataFrame oss_dm_login_tbuseractivitytypedis = sqlContext
                    .sql("select dtstatdate,stype,stypevalue,iperiod,igameid,iaccounttype,iworld,"
                            + "iactivitynum,ilostnum,ibacknum from oss_dm_login_tbuseractivitytypedis"
                            + strWhere);
            oss_dm_login_tbuseractivitytypedis.insertIntoJDBC(mySqlURL, "oss_dm_dajian_" + mode
                    + "_tbUserActivityTypeDis", false);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}