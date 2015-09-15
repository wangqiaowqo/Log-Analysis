package com.shadowinlife.app.ExtraData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.hive.HiveContext;

public class DeliveryTableByMode {
    public static void DeliveryByMode(HiveContext sqlContext,String mySqlURL, String mode, String strWhere) {

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
            strTableName = "oss_dm_" + mode + "_tbRegisterUser";
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
            strTableName = "oss_dm_" + mode + "_tbRegisterUserTypeDis";
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
            strTableName = "oss_dm_" + mode + "_tbDayNewRegTypeDis";
            sql = "delete from " + strTableName + " " + strWhere;
            stmt.executeUpdate(sql);
            DataFrame oss_dm_mode_tbDayNewRegTypeDis = sqlContext
                    .sql("select dtstatdate,stype,igameid,iaccounttype,iworld,stypevalue,iregnum "
                            + "from oss_dm_" + mode + "_tbdaynewregtypedis" + strWhere);
            oss_dm_mode_tbDayNewRegTypeDis.insertIntoJDBC(mySqlURL, "oss_dm_" + mode
                    + "_tbDayNewRegTypeDis", false);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            strTableName = "oss_dm_" + mode + "_tbActivityScaleDis";
            sql = "delete from " + strTableName + " " + strWhere;
            stmt.executeUpdate(sql);
            DataFrame oss_dm_mode_tbactivityscaledis = sqlContext
                    .sql("select dtstatdate,ssourceuser,iperiod,igameid,"
                            + "iaccounttype,iworld,ilevel,iactivityday,iactivitynum "
                            + "from oss_dm_" + mode + "_tbactivityscaledis" + strWhere);
            oss_dm_mode_tbactivityscaledis.insertIntoJDBC(mySqlURL, "oss_dm_" + mode
                    + "_tbActivityScaleDis ", false);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            strTableName = "oss_dm_" + mode + "_tbDayUserActivityTypeDis";
            sql = "delete from " + strTableName + " " + strWhere;
            stmt.executeUpdate(sql);
            DataFrame oss_dm_mode_tbdayuseractivitytypedis = sqlContext
                    .sql("select dtstatdate,stype,igameid,iaccounttype,iworld,stypevalue,idayactivitynum "
                            + "from oss_dm_" + mode + "_tbdayuseractivitytypedis" + strWhere);
            oss_dm_mode_tbdayuseractivitytypedis.insertIntoJDBC(mySqlURL, "oss_dm_" + mode
                    + "_tbDayUserActivityTypeDis", false);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            strTableName = "oss_dm_" + mode + "_tbStayScaleDis";
            sql = "delete from " + strTableName + " " + strWhere;
            stmt.executeUpdate(sql);
            DataFrame oss_dm_mode_tbstayscaledis = sqlContext
                    .sql("select dtstatdate,ssourceuser,iperiod,igameid,"
                            + "iaccounttype,iworld,ilevel,ilookday,iactivitynum from oss_dm_"
                            + mode + "_tbstayscaledis" + strWhere);
            oss_dm_mode_tbstayscaledis.insertIntoJDBC(mySqlURL, "oss_dm_" + mode
                    + "_tbStayScaleDis", false);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            strTableName = "oss_dm_" + mode + "_tbUserActivityTypeDis";
            sql = "delete from " + strTableName + " " + strWhere;
            stmt.executeUpdate(sql);
            DataFrame oss_dm_mode_tbuseractivitytypedis = sqlContext
                    .sql("select dtstatdate,stype,stypevalue,iperiod,igameid,iaccounttype,iworld,"
                            + "iactivitynum,ilostnum,ibacknum from oss_dm_" + mode
                            + "_tbuseractivitytypedis" + strWhere);
            oss_dm_mode_tbuseractivitytypedis.insertIntoJDBC(mySqlURL, "oss_dm_" + mode
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
