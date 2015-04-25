package com.shadowinlife.app.LogAnalyse.ProcessTableSQL;

public class CONSTANT {

    public static String tbUser_process_table_sql = "SELECT "
            + "IF(logout_id is null, login_id, logout_id), "
            + "IF(tbLogout_iOnlineTime is null, 0, tbLogout_iOnlineTime),"
            + "IF(tbLogout_iRoleLevel is null,tbLogin_iRoleLevel, tbLogout_iRoleLevel), "
            + "IF(tbLogout_vClientIp is null, tbLogin_vClientIp, tbLogout_vClientIp), "
            + "IF(in_times is null, 0, in_times),"
            + "IF(out_times is null, 0, out_times),"
            + "(CASE WHEN logout_dtEventTime IS NULL THEN login_dtEventTime "
            + "WHEN login_dtEventTime IS NULL THEN logout_dtEventTime "
            + "WHEN logout_dtEventTime>login_dtEventTime THEN logout_dtEventTime "
            + "ELSE login_dtEventTime END) "
            + "FROM tbLogin FULL OUTER JOIN tbLogout ON tbLogin.login_id=tbLogout.logout_id";

    public static String tbpay_process_table_sql = "SELECT iUin,MIN(dtEventTime),iRoleLevel,"
            + "SUM(iMoney),COUNT(1) FROM tbPay GROUP BY iUin";

    public static String tbRolestatus_process_table_sql = "SELECT iUin,iRoleId,iJobId,iGender,"
            + "iRoleLevel,dtRoleCreateTime,dtRoleLastSaveTime,iPoints,iMoney from tbRoleStatus";

    public static String tbRecharge_process_tabble_sql = "select SrcUin,DstUin,dtEventTime,sum(iPayDelta) iPayDelta,count(1)"
            + " from tbRecharge " + " group by DstUin";

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
