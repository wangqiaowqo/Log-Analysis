package com.shadowinlife.app.LogAnalyse.ProcessTableSQL;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.DataFrame;
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
            + "T1.iTaskType,"
            + "T1.iTaskId,"
            + "T1.iTaskLevel,"
            + "T1.iAcceptRoleNum,"
            + "T1.iAcceptUinNum,"
            + "T2.iCompleteRoleNum,"
            + "T2.iCompleteTimes,"
            + "0," // iCancelRoleNum
            + "0," // iCancelTimes
            + "T1.iAcceptUinNum,"
            + "T2.iCompleteUinNum,"
            + "T2.iTotalTime FROM T1 JOIN T2 "
            + "ON (T1.iTaskType=T2.iTaskType AND T1.iTaskId=T2.iTaskId AND T1.iTaskLevel=T2.iTaskLevel)";

    public static boolean process(HiveContext sqlContext, JavaRDD<String[]> rddTaskStart,
            JavaRDD<String[]> rddTaskFinished, String date, String iworldid, String url,
            String table) {

        try {

            // Create RDD from login FILES
            JavaRDD<TaskStart> tbTaskStart = rddTaskStart.map(new Function<String[], TaskStart>() {

                private static final long serialVersionUID = -8639862064479870422L;

                @Override
                public TaskStart call(String[] line) {

                    return TaskStart.parseFromLogFile(line);

                }

            });

            // Create RDD from logout FILES
            JavaRDD<TaskFinished> tbTaskFinished = rddTaskFinished
                    .map(new Function<String[], TaskFinished>() {

                        private static final long serialVersionUID = 9145640452810492525L;

                        @Override
                        public TaskFinished call(String[] line) {

                            return TaskFinished.parseFromLogFile(line);

                        }

                    });

            // Convert all the values into the spark table
            DataFrame dfTaskStart = sqlContext.createDataFrame(tbTaskStart, TaskStart.class);
            sqlContext.registerDataFrameAsTable(dfTaskStart, "TaskStart");
            DataFrame dfTaskFinished = sqlContext.createDataFrame(tbTaskFinished,
                    TaskFinished.class);
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
            sqlContext.sql(String.format(SQL_UNION, date, iworldid)).insertIntoJDBC(url, table,
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
