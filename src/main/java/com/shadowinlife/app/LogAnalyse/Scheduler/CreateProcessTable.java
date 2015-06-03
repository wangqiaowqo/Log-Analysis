package com.shadowinlife.app.LogAnalyse.Scheduler;

import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.hive.HiveContext;

import com.shadowinlife.app.LogAnalyse.ProcessTableSQL.AcountProcessTable;
import com.shadowinlife.app.LogAnalyse.ProcessTableSQL.ChongzhiProcessTable;
import com.shadowinlife.app.LogAnalyse.ProcessTableSQL.MoneyFlowProcessTable;
import com.shadowinlife.app.LogAnalyse.ProcessTableSQL.TaskProcessTable;


public class CreateProcessTable {
    public static void FatTableConstruct(HiveContext sqlContext, String FilePath,
            String tableName, String date, String iworldid) {
        
        switch (tableName) {
        case "RoleLogin":
            // Filter origin file into different RDD
            DataFrame dfLogin = sqlContext.parquetFile(FilePath+"RoleLogin.parquet");
            DataFrame dfLogout = sqlContext.parquetFile(FilePath+"RoleLogout.parquet");
            AcountProcessTable.process(sqlContext, dfLogin, dfLogout, date, iworldid);
            break;

        case "ChongZhi":
            DataFrame dfChongZhi = sqlContext.parquetFile(FilePath+"ChongZhi.parquet");
            ChongzhiProcessTable.process(sqlContext, dfChongZhi, date, iworldid);
            break;

        case "MoneyFlow":
            DataFrame dfMoneyFlow = sqlContext.parquetFile(FilePath+"MoneyFlow.parquet");
            MoneyFlowProcessTable.process(sqlContext, dfMoneyFlow, date, iworldid);
            break;
        case "Task":
            DataFrame dfTaskStart = sqlContext.parquetFile(FilePath+"TaskStart.parquet");
            DataFrame dfTaskFinished = sqlContext.parquetFile(FilePath+"TaskFinished.parquet");
            TaskProcessTable.process(sqlContext, dfTaskStart, dfTaskFinished, date, iworldid,
                    "jdbc:mysql://10-4-28-24:3306/dbDJOssResult?user=oss&password=oss",
                    "oss_dm_tbTask");
            break;
        }
    }

    public static void FatTableWithoutFile(HiveContext sqlContext, String tableName, String date,
            String iworldid) {
        switch (tableName) {
        case "RoleLogin":
            AcountProcessTable.ModifyProcessTableWithoutLogFile(sqlContext, date, iworldid);
            break;

        case "ChongZhi":
            ChongzhiProcessTable.ModifyProcessTableWithoutLogFile(sqlContext, date, iworldid);
            break;

        case "MoneyFlow":
            MoneyFlowProcessTable.ModifyProcessTableWithoutLogFile(sqlContext, date, iworldid);
            break;

        }
    }

}
