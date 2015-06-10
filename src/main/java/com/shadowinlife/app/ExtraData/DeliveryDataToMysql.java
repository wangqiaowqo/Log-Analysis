package com.shadowinlife.app.ExtraData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.hive.HiveContext;

public class DeliveryDataToMysql {

    public void insertIntoMysql(HiveContext sqlContext, String mySqlURL, String strTable, String mTable,
            List<String> columnNames, String strWhere, String mySQLWhere) {
        Connection conn;
        String sql;
        Statement stmt;
        
        if(mTable==null)
            return;
        
        try {
            conn = DriverManager.getConnection(mySqlURL);
            stmt = conn.createStatement();
        } catch (SQLException e1) {
            e1.printStackTrace();
            return;
        }

        try {
            
            sql = "DELETE FROM " + mTable + mySQLWhere;
            System.out.println(sql);
            int RowDelCount = stmt.executeUpdate(sql);
            System.out.println("DELETE " + RowDelCount);
            stmt.close();
            conn.close();
            String hivesql = "SELECT ";
            for (int i = 0; i < columnNames.size() - 1; i++) {
                hivesql = hivesql + columnNames.get(i) + ",";
            }
            hivesql = hivesql + columnNames.get(columnNames.size()-1) + " FROM "+strTable+strWhere;
           
            DataFrame oss_dm_mode_tbRegisterUser = sqlContext.sql(hivesql);

            oss_dm_mode_tbRegisterUser.insertIntoJDBC(mySqlURL, mTable, false);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
