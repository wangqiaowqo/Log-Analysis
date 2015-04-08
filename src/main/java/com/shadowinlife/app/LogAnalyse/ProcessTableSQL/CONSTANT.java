package com.shadowinlife.app.LogAnalyse.ProcessTableSQL;

public class CONSTANT {
    public static String tblogin_process_table_sql = "SELECT 'iUin', MIN('dtEventTime') AS dtLoginTime,"
            + "SUM('iOnlineTime') AS iTotallineTime, MAX('iRoleLevel') AS iLevelId,'vClientIp'"
            + "FROM logs GROUP BY 'iUin'";
}
