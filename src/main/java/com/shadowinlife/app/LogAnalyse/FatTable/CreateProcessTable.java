package com.shadowinlife.app.LogAnalyse.FatTable;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.hive.HiveContext;

import com.shadowinlife.app.LogAnalyse.Action.ReadParquetToDF;
import com.shadowinlife.app.LogAnalyse.ProcessTableSQL.AcountProcessTable;
import com.shadowinlife.app.LogAnalyse.ProcessTableSQL.ChongzhiProcessTable;
import com.shadowinlife.app.LogAnalyse.ProcessTableSQL.MoneyFlowProcessTable;
import com.shadowinlife.app.LogAnalyse.ProcessTableSQL.ProceeMoneyStorage;
import com.shadowinlife.app.LogAnalyse.ProcessTableSQL.TaskProcessTable;


public class CreateProcessTable {
    public static void FatTableConstruct(HiveContext sqlContext, String FilePath,
            String tableName, String date, String iworldid) {
        
        String BeginTime = date + " 00:00:00";
        Timestamp bTime = Timestamp.valueOf(BeginTime);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(bTime.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        String EndTime = format.format(calendar.getTime()) + " 02:00:00";
        String[] AccountType = {"1"};
        String[] GameId = {"1"};
        String[] WorldId = {iworldid};
        switch (tableName) {
        case "RoleLogin":
            // Filter origin file into different RDD
            ReadParquetToDF.ReadParquet(sqlContext, BeginTime, EndTime, GameId, AccountType, WorldId, "RoleLogin");
            ReadParquetToDF.ReadParquet(sqlContext, BeginTime, EndTime, GameId, AccountType, WorldId, "RoleLogout");
            AcountProcessTable.process(sqlContext, date, iworldid);
            break;

        case "ChongZhi":
            ReadParquetToDF.ReadParquet(sqlContext, BeginTime, EndTime, GameId, AccountType, WorldId, "ChongZhi");
            ChongzhiProcessTable.process(sqlContext, date, iworldid);
            break;

        case "MoneyFlow":
            ReadParquetToDF.ReadParquet(sqlContext, BeginTime, EndTime, GameId, AccountType, WorldId, "MoneyFlow");
            MoneyFlowProcessTable.process(sqlContext, date, iworldid);
            break;
        case "Task":
            ReadParquetToDF.ReadParquet(sqlContext, BeginTime, EndTime, GameId, AccountType, WorldId, "TaskStart");
            ReadParquetToDF.ReadParquet(sqlContext, BeginTime, EndTime, GameId, AccountType, WorldId, "TaskFinished");
            TaskProcessTable.process(sqlContext, date, iworldid,
                    "jdbc:mysql://10-4-28-24:3306/dbDJOssResult?user=oss&password=oss",
                    "oss_dm_tbTask");
            break;
        case "MoneyStorage":
            ReadParquetToDF.ReadParquet(sqlContext, BeginTime, EndTime, GameId, AccountType, WorldId, "RoleStatus");
            ProceeMoneyStorage.process(sqlContext, date, iworldid, 
                    "jdbc:mysql://10-4-28-24:3306/dbDJOssResult?user=oss&password=oss", "oss_dm_tbMoneyStorage");
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
