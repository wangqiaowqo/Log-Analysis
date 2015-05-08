package com.shadowinlife.app.OssTableSQL;

import org.apache.spark.sql.hive.HiveContext;

import com.shadowinlife.app.LogAnalyse.ProcessTableSQL.CONSTANT;


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
    private static String tbRegisterUser = "INSERT INTO TABLE oss_tbRegisterUser PARTITION(index_dtstatdate=%s) "
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
            + "sum(useractivity(idayacti, %s)) AS useractivitynum,"
            + "sum(userlost(idayacti, %s)) AS userlostnum,"
            + "sum(usercomeback(idayacti,%s)) AS usercomebacknum,"
            + "sum(itimes) AS itimes,"
            + "sum(ionlinetime) AS ionlinetime "
            + "FROM fat_login_user WHERE index_dtstatdate=DATE2LONG('%s') "
            + "group by index_igameid,index_iaccounttype,index_iworldid with cube) T1  "
            
            + "LEFT JOIN (SELECT IF(index_igameid IS NULL,-1,index_igameid) AS index_igameid,"
            + "IF(index_iaccounttype IS NULL,-1,index_iaccounttype) AS index_iaccounttype,"
            + "IF(index_iworldid is null,-1, index_iworldid) AS index_iworldid,"
            + "COUNT(DISTINCT suin) AS oldcount "
            + "FROM fat_login_user WHERE index_dtstatdate=(DATE2LONG('%s')-1) "
            + "GROUP BY index_igameid,index_iaccounttype,index_iworldid with cube) T2 "
            
            + "ON T1.index_igameid=T2.index_igameid AND T1.index_iaccounttype=T2.index_iaccounttype "
            + "AND T1.index_iworldid=T2.index_iworldid";
    
    /*
     * tbRegisterUserPeTypeDis
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
    private static String tbRegisterUserPeTypeDis = "INSERT INTO TABLE osss_tbRegisterUserPeTypeDis "
            + "SELECT '%s'," //date
            + "%s," //sType:level
            + "'%s'," //iPeroid
            + "igameid,"
            + "iaccounttype,"
            + "iworldid,"
            + "'%s'," //sTypeValue : levelvalue
            + "count(suin) "
            + "from fat_tbaccount "
            + "where iregtime>='%s' AND iregtime<'%s' "
            + "group by igameid,iaccounttype,iworldid grouping sets((igameid,iaccounttype,iworldid))";
    /*
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
    private static String tbDayNewRegTypeDis = "INSERT INTO TABLE osss_tbDayNewRegTypeDis "
            + "SELECT '%s'," //date
            + "%s," //sType:level
            + "'%s'," //iPeroid
            + "igameid,"
            + "iaccounttype,"
            + "iworldid,"
            + "'%s'," //sTypeValue : levelvalue
            + "count(suin) "
            + "from fat_tbaccount "
            + "where iregtime>='%s' AND iregtime<'%s' "
            + "group by igameid,iaccounttype,iworldid grouping sets((igameid,iaccounttype,iworldid))";
    /*
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
    private static String tbUserActivityPeTypeDis = "INSERT INTO TABLE osss_tbUserActivityPeTypeDis "
            + "SELECT '%s'," //date
            + "%s," //sType:level
            + "'%s'," //sTypeValue : levelvalue
            + "'%s'," //iPeroid
            + "igameid,"
            + "iaccounttype,"
            + "iworldid,"
            + "count(suin),"
            + "sum(useractivity(idayacti, %s))," //period
            + "sum(userlost(idayacti, %s))," //period
            + "sum(usercomeback(idayacti,%s)) " //period
            + "from fat_tbaccount "
            + "where dtstatdate='%s' "
            + "group by igameid,iaccounttype,iworldid grouping sets((igameid,iaccounttype,iworldid))";
    
    /*
     +------------------+------------+----------+--+
     |     col_name     | data_type  | comment  |
     +------------------+------------+----------+--+
     | dtstatdate       | date       |          |
     | stype            | string     |          |
     | iperiod          | bigint     |          |
     | igameid          | int        |          |
     | iaccounttype     | int        |          |
     | iworld           | int        |          |
     | stypevalue       | string     |          |
     | idayactivitynum  | bigint     |          |
     +------------------+------------+----------+--+
     */
    private static String tbDayUserActivityTypeDis = "INSERT INTO TABLE osss_tbDayUserActivityTypeDis "
            + "SELECT '%s'," //date
            + "%s," //sType:level
            + "'%s'," //iPeroid
            + "igameid,"
            + "iaccounttype,"
            + "iworldid,"
            + "'%s'," //sTypeValue : levelvalue
            + "sum(useractivity(idayacti, %s)) " //period
            + "from fat_tbaccount "
            + "where dtstatdate='%s' "
            + "group by igameid,iaccounttype,iworldid grouping sets((igameid,iaccounttype,iworldid))";
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
     | iactivityday  | bigint     |          |
     | iallnum       | bigint     |          |
     | iactivitynum  | bigint     |          |
     +---------------+------------+----------+--+

     */
    
    private static String tbActivityScaleDis = "INSERT INTO TABLE osss_tbActivityScaleDis "
            + "SELECT '%s'," //date
            + "%s," //ssourceuser : DAY WEEK MONTH DOUBLE WEEK
            + "'%s'," //iPeroid
            + "igameid,"
            + "iaccounttype,"
            + "iworldid,"
            + "ilevel," 
            + "" //TODO  user activity day num
            + "sum(useractivity(idayacti, %s)) " 
            + "from fat_tbaccount "
            + "where '%s' " //TODO 
            + "group by igameid,iaccounttype,iworldid,ilevel grouping sets((igameid,iaccounttype,iworldid,ilevel))";
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
     | iallnum       | bigint     |          |
     | iactivitynum  | bigint     |          |
     +---------------+------------+----------+--+

     */
    private static String tbStayScaleDis = "INSERT INTO TABLE osss_tbStayScaleDis "
            + "SELECT '%s'," //date
            + "%s," //ssourceuser : DAYACTI  DAYREGISTER
            + "'%s'," //iPeroid
            + "igameid,"
            + "iaccounttype,"
            + "iworldid,"
            + "ilevel," 
            + "%s," //TODO  Look day
            + "count(suin),"
            + "sum(useractivity(idayacti, %s)) " 
            + "from fat_tbaccount "
            + "where '%s' " //TODO
            + "group by igameid,iaccounttype,iworldid,datediff(dtstatdate, %s) grouping sets((igameid,iaccounttype,iworldid,datediff(dtstatdate, %s)))";

    public static void create_tbRegisterUser(HiveContext sqlContext,int Mode,String date,int period){
        
        // Initialization hive UDF
        sqlContext.sql("use dbprocess");
        sqlContext.sql("ADD JAR hdfs://10-4-28-24:8020//udf.jar");

        /*
         * args: index_dtstatdate dtstatdate period for insert period for
         * useractivity period for userlost period for usercomeback DATE DATE
         */
        String Create_tbRegisterUser_SQL = String.format(tbRegisterUser, CONSTANT.date2Long(date),
                period, period, period, period, date, date);
        sqlContext.sql(Create_tbRegisterUser_SQL);
    }
}
