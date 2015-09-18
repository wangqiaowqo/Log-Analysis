package com.shadowinlife.app.LogAnalyse.Action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MysqlAction {
    private static final Logger logger = LogManager.getLogger();

    public static void ExcuteMysqlSQL(String url, String sql) {
        try {
            // 建立一个Mysql连接
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                System.out.println(sql + " : Mysql Excuete Successfully!");
            }
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            logger.catching(e);

        }
    }
}
