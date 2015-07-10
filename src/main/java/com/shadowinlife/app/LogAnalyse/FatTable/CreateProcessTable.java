package com.shadowinlife.app.LogAnalyse.FatTable;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.spark.sql.hive.HiveContext;

import com.shadowinlife.app.LogAnalyse.Action.ReadParquetToDF;
import com.shadowinlife.app.LogAnalyse.ProcessTableSQL.AcountProcessTable;
import com.shadowinlife.app.LogAnalyse.ProcessTableSQL.ChongzhiProcessTable;
import com.shadowinlife.app.LogAnalyse.ProcessTableSQL.MoneyFlowProcessTable;
import com.shadowinlife.app.LogAnalyse.ProcessTableSQL.RoleOfAcountProcessTable;
import com.shadowinlife.app.LogAnalyse.ProcessTableSQL.RoleOfChongzhiProcessTable;
import com.shadowinlife.app.LogAnalyse.ProcessTableSQL.RoleOfMoneyFlowProcessTable;


public class CreateProcessTable {
    public static void FatTableConstruct(HiveContext sqlContext, String FilePath, String tableName,
            String date, String iworldid) {

        String BeginTime = date + " 00:00:00";
        Timestamp bTime = Timestamp.valueOf(BeginTime);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(bTime.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        String EndTime = format.format(calendar.getTime()) + " 02:00:00";
        String strWhere = "SELECT * FROM temp WHERE `dtEventTime`>='" + BeginTime
                + "' AND `dtEventTime`<'" + format.format(calendar.getTime()) + "'";
        String[] AccountType = { "1" };
        String[] GameId = { "1" };
        String[] WorldId = { iworldid };
        ReadParquetToDF rptd = new ReadParquetToDF();
        switch (tableName) {
        case "RoleLogin":
            // Filter origin file into different RDD
            rptd.ReadParquet(sqlContext, BeginTime, EndTime, GameId, AccountType,
                    WorldId, "RoleLogin", strWhere);
            rptd.ReadParquet(sqlContext, BeginTime, EndTime, GameId, AccountType,
                    WorldId, "RoleLogout", strWhere);
            AcountProcessTable.process(sqlContext, date, iworldid);
            RoleOfAcountProcessTable.process(sqlContext, date, iworldid);
            break;

        case "Deposit":
            rptd.ReadParquet(sqlContext, BeginTime, EndTime, GameId, AccountType,
                    WorldId, "Deposit", strWhere);
            ChongzhiProcessTable.process(sqlContext, date, iworldid);
            RoleOfChongzhiProcessTable.process(sqlContext, date, iworldid);
            break;

        case "MoneyFlow":
            rptd.ReadParquet(sqlContext, BeginTime, EndTime, GameId, AccountType,
                    WorldId, "MoneyFlow", strWhere);
            MoneyFlowProcessTable.process(sqlContext, date, iworldid);
            RoleOfMoneyFlowProcessTable.process(sqlContext, date, iworldid);
            break;
        
        }
    }

    public static void FatTableWithoutFile(HiveContext sqlContext, String tableName, String date,
            String iworldid) {
        switch (tableName) {
        case "RoleLogin":
            AcountProcessTable.ModifyProcessTableWithoutLogFile(sqlContext, date, iworldid);
            RoleOfAcountProcessTable.ModifyProcessTableWithoutLogFile(sqlContext, date, iworldid);
            break;

        case "Deposit":
            ChongzhiProcessTable.ModifyProcessTableWithoutLogFile(sqlContext, date, iworldid);
            RoleOfChongzhiProcessTable.ModifyProcessTableWithoutLogFile(sqlContext, date, iworldid);
            break;

        case "MoneyFlow":
            MoneyFlowProcessTable.ModifyProcessTableWithoutLogFile(sqlContext, date, iworldid);
            RoleOfMoneyFlowProcessTable.ModifyProcessTableWithoutLogFile(sqlContext, date, iworldid);
            break;

        }
    }

}
