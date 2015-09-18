package com.shadowinlife.app.Tools;

import java.sql.Timestamp;
import java.util.Date;

public class CONSTANT {

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
    
    
}