package com.shadowinlife.app.LogAnalyse.ProcessTableSQL;

public class CONSTANT {
   
    public static String tblogin_process_table_sql = "SELECT iUin,MIN(dtEventTime),SUM(iOnlineTime),MAX(iRoleLevel),MAX(vClientIp) FROM tbLogin GROUP BY iUin";

    public static String tbpay_process_table_sql = "SELECT iUin,MIN(dtEventTime),iRoleLevel,SUM(iMoney),COUNT(1) FROM tbPay GROUP BY iUin";

    public static String tbRolestatus_process_table_sql="SELECT iUin,iRoleId,iJobId,iGender,iRoleLevel,dtRoleCreateTime,dtRoleLastSaveTime,iPoints,iMoney from tbRoleStatus";
    
    public static String tbRecharge_process_tabble_sql="select SrcUin,DstUin,dtEventTime,sum(iPayDelta) iPayDelta,count(1)"
            +" from tbRecharge "
            +" group by DstUin";
    /**
     * Convert IP to Long
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
     * @param ip
     * @return
     */
    public static String longToIp(long ip) {
        return ((ip >> 24) & 0xFF) + "." + ((ip >> 16) & 0xFF) + "." + ((ip >> 8) & 0xFF) + "."
                + (ip & 0xFF);
    }
}
