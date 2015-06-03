package com.shadowinlife.app.LogAnalyse.ProcessTableSQL;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.hive.HiveContext;

import com.shadowinlife.app.LogAnalyse.SQLModelFactory.RoleStatus;;

public class ProceeMoneyStorage {

    private static String SQL_AcceptTask = "SELECT "
            + "1 AS `iMoneyType`," // iMoneyType 1 钻石
            + "-1 AS `iStore`," // iStore
            + "ConvertNull(`iJobId`) AS `iJobId`,"
            + "ConvertNull(`iGender`) AS `iGender`,"
            + "ConvertNull(`iRoleLevel`) AS `iRoleLevel`,"
            + "-1 AS `iVipLevel`," // iVipLevel
            + "COUNT(DISTINCT `iRoleId`) AS `iRoleNum`,"
            + "SUM(`iDepositPoints`) AS `iMoney` "
            + "FROM `MoneyStorage` "
            + "WHERE `iDepositPoints` > 0 "
            + "GROUP BY `iJobId`,`iGender`,`iRoleLevel` WITH CUBE "
            + "union "
            + "SELECT "
            + "1 AS `iMoneyType`," // iMoneyType 1 钻石
            + "-1 AS `iStore`," // iStore
            + "ConvertNull(`iJobId`) AS `iJobId`,"
            + "ConvertNull(`iGender`) AS `iGender`,"
            + "ConvertNull(`iRoleLevel`) AS `iRoleLevel`,"
            + "-1 AS `iVipLevel`," // iVipLevel
            + "COUNT(DISTINCT `iRoleId`) AS `iRoleNum`,"
            + "SUM(`iPoints` - `iDepositPoints`) AS `iMoney` "
            + "FROM `MoneyStorage` "
            + "WHERE `iPoints` - `iDepositPoints` > 0 "
            + "GROUP BY `iJobId`,`iGender`,`iRoleLevel` WITH CUBE "
            + "union "
            + "SELECT "
            + "1 AS `iMoneyType`," // iMoneyType 1 钻石
            + "-1 AS `iStore`," // iStore
            + "ConvertNull(`iJobId`) AS `iJobId`,"
            + "ConvertNull(`iGender`) AS `iGender`,"
            + "ConvertNull(`iRoleLevel`) AS `iRoleLevel`,"
            + "-1 AS `iVipLevel`," // iVipLevel
            + "COUNT(DISTINCT `iRoleId`) AS `iRoleNum`,"
            + "SUM(`iMoney`) AS `iMoney` "
            + "FROM `MoneyStorage` "
            + "WHERE `iMoney` > 0 "
            + "GROUP BY `iJobId`,`iGender`,`iRoleLevel` WITH CUBE";

    private static String SQL_UNION = "SELECT '%s',"
            + "1," // iAccountType
            + "%s," // iWorldId
            + "`iMoneyType`," // iMoneyType 1 钻石
            + "`iStore`," // iStore
            + "`iJobId`,"
            + "`iGender`,"
            + "`iRoleLevel`,"
            + "`iVipLevel`," // iVipLevel
            + "`iRoleNum`,"
            + "`iMoney` "
            + "FROM `T1`";

    public static boolean process(HiveContext sqlContext, JavaRDD<String[]> RDD1,
            String date, String iworldid, String url,
            String table) {

        try {

            // Create RDD from login FILES
            JavaRDD<RoleStatus> tbMoneyStorage = RDD1.map(new Function<String[], RoleStatus>() {

                @Override
                public RoleStatus call(String[] line) {

                    return RoleStatus.parseFromLogFile(line);

                }

            });


            // Convert all the values into the spark table
            DataFrame dfTaskStart = sqlContext.createDataFrame(tbMoneyStorage, RoleStatus.class);
            sqlContext.registerDataFrameAsTable(dfTaskStart, "MoneyStorage");
           
            // run SQL analysis SQL
            sqlContext.sql(SQL_AcceptTask).registerTempTable("T1");

            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            String delMysql = "DELETE FROM " + "oss_dm_tbMoneyStorage" + " Where dtStatDate='" + date
                    + "' AND iWorldId=" + iworldid;
            stmt.executeUpdate(delMysql);
            conn.close();
            sqlContext.sql(String.format(SQL_UNION, date, iworldid)).insertIntoJDBC(url, table,
                    false);

            // Free Mem
            sqlContext.dropTempTable("T1");
            sqlContext.dropTempTable("MoneyStorage");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
