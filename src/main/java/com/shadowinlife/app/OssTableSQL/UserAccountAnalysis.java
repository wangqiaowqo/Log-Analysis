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
            + "sum(useractivity(%s, %s)) AS useractivitynum," //strTable //period
            + "sum(userlost(%s, %s)) AS userlostnum," //strTable //period
            + "sum(usercomeback(%s,%s)) AS usercomebacknum," //strTable period
            + "sum(itimes) AS itimes," 
            + "sum(ionlinetime) AS ionlinetime "
            + "FROM fat_%s_user WHERE index_dtstatdate=DATE2LONG('%s') " //strMode Table name
            + "group by index_igameid,index_iaccounttype,index_iworldid with cube) T1  "
            
            + "LEFT JOIN (SELECT IF(index_igameid IS NULL,-1,index_igameid) AS index_igameid,"
            + "IF(index_iaccounttype IS NULL,-1,index_iaccounttype) AS index_iaccounttype,"
            + "IF(index_iworldid is null,-1, index_iworldid) AS index_iworldid,"
            + "COUNT(DISTINCT suin) AS oldcount "
            + "FROM fat_%s_user WHERE index_dtstatdate=(DATE2LONG('%s')-%s) " //strMode date period
            + "GROUP BY index_igameid,index_iaccounttype,index_iworldid with cube) T2 "
            
            + "ON T1.index_igameid=T2.index_igameid AND T1.index_iaccounttype=T2.index_iaccounttype "
            + "AND T1.index_iworldid=T2.index_iworldid";
    
    private static String tbRegisterUser_TotalRegUser = "INSERT INTO TABLE oss_tbRegisterUser PARTITION(index_dtstatdate=%s) "
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
            + "FROM fat_login_user WHERE index_dtstatdate=DATE2LONG('%s') "
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

    public static void create_tbRegisterUser(HiveContext sqlContext,String strMode,String strDate){
        
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
		
		Calendar time=Calendar.getInstance();
		time.setTime(date);
		
		String strSql;
		String strTableField = new String();
		int iPeriod = 0;
		int iType = time.get(Calendar.DAY_OF_WEEK);
		if (1 == iType){//周的最后一天，需要进行周统计
			iPeriod = 7;
			strTableField = "iweekacti";
			strSql = String.format(tbRegisterUser,CONSTANT.date2Long(strDate),
					strDate,iPeriod,strTableField,1,strTableField,1,strTableField,1,
					strMode,strDate,strMode,strDate,iPeriod);
			sqlContext.sql(strSql);
		}
		
		//是否需要进行月统计
		time.add(Calendar.DATE, 1);
		iType = time.get(Calendar.DAY_OF_MONTH);
		if (1 == iType){//月的最后一天，需要进行月统计
			iPeriod = 30;
			strTableField = "imonthacti";
			time.set(Calendar.MONDAY, time.get(Calendar.MONDAY) - 1);
			time.add(Calendar.DATE, -1);
			String strBeforeMonthDate = sdf.format(time.getTime());
			
			strSql = String.format(tbRegisterUser,CONSTANT.date2Long(strDate),
					strDate,iPeriod,strTableField,1,strTableField,1,strTableField,1,
					strMode,strDate,strMode,strBeforeMonthDate,0);
			sqlContext.sql(strSql);
		}
        
		//日统计，需要每天进行
		iPeriod = 1;
		strTableField = "idayacti";
		strSql = String.format(tbRegisterUser,CONSTANT.date2Long(strDate),
				strDate,iPeriod,strTableField,1,strTableField,1,strTableField,1,
				strMode,strDate,strMode,strDate,iPeriod);
		sqlContext.sql(strSql);
        
        //总注册用户，每天需要计算
		iPeriod = -1;
		strSql = String.format(tbRegisterUser_TotalRegUser,CONSTANT.date2Long(strDate),
				strDate,iPeriod,strDate);
		sqlContext.sql(strSql);
    }
}
