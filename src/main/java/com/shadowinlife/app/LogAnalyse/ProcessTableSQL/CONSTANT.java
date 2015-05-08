package com.shadowinlife.app.LogAnalyse.ProcessTableSQL;

import java.sql.Timestamp;
import java.util.Date;

public class CONSTANT {

    // Add user define function to hive function list
    public static String shiftleft = "CREATE FUNCTION  shiftleft AS 'com.shadowinlife.app.UserACT.ShiftLeft'";
    public static String shiftact = "CREATE FUNCTION  shiftact "
            + "AS 'com.shadowinlife.app.UserACT.ShiftAct'";
    public static String usercomeback = "CREATE FUNCTION  usercomeback "
            + "AS 'com.shadowinlife.app.UserACT.UserComeBack'";
    public static String userlost = "CREATE FUNCTION  userlost "
            + "AS 'com.shadowinlife.app.UserACT.UserLost'";
    
    public static Long date2Long(String date) {
        Long result = 0l;
        if (date != null) {
            try { 
                Timestamp tmp = Timestamp.valueOf(date +" 00:00:00"); //Translate string to a date
                result = tmp.getTime()/(24l*3600l*1000l)+1; // day count: date-"1970-01-01"
            } catch (Exception e) { 
               return result;
            }
        }
        return result;
    }
    
    public static Long date2Long(Date date) {
        Long result = 0l;
        if (date != null) {
            try { 
                result = date.getTime()/(24l*3600l*1000l)+1;
            } catch (Exception e) { 
               return result;
            }
        }
        return result;
    }
    
    
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
