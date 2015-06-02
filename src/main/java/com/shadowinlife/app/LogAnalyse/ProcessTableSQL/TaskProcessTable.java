package com.shadowinlife.app.LogAnalyse.ProcessTableSQL;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.hive.HiveContext;

import com.shadowinlife.app.LogAnalyse.SQLModelFactory.TaskFinished;
import com.shadowinlife.app.LogAnalyse.SQLModelFactory.TaskStart;

public class TaskProcessTable {

    private static String SQL_AcceptTask = "select "
            + "ConvertNull(`iTaskType`) AS `iTaskType`,"
            + "ConvertNull(`iTaskId`) AS `iTaskId`,"
            + "ConvertNull(`iTaskStar`) AS `iTaskLevel`,"
            + "COUNT(DISTINCT `iRoleId`) AS `iAcceptRoleNum`,"
            + "COUNT(*) AS `iAcceptTimes`,"
            + "COUNT(DISTINCT `iUin`) AS `iAcceptUinNum` "
            + "FROM `TaskStart` GROUP BY `iTaskType`,`iTaskId`,`iTaskStar` "
            + "GROUPING SETS ((`iTaskType`,`iTaskId`,`iTaskStar`), (`iTaskType`,`iTaskId`),(`iTaskType`),())";

    private static String SQL_FinishedTask = "select "
            + "ConvertNull(`iTaskType`) AS `iTaskType`,"
            + "ConvertNull(`iTaskId`) AS `iTaskId`,"
            + "ConvertNull(`iTaskStar`) AS `iTaskLevel`,"
            + "COUNT(DISTINCT `iRoleId`) AS `iCompleteRoleNum`,"
            + "COUNT(*) AS `iCompleteTimes`,"
            + "COUNT(DISTINCT `iUin`) AS `iCompleteUinNum`,"
            + "SUM(`iTaskTime`) AS `iTotalTime` "
            + "FROM TaskFinished GROUP BY `iTaskType`,`iTaskId`,`iTaskStar` "
            + "GROUPING SETS ((`iTaskType`,`iTaskId`,`iTaskStar`), (`iTaskType`,`iTaskId`),(`iTaskType`),())";

    private static String SQL_UNION = "SELECT '%s',"
            + "1," // iAccountType
            + "%s," // iWorldId
            + "IF(T1.iTaskType IS NULL, T2.iTaskType, T1.iTaskType),"
            + "IF(T1.iTaskId IS NULL, T2.iTaskId, T1.iTaskId),"
            + "IF(T1.iTaskLevel IS NULL, T2.iTaskLevel, T1.iTaskLevel),"
            + "IF(T1.iAcceptRoleNum IS NULL, '0' ,T1.iAcceptRoleNum),"
            + "IF(T1.iAcceptTimes IS NULL, '0', T1.iAcceptTimes),"
            + "IF(T2.iCompleteRoleNum IS NULL, '0', T2.iCompleteRoleNum),"
            + "IF(T2.iCompleteTimes IS NULL, '0', T2.iCompleteTimes),"
            + "0," // iCancelRoleNum
            + "0," // iCancelTimes
            + "IF(T1.iAcceptUinNum IS NULL, '0', T1.iAcceptUinNum),"
            + "IF(T2.iCompleteUinNum IS NULL, '0', T2.iCompleteUinNum),"
            + "IF(T2.iTotalTime IS NULL, '0', T2.iTotalTime) FROM T1 FULL JOIN T2 "
            + "ON (T1.iTaskType=T2.iTaskType AND T1.iTaskId=T2.iTaskId AND T1.iTaskLevel=T2.iTaskLevel)";

    public static boolean process(HiveContext sqlContext, DataFrame dfTaskStart,
            DataFrame dfTaskFinished, String date, String iworldid, String url,
            String table) {

        try {
            sqlContext.registerDataFrameAsTable(dfTaskFinished, "TaskFinished");

            // run SQL analysis SQL
            sqlContext.sql(SQL_AcceptTask).registerTempTable("T1");
           
            sqlContext.sql(SQL_FinishedTask).registerTempTable("T2");
            
           
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            String delMysql = "DELETE FROM " + table + " Where dtStatDate='" + date
                    + "' AND iWorldId=" + iworldid;
            int rows = stmt.executeUpdate(delMysql);
            System.out.println(rows + " " + delMysql);
            conn.close();
            DataFrame OssData = sqlContext.sql(String.format(SQL_UNION, date, iworldid));
            
            OssData.insertIntoJDBC(url, table,
                    false);

            // Free Mem
            sqlContext.dropTempTable("T1");
            sqlContext.dropTempTable("T2");
            sqlContext.dropTempTable("TaskStart");
            sqlContext.dropTempTable("TaskFinished");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
