package com.shadowinlife.app.ExtraData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.hive.HiveContext;

public class DeliveryDataToMysql {

    public void insertIntoMysql(HiveContext sqlContext, String mySqlURL, String strTable,
            List<String> columnNames, String strWhere) {
        sqlContext.sql("use dbprocess");
        sqlContext.sql("ADD JAR hdfs://10-4-28-24:8020//udf.jar");

        Connection conn;
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
            sql = "SELECT mTableName FROM NAMECONFIG WHERE hTableName="+strTable;
            ResultSet rs = stmt.executeQuery(sql);
            String mTable = rs.getString(0);
            sql = "DELETE FROM " + mTable + strWhere;
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
            sql = "SELECT ";
            for (int i = 0; i < columnNames.size() - 1; i++) {
                sql = sql + columnNames.get(i) + ",";
            }
            sql = sql + columnNames.get(columnNames.size()-1) + " FROM "+strTable+strWhere;
            DataFrame oss_dm_mode_tbRegisterUser = sqlContext.sql(sql);

            oss_dm_mode_tbRegisterUser.insertIntoJDBC(mySqlURL, mTable, false);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
