package com.shadowinlife.app.OssTableSQL;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.spark.sql.hive.HiveContext;

import com.shadowinlife.app.Tools.CONSTANT;


public class UserAccountAnalysis {
    /* 
    osss_tbRegisterUser
    +---------------+------------+----------+--+
    |   col_name    | data_type  | comment  |
    +---------------+------------+----------+--+
    | dtstatdate    | date       |          |
    | iperiod       | bigint     |          |
    | igameid       | int        |          |
    | iaccounttype  | int        |          |
    | iworld        | int        |          |
    | iregnum       | bigint     |          |
    | iactivity     | bigint     |          |
    | ilostnum      | bigint     |          |
    | ibacknum      | bigint     |          |
    | itimes        | bigint     |          |
    | ionlinetime   | bigint     |          |
    +---------------+------------+----------+--+
     */
    private static String tbRegisterUser = "INSERT OVERWRITE TABLE oss_dm_%s_tbRegisterUser "
            + "PARTITION(index_dtstatdate=%s,index_iperiod='%s') "
            + "SELECT '%s'," //date 
            + "%s,"  //period
            + "IF(T1.index_igameid IS NULL,-1,T1.index_igameid),"
            + "IF(T1.index_iaccounttype IS NULL,-1,T1.index_iaccounttype),"
            + "IF(T1.index_iworldid is null,-1, T1.index_iworldid),"
            + "IF(T2.oldcount is null,T1.newcount,T1.newcount-T2.oldcount),"
            + "T1.useractivitynum,"
            + "T1.userlostnum,"
            + "T1.usercomebacknum,"
            + "T1.itimes,"
            + "T1.ionlinetime "
            
            + "From " //T1 BEGIN
            + "(SELECT IF(index_igameid IS NULL,-1,index_igameid) AS index_igameid,"
            + "IF(index_iaccounttype IS NULL,-1,index_iaccounttype) as index_iaccounttype,"
            + "IF(index_iworldid is null,-1, index_iworldid) as index_iworldid,"
            + "count(DISTINCT suin) AS newcount, "
            + "sum(useractivity(%s, %s)) AS useractivitynum,"
            + "sum(userlost(%s, %s)) AS userlostnum,"
            + "sum(usercomeback(%s,%s)) AS usercomebacknum,"
            + "sum(itimes) AS itimes,"
            + "sum(ionlinetime) AS ionlinetime "
            + "FROM fat_%s_user WHERE index_dtstatdate=DATE2LONG('%s') "
            + "group by index_igameid,index_iaccounttype,index_iworldid with cube) T1  "
            
            + "LEFT JOIN (SELECT IF(index_igameid IS NULL,-1,index_igameid) AS index_igameid,"
            + "IF(index_iaccounttype IS NULL,-1,index_iaccounttype) AS index_iaccounttype,"
            + "IF(index_iworldid is null,-1, index_iworldid) AS index_iworldid,"
            + "COUNT(DISTINCT suin) AS oldcount "
            + "FROM fat_%s_user WHERE index_dtstatdate=(DATE2LONG('%s')-%s) "
            + "GROUP BY index_igameid,index_iaccounttype,index_iworldid with cube) T2 "
            
            + "ON T1.index_igameid=T2.index_igameid AND T1.index_iaccounttype=T2.index_iaccounttype "
            + "AND T1.index_iworldid=T2.index_iworldid";
    
    private static String tbRegisterUser_TotalRegUser = "INSERT OVERWRITE TABLE oss_dm_%s_tbRegisterUser "
            + "PARTITION(index_dtstatdate=%s,index_iperiod='%s') "
    		+ "SELECT T1.dtStatDate,T1.iPeriod,T1.iGameId,T1.iAccountType,T1.iWorldId,T1.iRegNum,T1.iActivityNum,T1.iLostNum,"
    		+ "T1.iBackNum,if(T2.iTimes is null,T1.iTimes,T1.iTimes+T2.iTimes),if(T2.iOnlineTime is null,T1.iOnlineTime,T1.iOnlineTime+T2.iOnlineTime) "
    		+ "FROM "
    		
            + "(SELECT '%s' AS dtStatDate," //date 
            + "%s AS iPeriod,"  //period
            + "IF(index_igameid IS NULL,-1,index_igameid) AS iGameId,"
            + "IF(index_iaccounttype IS NULL,-1,index_iaccounttype) as iAccountType,"
            + "IF(index_iworldid is null,-1, index_iworldid) as iWorldId,"
            + "count(DISTINCT suin) AS iRegNum, "
            + "0 AS iActivityNum,"
            + "0 AS iLostNum,"
            + "0 AS iBackNum,"
            + "sum(itimes) AS iTimes,"
            + "sum(ionlinetime) AS iOnlineTime "
            + "FROM fat_%s_user WHERE index_dtstatdate=DATE2LONG('%s') " //tablename  date
            + "group by index_igameid,index_iaccounttype,index_iworldid with cube) T1 "
            + "LEFT JOIN "
            + "(select igameid,iaccounttype,iworld,itimes AS iTimes,ionlinetime AS iOnlineTime "
            + "from oss_dm_%s_tbregisteruser "
            + "where index_dtstatdate=DATE2LONG(date_add('%s',-1)) and index_iperiod = %s) T2 "
            
            + "ON T1.iGameId = T2.igameid and T1.iAccountType = T2.iaccounttype and T1.iWorldId = T2.iworld";
    
    /*
     * tbRegisterUserTypeDis
    +---------------+------------+----------+--+
    |   col_name    | data_type  | comment  |
    +---------------+------------+----------+--+
    | dtstatdate    | date       |          |
    | stype         | string     |          |
    | iperiod       | bigint     |          |
    | igameid       | int        |          |
    | iaccounttype  | int        |          |
    | iworld        | int        |          |
    | stypevalue    | string     |          |
    | iregnum       | bigint     |          |
    +---------------+------------+----------+--+
     */
    private static String tbRegisterUserTypeDis = "INSERT OVERWRITE TABLE oss_dm_%s_tbRegisterUserTypeDis "
            + "PARTITION(index_dtstatdate=%s,index_iperiod='%s') "
            + "SELECT '%s'," //date
            + "'Level'," //sType:level
            + "'%s'," //iPeroid
            + "if(index_igameid is null,-1,index_igameid),"
            + "if(index_iaccounttype is null,-1,index_iaccounttype),"
            + "if(index_iworldid is null,-1,index_iworldid),"
            + "if(maxlevel is null,-1,maxlevel)," //sTypeValue : levelvalue
            + "count(*) "
            + "from "
            
            + "(select "
            + "index_iaccounttype,"
            + "index_igameid,"
            + "index_iworldid,"
            + "suin,"
            + "max(ilevel) maxlevel "
            + "from "
            + "fat_%s_user "
            + "WHERE "
            + "index_dtstatdate=DATE2LONG('%s') "
            + "%s " //"and iregtime >= '%s' and iregtime < date_add('%s',1) "
            + "group by index_iaccounttype,index_igameid,index_iworldid,suin) t "
            
            + "group by index_igameid,index_iaccounttype,index_iworldid,maxlevel with cube";
   
    /*
     * tbDayNewRegTypeDis
     +---------------+------------+----------+--+
     |   col_name    | data_type  | comment  |
     +---------------+------------+----------+--+
     | dtstatdate    | date       |          |
     | stype         | string     |          |
     | igameid       | int        |          |
     | iaccounttype  | int        |          |
     | iworld        | int        |          |
     | stypevalue    | string     |          |
     | iregnum       | bigint     |          |
     +---------------+------------+----------+--+
     */
    private static String tbDayNewRegTypeDis = "INSERT OVERWRITE TABLE oss_dm_%s_tbDayNewRegTypeDis PARTITION(index_dtstatdate=%s) "
	    + "SELECT '%s'," //date
            + "'%s'," //sType:ActivityTimesDis
            + "if(index_igameid is null,-1,index_igameid),"
            + "if(index_iaccounttype is null,-1,index_iaccounttype),"
            + "if(index_iworldid is null,-1,index_iworldid),"
            + "if(totaltimes is null,-1,totaltimes)," //sTypeValue : ActivityTimesDis value
            + "count(*),"
            + "DATE2LONG('%s') "
            + "from "
            
            + "(select "
            + "index_iaccounttype,"
            + "index_igameid,"
            + "index_iworldid,"
            + "suin,"
            + "sum(itimes) totaltimes "
            + "from "
            + "fat_%s_user "
            + "WHERE "
            + "index_dtstatdate=DATE2LONG('%s') "
            + "%s " //"and iregtime >= '%s' and iregtime < date_add('%s',1) "
            + "group by index_iaccounttype,index_igameid,index_iworldid,suin) t "
            
            + "group by index_igameid,index_iaccounttype,index_iworldid,totaltimes with cube";
    /*
     * tbUserActivityTypeDis
     +---------------+------------+----------+--+
     |   col_name    | data_type  | comment  |
     +---------------+------------+----------+--+
     | dtstatdate    | date       |          |
     | stype         | string     |          |
     | stypevalue    | string     |          |
     | iperiod       | bigint     |          |
     | igameid       | int        |          |
     | iaccounttype  | int        |          |
     | iworld        | int        |          |
     | iregnum       | bigint     |          |
     | iactivity     | bigint     |          |
     | ilostnum      | bigint     |          |
     | ibacknum      | bigint     |          |
     +---------------+------------+----------+--+
     */
    private static String tbUserActivityTypeDis = "INSERT OVERWRITE TABLE oss_dm_%s_tbUserActivityTypeDis "
            + "PARTITION(index_dtstatdate=%s,index_iperiod='%s') "
            + "SELECT '%s'," //date
            + "'%s'," //sType:level
            + "if(maxlevel is null,-1,maxlevel)," //sTypeValue : levelvalue
            + "'%s'," //iPeroid
            + "if(index_igameid is null,-1,index_igameid),"
            + "if(index_iaccounttype is null,-1,index_iaccounttype),"
            + "if(index_iworldid is null,-1,index_iworldid),"
            + "sum(useractivity(%s, 1))," //period
            + "sum(userlost(%s, 1))," //period
            + "sum(usercomeback(%s, 1))," //period
            + "DATE2LONG('%s') "
            + "from "
            
            + "(select index_iaccounttype,"
            + "index_igameid,"
            + "index_iworldid,"
            + "suin,"
            + "max(ilevel) maxlevel,"
            + "groupuseracti(idayacti) AS idayacti,"
            + "groupuseracti(iweekacti) AS iweekacti,"
            + "groupuseracti(imonthacti) AS imonthacti "
            + "from fat_%s_user "
            + "WHERE index_dtstatdate=DATE2LONG('%s') and ilastacttime >= '%s' "
            + "group by index_iaccounttype,index_igameid,index_iworldid,suin) t "

            + "group by index_igameid,index_iaccounttype,index_iworldid,maxlevel with cube";
    
    /*
     * tbDayUserActivityTypeDis
     +------------------+------------+----------+--+
     |     col_name     | data_type  | comment  |
     +------------------+------------+----------+--+
     | dtstatdate       | date       |          |
     | stype            | string     |          |
     | igameid          | int        |          |
     | iaccounttype     | int        |          |
     | iworld           | int        |          |
     | stypevalue       | string     |          |
     | idayactivitynum  | bigint     |          |
     +------------------+------------+----------+--+
     */
    private static String tbDayUserActivityTypeDis_ActivityTimesDis = "INSERT OVERWRITE TABLE oss_dm_%s_tbDayUserActivityTypeDis PARTITION(index_dtstatdate=%s,index_sType='ActivityTimesDis') "
            + "SELECT '%s'," //date
            + "'%s'," //sType:ActivityTimesDis
            + "if(index_igameid is null,-1,index_igameid),"
            + "if(index_iaccounttype is null,-1,index_iaccounttype),"
            + "if(index_iworldid is null,-1,index_iworldid),"
            + "IF(itimesdis is null,-1,itimesdis)," //sTypeValue : ActivityTimesDis value
            + "count(*) " 
            //+ "DATE2LONG('%s') "
            + "from "
            
            + "(select "
            + "index_iaccounttype,"
            + "index_igameid,"
            + "index_iworldid,"
            + "suin,"
            + "(case when sum(itimes) < 100 then sum(itimes) else floor(sum(itimes)/100)*100 END) itimesdis "
            + "from fat_%s_user "
            + "WHERE "
            + "index_dtstatdate=DATE2LONG('%s') and useractivity(idayacti,1) = 1 "
            + "group by index_iaccounttype,index_igameid,index_iworldid,suin) t "
            
            + "group by index_igameid,index_iaccounttype,index_iworldid,itimesdis with cube";
    
    private static String tbDayUserActivityTypeDis_ActivityTimeDis = "INSERT OVERWRITE TABLE oss_dm_%s_tbDayUserActivityTypeDis PARTITION(index_dtstatdate=%s,index_sType='ActivityTimeDis') "
            + "SELECT '%s'," //date
            + "'%s'," //sType:ActivityTimeDis
            + "if(index_igameid is null,-1,index_igameid),"
            + "if(index_iaccounttype is null,-1,index_iaccounttype),"
            + "if(index_iworldid is null,-1,index_iworldid),"
            + "IF(ionlinetimedis is null,-1,ionlinetimedis),"//"IF(ionlinetime is null,-1,floor(ionlinetime/300)*5) ionlinetimedis," //sTypeValue : ActivityTimeDis value
            + "count(*) " 
            //+ "DATE2LONG('%s') "
            + "from "
            
            + "(select "
            + "index_iaccounttype,"
            + "index_igameid,"
            + "index_iworldid,"
            + "suin,"
            + "%s "
            + "from fat_%s_user "
            + "WHERE "
            + "index_dtstatdate=DATE2LONG('%s') and useractivity(idayacti,1) = 1 "
            + "group by index_iaccounttype,index_igameid,index_iworldid,suin) t "
            
            + "group by index_igameid,index_iaccounttype,index_iworldid,ionlinetimedis with cube";
    /*
     * tbActivityScaleDis
     +---------------+------------+----------+--+
     |   col_name    | data_type  | comment  |
     +---------------+------------+----------+--+
     | dtstatdate    | date       |          |
     | ssourceuser   | string     |          |
     | iperiod       | bigint     |          |
     | igameid       | int        |          |
     | iaccounttype  | int        |          |
     | iworld        | int        |          |
     | ilevel        | bigint     |          |
     | iactivityday  | bigint     |          |
     | iactivitynum  | bigint     |          |
     +---------------+------------+----------+--+

     */
    
    private static String tbActivityScaleDis = "INSERT OVERWRITE TABLE oss_dm_%s_tbActivityScaleDis "
            + "PARTITION(index_dtstatdate=%s,index_iperiod='%s',index_ssourceuser='%s') "
            + "SELECT '%s'," //date
            + "'%s'," //ssourceuser : DAY WEEK MONTH DOUBLE WEEK
            + "'%s'," //iPeroid
            + "index_igameid,"
            + "index_iaccounttype,"
            + "index_iworldid,"
            + "if(maxlevel is null,-1,maxlevel) as maxlevel,"
            + "if(iactiDays is null,-1,iactiDays) as iactiDays,"
            + "count(*),"
            + "DATE2LONG('%s') "
            + "from "
            
            + "(select index_iaccounttype,"
            + "index_igameid,"
            + "index_iworldid,"
            + "maxlevel,"
            + "onecount(idayacti,%s) AS iactiDays "
            + "from (select if(index_iaccounttype is null,-1,index_iaccounttype) as index_iaccounttype,"
            + "if(index_igameid is null,-1,index_igameid) as index_igameid,"
            + "if(index_iworldid is null,-1,index_iworldid) as index_iworldid,"
            + "suin,max(ilevel) maxlevel,groupuseracti(idayacti) AS idayacti "
            + "from fat_%s_user "
            + "WHERE index_dtstatdate=DATE2LONG('%s') %s "//and useractivity(%s,1) = 1
            + "group by index_iaccounttype,index_igameid,index_iworldid,suin "
            + "grouping sets((index_iaccounttype,index_igameid,index_iworldid,suin),"
            + "(index_igameid,index_iworldid,suin),"
            + "(index_iaccounttype,index_iworldid,suin),"
            + "(index_iaccounttype,index_igameid,suin),"
            + "(index_iaccounttype,suin),"
            + "(index_igameid,suin),"
            + "(index_iworldid,suin),"
            + "(suin))) t "
            + ") t1 "
            
            + "group by index_iaccounttype,index_igameid,index_iworldid,maxlevel,iactiDays "
            + "grouping sets((index_iaccounttype,index_igameid,index_iworldid),"
            + "(index_iaccounttype,index_igameid,index_iworldid,maxlevel,iactiDays),"
            + "(index_iaccounttype,index_igameid,index_iworldid,maxlevel),"
            + "(index_iaccounttype,index_igameid,index_iworldid,iactiDays))";
    /*
     +---------------+------------+----------+--+
     |   col_name    | data_type  | comment  |
     +---------------+------------+----------+--+
     | dtstatdate    | date       |          |
     | ssourceuser   | string     |          |
     | iperiod       | bigint     |          |
     | igameid       | int        |          |
     | iaccounttype  | int        |          |
     | iworld        | int        |          |
     | ilevel        | bigint     |          |
     | ilookday      | bigint     |          |
     | iactivitynum  | bigint     |          |
     +---------------+------------+----------+--+

     */
    private static String tbStayScaleDis = "INSERT OVERWRITE TABLE oss_dm_%s_tbStayScaleDis "
            + "PARTITION(index_dtstatdate, index_iperiod, index_ssourceuser, index_ilookperiod) "
            + "SELECT '%s'," //date
            + "'%s'," //ssourceuser : DAYACTI  DAYREGISTER
            + "'%s'," //iPeroid
            + "index_igameid,"
            + "index_iaccounttype,"
            + "index_iworldid,"
            + "if(maxLevel is null,-1,maxLevel)," 
            + "if(iPeriodNum is null,-1,iPeriodNum)," //TODO  Look period
            + "count(*),"
            
            + "DATE2LONG('%s'),'%s','%s',if(iPeriodNum is null,-1,iPeriodNum) "
            + "from "
            
            + "(select if(index_iaccounttype is null,-1,index_iaccounttype) as index_iaccounttype,"
            + "if(index_igameid is null,-1,index_igameid) as index_igameid,"
            + "if(index_iworldid is null,-1,index_iworldid) as index_iworldid,"
            + "suin,"
            + "%s,"//datediff('%s',to_date(iregtime)) iPeriodNum
            + "max(ilevel) maxLevel "
            + "from fat_%s_user "
            + "WHERE index_dtstatdate=DATE2LONG('%s') "
            + "%s "//and useractivity(idayacti,1) = 1 and iregtime >= date_add('%s',-90)
            + "group by index_iaccounttype,index_igameid,index_iworldid,suin,iregtime "
            + "grouping sets((index_iaccounttype,index_igameid,index_iworldid,suin,iregtime),"
            + "(index_igameid,index_iworldid,suin,iregtime),(index_iaccounttype,index_iworldid,suin,iregtime),"
            + "(index_iaccounttype,index_igameid,suin,iregtime),(index_iaccounttype,suin,iregtime),"
            + "(index_igameid,suin,iregtime),(index_iworldid,suin,iregtime),(suin,iregtime))) t "
            
            + "group by index_iaccounttype,index_igameid,index_iworldid,maxLevel,iPeriodNum "
            + "grouping sets((index_iaccounttype,index_igameid,index_iworldid,maxLevel,iPeriodNum),"
            + "(index_iaccounttype,index_igameid,index_iworldid,iPeriodNum))";
    
    private static String tbStayScaleDis_DayActi = "INSERT OVERWRITE TABLE oss_dm_%s_tbStayScaleDis "
            + "PARTITION(index_dtstatdate, index_iperiod, index_ssourceuser, index_ilookperiod) "
            + "SELECT '%s'," //date
            + "'%s'," //ssourceuser : DAYACTI  DAYREGISTER
            + "'%s'," //iPeroid
            + "t1.igameid,"
            + "t1.iaccounttype,"
            + "t1.iworldid,"
            + "t2.ilevel," 
            + "datediff(t2.dtstatdate,t1.dtstatdate)," //TODO  Look period
            + "count(*),"
            
            + "DATE2LONG('%s'),'%s','%s',datediff(t2.dtstatdate,t1.dtstatdate) "
            + "from "
            
            + "(select dtstatdate,"
            + "if(iaccounttype is null,-1,iaccounttype) AS iaccounttype,"
            + "if(igameid is null,-1,igameid) AS igameid,"
            + "if(iworldid is null,-1,iworldid) AS iworldid,"
            + "suin "
            + "from fat_%s_user "
            + "where index_dtstatdate <= date2long('%s') and index_dtstatdate >= (date2long('%s') - 90) and useractivity(idayacti,1) = 1 "
            + "group by dtstatdate,iaccounttype,igameid,iworldid,suin "
            + "grouping sets((dtstatdate,iaccounttype,igameid,iworldid,suin),"
            + "(dtstatdate,iaccounttype,igameid,suin),"
            + "(dtstatdate,iaccounttype,iworldid,suin),"
            + "(dtstatdate,igameid,iworldid,suin),"
            + "(dtstatdate,igameid,suin),"
            + "(dtstatdate,iaccounttype,suin),"
            + "(dtstatdate,iworldid,suin),"
            + "(dtstatdate,suin))) t1  "
            + "left join "
            + "(select dtstatdate,"
            + "if(iaccounttype is null,-1,iaccounttype) AS iaccounttype,"
            + "if(igameid is null,-1,igameid) AS igameid,"
            + "if(iworldid is null,-1,iworldid) AS iworldid,"
            + "if(ilevel is null,-1,ilevel) AS ilevel,"
            + "suin "
            + "from fat_%s_user "
            + "where index_dtstatdate = date2long('%s') and useractivity(idayacti,1) = 1 "
            + "group by dtstatdate,iaccounttype,igameid,iworldid,ilevel,suin "
            + "grouping sets((dtstatdate,iaccounttype,igameid,iworldid,ilevel,suin),"
            + "(dtstatdate,iaccounttype,igameid,ilevel,suin),"
            + "(dtstatdate,iaccounttype,iworldid,ilevel,suin),"
            + "(dtstatdate,igameid,iworldid,ilevel,suin),"
            + "(dtstatdate,iaccounttype,igameid,iworldid,suin),"
            + "(dtstatdate,igameid,ilevel,suin),"
            + "(dtstatdate,iaccounttype,ilevel,suin),"
            + "(dtstatdate,iworldid,ilevel,suin),"
            + "(dtstatdate,iaccounttype,igameid,suin),"
            + "(dtstatdate,iaccounttype,iworldid,suin),"
            + "(dtstatdate,igameid,iworldid,suin),"
            + "(dtstatdate,iaccounttype,suin),"
            + "(dtstatdate,igameid,suin),"
            + "(dtstatdate,iworldid,suin),"
            + "(dtstatdate,ilevel,suin),"
            + "(dtstatdate,suin)))  t2 "
            + " on t1.iaccounttype=t2.iaccounttype and t1.suin=t2.suin and t1.igameid=t2.igameid and t1.iworldid=t2.iworldid "
            + " where t2.suin is not null "
            
            + "group by t1.igameid,t1.iaccounttype,t1.iworldid,t2.ilevel,datediff(t2.dtstatdate,t1.dtstatdate)";  
    
    private static String tbStayScaleDis_WeekOrMonthActi = "INSERT OVERWRITE TABLE oss_dm_%s_tbStayScaleDis "
            + "PARTITION(index_dtstatdate, index_iperiod, index_ssourceuser, index_ilookperiod) "
            + "SELECT '%s'," //date
            + "'%s'," //ssourceuser : DAYACTI  DAYREGISTER
            + "'%s'," //iPeroid
            + "t1.igameid,"
            + "t1.iaccounttype,"
            + "t1.iworldid,"
            + "t2.ilevel," 
            + "%s," //TODO  Look period
            + "count(*),"
            
            + "DATE2LONG('%s'),'%s','%s',%s "
            + "from "
            
            + "(select dtstatdate,"
            + "if(iaccounttype is null,-1,iaccounttype) AS iaccounttype,"
            + "if(igameid is null,-1,igameid) AS igameid,"
            + "if(iworldid is null,-1,iworldid) AS iworldid,"
            + "suin "
            + "from fat_%s_user "
            + "where index_dtstatdate = date2long('%s') and useractivity(%s,1) = 1 "
            + "group by dtstatdate,iaccounttype,igameid,iworldid,suin "
            + "grouping sets((dtstatdate,iaccounttype,igameid,iworldid,suin),"
            + "(dtstatdate,iaccounttype,igameid,suin),"
            + "(dtstatdate,iaccounttype,iworldid,suin),"
            + "(dtstatdate,igameid,iworldid,suin),"
            + "(dtstatdate,igameid,suin),"
            + "(dtstatdate,iaccounttype,suin),"
            + "(dtstatdate,iworldid,suin),"
            + "(dtstatdate,suin))) t1  "
            + "left join "
            + "(select dtstatdate,"
            + "if(iaccounttype is null,-1,iaccounttype) AS iaccounttype,"
            + "if(igameid is null,-1,igameid) AS igameid,"
            + "if(iworldid is null,-1,iworldid) AS iworldid,"
            + "if(ilevel is null,-1,ilevel) AS ilevel,"
            + "suin "
            + "from fat_%s_user "
            + "where index_dtstatdate = date2long('%s') and useractivity(%s,1) = 1 "
            + "group by dtstatdate,iaccounttype,igameid,iworldid,ilevel,suin "
            + "grouping sets((dtstatdate,iaccounttype,igameid,iworldid,ilevel,suin),"
            + "(dtstatdate,iaccounttype,igameid,ilevel,suin),"
            + "(dtstatdate,iaccounttype,iworldid,ilevel,suin),"
            + "(dtstatdate,igameid,iworldid,ilevel,suin),"
            + "(dtstatdate,iaccounttype,igameid,iworldid,suin),"
            + "(dtstatdate,igameid,ilevel,suin),"
            + "(dtstatdate,iaccounttype,ilevel,suin),"
            + "(dtstatdate,iworldid,ilevel,suin),"
            + "(dtstatdate,iaccounttype,igameid,suin),"
            + "(dtstatdate,iaccounttype,iworldid,suin),"
            + "(dtstatdate,igameid,iworldid,suin),"
            + "(dtstatdate,iaccounttype,suin),"
            + "(dtstatdate,igameid,suin),"
            + "(dtstatdate,iworldid,suin),"
            + "(dtstatdate,ilevel,suin),"
            + "(dtstatdate,suin)))  t2 "
            + " on t1.iaccounttype=t2.iaccounttype and t1.suin=t2.suin and t1.igameid=t2.igameid and t1.iworldid=t2.iworldid "
            + " where t2.suin is not null "
            
            + "group by t1.igameid,t1.iaccounttype,t1.iworldid,t2.ilevel";    

    public static void create_tbRegisterUser(HiveContext sqlContext, String strMode, String strDate) {

        // Initialization hive UDF
        sqlContext.sql("use dbprocess");
        sqlContext.sql("ADD JAR hdfs://10-4-28-24:8020//udf.jar");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Calendar time = Calendar.getInstance();
        time.setTime(date);

        String strSql = new String();
        String strTableField = new String();
        int iPeriod = 0;
        int iType = time.get(Calendar.DAY_OF_WEEK);
        if (1 == iType) {// the statday is sunday
            iPeriod = 7;

            // stat tbRegisterUser
            strTableField = "iweekacti";
            strSql = String.format(tbRegisterUser, strMode, CONSTANT.date2Long(strDate), iPeriod, strDate,
                    iPeriod, strTableField, 1, strTableField, 1, strTableField, 1, strMode,
                    strDate, strMode, strDate, iPeriod);
            sqlContext.sql(strSql);

            // stat tbRegisterUserTypeDis
            time.add(Calendar.DATE, -6);
            String strBeforeWeekDate = sdf.format(time.getTime());
            String str = "and iregtime >= '" + strBeforeWeekDate + "' and iregtime < date_add('"
                    + strDate + "',1) ";
            strSql = String.format(tbRegisterUserTypeDis, strMode, CONSTANT.date2Long(strDate), iPeriod,
                    strDate, iPeriod, strMode, strDate, str);
            sqlContext.sql(strSql);

            // stat tbUserActivityTypeDis
            strSql = String.format(tbUserActivityTypeDis, strMode, CONSTANT.date2Long(strDate), iPeriod,
                    strDate, "Level", iPeriod, strTableField, strTableField,
                    strTableField, strDate, strMode, strDate, strBeforeWeekDate);
            sqlContext.sql(strSql);

            // stat tbActivityScaleDis
            // 1.active user in this week
            int iMask = 7;
            strTableField = "and useractivity(iweekacti,1) = 1";
            strSql = String.format(tbActivityScaleDis, strMode, CONSTANT.date2Long(strDate), iPeriod, "WeekActi",
                    strDate, "WeekActi", iPeriod, strDate, iMask, strMode, strDate, strTableField);
            sqlContext.sql(strSql);
            
            // 2.reg user in this week
            strTableField = str;
            strSql = String.format(tbActivityScaleDis, strMode, CONSTANT.date2Long(strDate), iPeriod, "WeekReg",
                    strDate, "WeekReg", iPeriod, strDate, iMask, strMode, strDate, strTableField);
            sqlContext.sql(strSql);
            
            // stat tbStayScaleDis
            // 1.week reg user active tracking
            str = "floor(datediff('" + strDate + "',to_date(iregtime))/7) iPeriodNum";
            String strWhere = "and useractivity(iweekacti,1) = 1 and iregtime >= date_add('" + strDate + "',-251)";
            strSql = String.format(tbStayScaleDis, strMode, strDate, "WeekReg", 36, 
            		strDate, 36, "WeekReg", 
            		str, strMode, strDate, strWhere);
            sqlContext.sql(strSql);
            
            // 2.week active user active tracking
            for (int i = 0; i <= 36; ++i) {
            	time.setTime(date);
            	time.add(Calendar.DATE, -(7*i));
            	strBeforeWeekDate = sdf.format(time.getTime());
            	strSql = String.format(tbStayScaleDis_WeekOrMonthActi, strMode, strDate, "WeekActi", 36, i,
                		strDate, 36, "WeekActi", i, 
                		strMode, strBeforeWeekDate, "iWeekActi", strMode, strDate, "iWeekActi");
                sqlContext.sql(strSql);
            }
            
        }

        time.setTime(date);//renew date
        time.add(Calendar.DATE, 1);
        iType = time.get(Calendar.DAY_OF_MONTH);
        if (1 == iType) {// the statday is the lastday of month
            iPeriod = 30;

            // stat tbRegisterUser
            strTableField = "imonthacti";
            time.set(Calendar.MONTH, time.get(Calendar.MONTH) - 1);
            String strMothFirstDay = sdf.format(time.getTime());
            int iMask = time.getActualMaximum(Calendar.DATE);
            time.add(Calendar.DATE, -1);
            String strBeforeMonthDate = sdf.format(time.getTime());

            strSql = String.format(tbRegisterUser, strMode, CONSTANT.date2Long(strDate), iPeriod, strDate,
                    iPeriod, strTableField, 1, strTableField, 1, strTableField, 1, strMode,
                    strDate, strMode, strBeforeMonthDate, 0);
            sqlContext.sql(strSql);

            // stat tbRegisterUserTypeDis
            String str = "and iregtime >= '" + strMothFirstDay + "' and iregtime < date_add('"
                    + strDate + "',1) ";
            strSql = String.format(tbRegisterUserTypeDis, strMode, CONSTANT.date2Long(strDate), iPeriod,
                    strDate, iPeriod, strMode, strDate, str);
            sqlContext.sql(strSql);

            // stat tbUserActivityTypeDis
            strSql = String.format(tbUserActivityTypeDis, strMode, CONSTANT.date2Long(strDate), iPeriod,
                    strDate, "Level", iPeriod, strTableField, strTableField, strTableField,
                    strDate, strMode, strDate, strMothFirstDay);
            sqlContext.sql(strSql);

            // stat tbActivityScaleDis
            // 1. active user in this month
            strTableField = "and useractivity(imonthacti,1) = 1";
            strSql = String.format(tbActivityScaleDis, strMode, CONSTANT.date2Long(strDate), iPeriod, "MonthActi",
                    strDate, "MonthActi", iPeriod, strDate, iMask, strMode, strDate, strTableField);
            sqlContext.sql(strSql);
            
            // 2. reg user in this month
            strTableField = str;
            strSql = String.format(tbActivityScaleDis, strMode, CONSTANT.date2Long(strDate), iPeriod, "MonthReg",
                    strDate, "MonthReg", iPeriod, strDate, iMask, strMode, strDate, strTableField);
            sqlContext.sql(strSql);
            
            // stat tbStayScaleDis
            // 1.month reg user active tracking
            str = "year('" + strDate + "')*12 + month('" + strDate + "') - year(to_date(iregtime))*12 - month(to_date(iregtime)) iPeriodNum";
            time.add(Calendar.DATE, 1);//first day of the month
            time.set(Calendar.MONTH, time.get(Calendar.MONTH) - 24);
            String strBefore24MonthDate = sdf.format(time.getTime());
            String strWhere = "and useractivity(iweekacti,1) = 1 and iregtime >= '" + strBefore24MonthDate + "'";
            strSql = String.format(tbStayScaleDis, strMode, strDate, "MonthReg", 24, 
            		strDate, 24, "MonthReg", 
            		str, strMode, strDate, strWhere);
            sqlContext.sql(strSql);
            
            // 2.month active user active tracking
			for (int i = 0; i <= 24; ++i) {
				time.setTime(date);
				time.add(Calendar.MONTH, -i);
				strBeforeMonthDate = sdf.format(time.getTime());
				strSql = String.format(tbStayScaleDis_WeekOrMonthActi, strMode,
						strDate, "MonthActi", 24, i, strDate, 24, "MonthActi",
						i, strMode, strBeforeMonthDate, "iMonthActi", strMode,
						strDate, "iMonthActi");
				sqlContext.sql(strSql);
			}
        }

        // day stat,every day execute
        iPeriod = 1;
        {
            // stat tbRegisterUser
            strTableField = "idayacti";
            strSql = String.format(tbRegisterUser, strMode, CONSTANT.date2Long(strDate), iPeriod, strDate,
                    iPeriod, strTableField, 1, strTableField, 1, strTableField, 1, strMode,
                    strDate, strMode, strDate, iPeriod);
            sqlContext.sql(strSql);
            // stat tbRegisterUserTypeDis
            String str = "and iregtime >= '" + strDate + "' and iregtime < date_add('" + strDate + "',1) ";
            strSql = String.format(tbRegisterUserTypeDis, strMode, CONSTANT.date2Long(strDate), iPeriod,
                    strDate, iPeriod, strMode, strDate, str);
            sqlContext.sql(strSql);

            // stat tbDayNewRegTypeDis
            str = "and iregtime >= '" + strDate + "' and iregtime < date_add('" + strDate + "',1) ";
            strSql = String.format(tbDayNewRegTypeDis, strMode, CONSTANT.date2Long(strDate),
                    strDate, "ActivityTimesDis", strDate, strMode, strDate, str);
            sqlContext.sql(strSql);

           // stat tbUserActivityTypeDis
            strSql = String.format(tbUserActivityTypeDis, strMode, CONSTANT.date2Long(strDate), iPeriod,
                    strDate, "Level", iPeriod, strTableField, strTableField, strTableField,
                    strDate, strMode, strDate, strDate);
            sqlContext.sql(strSql);
            
            // stat tbDayUserActivityTypeDis
            strSql = String.format(tbDayUserActivityTypeDis_ActivityTimesDis, strMode, CONSTANT.date2Long(strDate),
                    strDate, "ActivityTimesDis", strMode, strDate);
            sqlContext.sql(strSql);
            
            String strRange = new String();
            if(strMode.equalsIgnoreCase("login")){
            	strRange = "case when sum(ionlinetime) < 30 then 0 when sum(ionlinetime) < 60 then 30 when sum(ionlinetime) < 300 then floor(sum(ionlinetime)/60)*60 else floor(sum(ionlinetime)/300)*300 end ionlinetimedis";
            }
            else if(strMode.equalsIgnoreCase("deposit")){
            	strRange = "floor(sum(ionlinetime)/500)*500 ionlinetimedis";
            }
            else if(strMode.equalsIgnoreCase("pay")){
            	strRange = "floor(sum(ionlinetime)/100)*100 ionlinetimedis";
            }
            else{
            	
            }
            
            strSql = String.format(tbDayUserActivityTypeDis_ActivityTimeDis, strMode, CONSTANT.date2Long(strDate),
                    strDate, "ActivityTimeDis", strRange, strMode, strDate);
            sqlContext.sql(strSql);

            // stat tbStayScaleDis
            // 1.day reg user active tracking
            str = "datediff('" + strDate + "',to_date(iregtime)) iPeriodNum";
            String strWhere = "and useractivity(idayacti,1) = 1 and iregtime >= date_add('" + strDate + "',-90)";
            strSql = String.format(tbStayScaleDis, strMode, strDate, "DayReg", 90, 
            		strDate, 90, "DayReg", 
            		str, strMode, strDate, strWhere);
            sqlContext.sql(strSql);
            
            // 2.day active user active tracking
            strSql = String.format(tbStayScaleDis_DayActi, strMode, strDate, "DayActi", 90, 
            		strDate, 90, "DayActi", 
            		strMode, strDate, strDate, strMode, strDate);
            sqlContext.sql(strSql);
        }

        // total stat,every day execute
        iPeriod = -1;
        {
            // stat tbRegisterUser
            strSql = String.format(tbRegisterUser_TotalRegUser, strMode,
                    CONSTANT.date2Long(strDate), iPeriod, strDate, iPeriod, strMode, strDate,strMode,strDate,iPeriod);
            sqlContext.sql(strSql);

            // stat tbRegisterUserTypeDis
            String str = "";
            strSql = String.format(tbRegisterUserTypeDis, strMode, CONSTANT.date2Long(strDate), iPeriod,
                    strDate, iPeriod, strMode, strDate, str);
            sqlContext.sql(strSql);
        }
    }
}
