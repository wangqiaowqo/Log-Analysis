package com.shadowinlife.app.LogAnalyse.ProcessTableSQL;

public class CONSTANT {
    // create daily user act table
    public static String tbUser_process_table_sql = "SELECT "
            + "IF(logout_id is null, login_id, logout_id) AS id, "
            + "IF(tbLogout_iOnlineTime is null, 0, tbLogout_iOnlineTime) AS iOnlineTime,"
            + "IF(tbLogout_iRoleLevel is null,tbLogin_iRoleLevel, tbLogout_iRoleLevel) AS iRoleLevel, "
            + "IF(tbLogout_vClientIp is null, tbLogin_vClientIp, tbLogout_vClientIp) AS ip, "
            + "IF(in_times is null, 0, in_times) AS intime," 
            + "IF(out_times is null, 0, out_times) AS outtime,"
            + "(CASE WHEN logout_dtEventTime IS NULL THEN login_dtEventTime "
            + "WHEN login_dtEventTime IS NULL THEN logout_dtEventTime "
            + "WHEN logout_dtEventTime>login_dtEventTime THEN logout_dtEventTime "
            + "ELSE login_dtEventTime END) AS acttime "
            + "FROM tbLogin FULL OUTER JOIN tbLogout ON tbLogin.login_id=tbLogout.logout_id";

    // Add user define function to hive function list
    public static String shiftleft = "CREATE FUNCTION  shiftleft "
            + "AS 'com.shadowinlife.app.UserACT.ShiftLeft'";
    public static String shiftact = "CREATE FUNCTION  shiftact "
            + "AS 'com.shadowinlife.app.UserACT.ShiftAct'";
    public static String usercomeback = "CREATE FUNCTION  usercomeback "
            + "AS 'com.shadowinlife.app.UserACT.UserComeBack'";
    public static String userlost = "CREATE FUNCTION  userlost "
            + "AS 'com.shadowinlife.app.UserACT.UserLost'";

    // USER NOT ACTIVITY
    public static String tbUser_unact_account_table = "INSERT INTO TABLE fat_tbaccount "
            + "SELECT '%s', "
            + "iaccounttype,"
            + "suin,"
            + "igameid,"
            + "iworld, "
            + "iroleid,"
            + "iregtime,"
            + "ilastacttime,"
            + "shiftleft(idayacti),"
            + "shiftleft(iweekacti),"
            + "shiftleft(imonthacti),"
            + " igroup,"
            + "ilevel,"
            + "iviplevel,"
            + "itimes,"
            + "loginProcessTable.ionlinetime "
            + "FROM fat_tbaccount LEFT JOIN loginProcessTable ON fat_tbaccount.suin = loginProcessTable.id "
            + "WHERE loginProcessTable.id IS NULL";

    // USER ACTIVITY 
    public static String tbUser_act_account_table = "INSERT INTO TABLE fat_tbaccount "
            + "SELECT '%s',"
            + "1,"
            + "loginProcessTable.id,"
            + "1, 1, 1, null, "
            + "loginProcessTable.acttime,"
            + "shiftact(fat_tbaccount.idayacti),"
            + "shiftact(fat_tbaccount.iweekacti),"
            + "shiftact(fat_tbaccount.imonthacti), null, loginProcessTable.iRoleLevel, "
            + "1,"
            + "IF(outtime is null, intime, outtime),"
            + "loginProcessTable.iOnlinetime FROM loginProcessTable LEFT JOIN fat_tbaccount "
            + "ON loginProcessTable.id=fat_tbaccount.suin";
    
    
    
    
    
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
