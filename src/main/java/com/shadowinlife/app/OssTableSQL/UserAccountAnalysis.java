package com.shadowinlife.app.OssTableSQL;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
    private static String tbRegisterUser = "INSERT INTO TABLE oss_dm_%s_tbRegisterUser PARTITION(index_dtstatdate=%s) "
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
    
    private static String tbRegisterUser_TotalRegUser = "INSERT INTO TABLE oss_dm_%s_tbRegisterUser PARTITION(index_dtstatdate=%s) "
            + "SELECT '%s'," //date 
            + "%s,"  //period
            + "IF(index_igameid IS NULL,-1,index_igameid) AS index_igameid,"
            + "IF(index_iaccounttype IS NULL,-1,index_iaccounttype) as index_iaccounttype,"
            + "IF(index_iworldid is null,-1, index_iworldid) as index_iworldid,"
            + "count(DISTINCT suin) AS newcount, "
            + "0,"
            + "0,"
            + "0,"
            + "sum(itimes) AS itimes,"
            + "sum(ionlinetime) AS ionlinetime "
            + "FROM fat_%s_user WHERE index_dtstatdate=DATE2LONG('%s') " //tablename  date
            + "group by index_igameid,index_iaccounttype,index_iworldid with cube";
    
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
    private static String tbRegisterUserTypeDis = "INSERT INTO TABLE oss_dm_%s_tbRegisterUserTypeDis PARTITION(index_dtstatdate=%s) "
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
    private static String tbDayNewRegTypeDis = "INSERT INTO TABLE oss_dm_%s_tbDayNewRegTypeDis PARTITION(index_dtstatdate=%s) "
	    + "SELECT '%s'," //date
            + "'%s'," //sType:level
            + "%s," //iperiod
            + "if(index_igameid is null,-1,index_igameid),"
            + "if(index_iaccounttype is null,-1,index_iaccounttype),"
            + "if(index_iworldid is null,-1,index_iworldid),"
            + "if(totaltimes is null,-1,totaltimes)," //sTypeValue : levelvalue
            + "count(*) "
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
    private static String tbUserActivityTypeDis = "INSERT INTO TABLE osss_tbUserActivityTypeDis PARTITION(index_dtstatdate=%s) "
            + "SELECT '%s'," //date
            + "%s," //sType:level
            + "'%s'," //sTypeValue : levelvalue
            + "'%s'," //iPeroid
            + "if(index_igameid is null,-1,index_igameid),"
            + "if(index_iaccounttype is null,-1,index_iaccounttype),"
            + "if(index_iworldid is null,-1,index_iworldid),"
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
    private static String tbDayUserActivityTypeDis = "INSERT INTO TABLE oss_dm_%s_tbDayUserActivityTypeDis PARTITION(index_dtstatdate=%s) "
            + "SELECT '%s'," //date
            + "'%s'," //sType:level
            + "if(index_igameid is null,-1,index_igameid),"
            + "if(index_iaccounttype is null,-1,index_iaccounttype),"
            + "if(index_iworldid is null,-1,index_iworldid),"
            + "if(maxlevel is null,-1,maxlevel)," 
            + "count(*),"
            + "DATE2LONG('%s')" //date
            + "from "
            
            + "(select "
            + "index_iaccounttype,"
            + "index_igameid,"
            + "index_iworldid,"
            + "suin,"
            + "max(ilevel) maxlevel "
            + "from fat_%s_user " //strMode
            + "WHERE "
            + "index_dtstatdate=DATE2LONG('%s') and useractivity(idayacti,1) = 1 " //date
            + "group by index_iaccounttype,index_igameid,index_iworldid,suin) t "
            
            + "group by index_igameid,index_iaccounttype,index_iworldid,maxlevel with cube";
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
    
    private static String tbActivityScaleDis = "INSERT INTO TABLE osss_tbActivityScaleDis PARTITION(index_dtstatdate=%s) "
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
    private static String tbStayScaleDis = "INSERT INTO TABLE osss_tbStayScaleDis PARTITION(index_dtstatdate=%s) "
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
            strSql = String.format(tbRegisterUser, strMode, CONSTANT.date2Long(strDate), strDate,
                    iPeriod, strTableField, 1, strTableField, 1, strTableField, 1, strMode,
                    strDate, strMode, strDate, iPeriod);
            sqlContext.sql(strSql);

            // stat tbRegisterUserPeTypeDis
            time.add(Calendar.DATE, -7);
            String strBeforeWeekDate = sdf.format(time.getTime());
            String str = "and iregtime >= '" + strBeforeWeekDate + "' and iregtime < date_add('"
                    + strDate + "',1) ";
            strSql = String.format(tbRegisterUserTypeDis, strMode, CONSTANT.date2Long(strDate),
                    strDate, iPeriod, strMode, strDate, str);
            sqlContext.sql(strSql);
        }

        time.add(Calendar.DATE, 1);
        iType = time.get(Calendar.DAY_OF_MONTH);
        if (1 == iType) {// the statday is the lastday of month
            iPeriod = 30;

            // stat tbRegisterUser
            strTableField = "imonthacti";
            time.set(Calendar.MONDAY, time.get(Calendar.MONDAY) - 1);
            String strMothFirstDay = sdf.format(time.getTime());
            time.add(Calendar.DATE, -1);
            String strBeforeMonthDate = sdf.format(time.getTime());

            strSql = String.format(tbRegisterUser, strMode, CONSTANT.date2Long(strDate), strDate,
                    iPeriod, strTableField, 1, strTableField, 1, strTableField, 1, strMode,
                    strDate, strMode, strBeforeMonthDate, 0);
            sqlContext.sql(strSql);

            // stat tbRegisterUserPeTypeDis

            String strBeforeWeekDate = sdf.format(time.getTime());
            String str = "and iregtime >= '" + strMothFirstDay + "' and iregtime < date_add('"
                    + strDate + "',1) ";
            strSql = String.format(tbRegisterUserTypeDis, strMode, CONSTANT.date2Long(strDate),
                    strDate, iPeriod, strMode, strDate, str);
            sqlContext.sql(strSql);
        }

        // day stat,every day execute
        iPeriod = 1;
        {
            // stat tbRegisterUser
            strTableField = "idayacti";
            strSql = String.format(tbRegisterUser, strMode, CONSTANT.date2Long(strDate), strDate,
                    iPeriod, strTableField, 1, strTableField, 1, strTableField, 1, strMode,
                    strDate, strMode, strDate, iPeriod);
            sqlContext.sql(strSql);
            String str;
            // stat tbRegisterUserPeTypeDis
            str = "and iregtime >= '" + strDate + "' and iregtime < date_add('" + strDate + "',1) ";
            strSql = String.format(tbRegisterUserTypeDis, strMode, CONSTANT.date2Long(strDate),
                    strDate, iPeriod, strMode, strDate, str);
            sqlContext.sql(strSql);

            // stat tbDayNewRegTypeDis
            str = "and iregtime >= '" + strDate + "' and iregtime < date_add('" + strDate + "',1) ";
            strSql = String.format(tbDayNewRegTypeDis, strMode,  CONSTANT.date2Long(strDate),
                    strDate, "Level",iPeriod, strMode, strDate, str);
            sqlContext.sql(strSql);

            // stat tbDayUserActivityTypeDis
            strSql = String.format(tbDayUserActivityTypeDis, strMode, CONSTANT.date2Long(strDate),
                    strDate, "Level", strDate, strMode, strDate);
            sqlContext.sql(strSql);
        }

        // total stat,every day execute
        iPeriod = -1;
        {
            // stat tbRegisterUser
            strSql = String.format(tbRegisterUser_TotalRegUser, strMode,
                    CONSTANT.date2Long(strDate), strDate, iPeriod, strMode, strDate);
            sqlContext.sql(strSql);

            // stat tbRegisterUserPeTypeDis
            String str = "";
            strSql = String.format(tbRegisterUserTypeDis, strMode, CONSTANT.date2Long(strDate),
                    strDate, iPeriod, strMode, strDate, str);
            sqlContext.sql(strSql);
        }
    }
}
