package com.shadowinlife.app.OssTableSQL;

import org.apache.spark.sql.hive.HiveContext;


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
    String tbRegisterUser = "INSERT INTO TABLE osss_tbRegisterUser "
            + "SELECT '%s'," //date
            + "%s," //iperiod
            + "igameid,"
            + "iaccounttype,"
            + "iworldid,"
            + "count(suin),"
            + "sum(useractivity(idayacti, %s))," //period
            + "sum(userlost(idayacti, %s))," //period
            + "sum(usercomeback(idayacti,%s))," //period
            + "sum(itimes),"
            + "sum(ionlinetime) "
            + "from fat_tbaccount "
            + "where iregtime>='%s' AND iregtime<'%s' "
            + "group by igameid,iaccounttype,iworldid grouping sets((igameid,iaccounttype,iworldid))";
    
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
    String tbRegisterUserPeTypeDis = "INSERT INTO TABLE osss_tbRegisterUserPeTypeDis "
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
    String tbDayNewRegTypeDis = "INSERT INTO TABLE osss_tbDayNewRegTypeDis "
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
    String tbUserActivityPeTypeDis = "INSERT INTO TABLE osss_tbUserActivityPeTypeDis "
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
    String tbDayUserActivityTypeDis = "INSERT INTO TABLE osss_tbDayUserActivityTypeDis "
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
    
    String tbActivityScaleDis = "INSERT INTO TABLE osss_tbActivityScaleDis "
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
    String tbStayScaleDis = "INSERT INTO TABLE osss_tbStayScaleDis "
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
    
    public static void create_tbRegisterUser(HiveContext sqlContext,int Mode,String[] args){
        
    }
}
