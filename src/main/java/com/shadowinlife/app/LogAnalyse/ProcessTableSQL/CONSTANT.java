package com.shadowinlife.app.LogAnalyse.ProcessTableSQL;

public class CONSTANT {
    // create daily user act table
    public static String tbUser_process_table_sql = "SELECT "
            + "IF(logout_id is null, login_id, logout_id) AS id, "
            + "IF(tbLogout_iOnlineTime is null, 0, tbLogout_iOnlineTime) AS iOnlineTime,"
            + "IF(tbLogout_iRoleLevel is null,tbLogin_iRoleLevel, tbLogout_iRoleLevel) AS iRoleLevel, "
            + "IF(tbLogout_vClientIp is null, tbLogin_vClientIp, tbLogout_vClientIp) AS ip, "
            + "IF(in_times is null, 0, in_times)," + "IF(out_times is null, 0, out_times),"
            + "(CASE WHEN logout_dtEventTime IS NULL THEN login_dtEventTime "
            + "WHEN login_dtEventTime IS NULL THEN logout_dtEventTime "
            + "WHEN logout_dtEventTime>login_dtEventTime THEN logout_dtEventTime "
            + "ELSE login_dtEventTime END) AS acttime"
            + "FROM tbLogin FULL OUTER JOIN tbLogout ON tbLogin.login_id=tbLogout.logout_id";

    // Add user define function to hive function list
    public static String udf = "CREATE FUNCTION shiftleft AS 'com.shadowinlife.app.UserACT.ShiftLeft' "
            + "USING JAR 'hdfs://10-4-18-185:8020//udf.jar'";

    // USER NOT ACTIVITY
    public static String tbUser_unact_account_table = "INSERT OVERWRITE TABLE test_account "
            + "SELECT unix_timestamp(), iaccount, suin, igameid,"
            + " iworldid, irole, iregtime, ilastacttime,"
            + "shiftleft(idayacti), shiftleft(iweekacti), shiftleft(imonthacti),"
            + " igroup, ilevel, iviplevel, itimes, ionlinetime "
            + " FROM test_account LEFT JOIN loginProcessTable ON test_account.suin = loginProcessTable.id "
            + "WHERE loginProcessTable.id IS NULL";

    // USER ACTIVITY 
    public static String tbUser_act_account_table = "INSERT INTO TABLE test_account "
            + "SELECT unix_timestamp(), 1, loginProcessTable.id, 1, 1, 1, null, "
            + "loginProcessTable.acttime, ShiftAct(test_account.idayacti), ShiftAct(test_account.iweekacti),"
            + "ShiftAct(test_account.imonthacti), null, loginProcessTable.iRoleLevel, "
            + "1, 1, loginProcessTable.iOnlinetime FROM loginProcessTable LEFT JOIN test_account "
            + "ON loginProcessTable.id=test_account.suin";

    /**
     * Convert IP to Long
     * 
     * @param ipAddress
     * @return
     */
    public static long ipToLong(String ipAddress) {
        String[] ipAddressInArray = ipAddress.split("\\.");

        long result = 0;
        for (int i = 3; i >= 0; i--) {
            long ip = Long.parseLong(ipAddressInArray[3 - i]);
            result |= ip << (i * 8);
        }
        return result;
    }

    /**
     * Convert Long to IP
     * 
     * @param ip
     * @return
     */
    public static String longToIp(long ip) {
        return ((ip >> 24) & 0xFF) + "." + ((ip >> 16) & 0xFF) + "." + ((ip >> 8) & 0xFF) + "."
                + (ip & 0xFF);
    }
}
