package com.shadowinlife.app.Tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadConfigurationFile {
    
    public static List<Map<String, List<String[]>>> ReadLogAnalyseConfigurationByDb(String ConfiguationFile){
    	List<Map<String, List<String[]>>> l = new ArrayList<Map<String, List<String[]>>>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			// read jdbc Properties
			InputStream fileInputStream = new FileInputStream(ConfiguationFile);
			Properties properties = new Properties();
			properties.load(fileInputStream);
			fileInputStream.close();
			
			Class.forName(properties.getProperty("database.driver"));
			conn = DriverManager.getConnection(properties.getProperty("database.url"), properties.getProperty("database.user"), properties.getProperty("database.pwd"));
			st = conn.createStatement();
			
			String templateIPeriod = "";
			String templateIStep = "";
			String templateIGameId = "";
			String templateIWorldId = "";
			String templateIAccountType ="";
			
			rs = st.executeQuery("SELECT * FROM t_template_info");
			if (rs.next()) {
				templateIPeriod = rs.getString("iPeriod");
				templateIStep = rs.getString("iStep");
				templateIGameId = rs.getString("iGameId");
				templateIWorldId = rs.getString("iWorldId");
				templateIAccountType = rs.getString("iAccountType");
			}else{
				System.out.println("t_template_info null");
			}
			
			rs = st.executeQuery("SELECT * FROM t_action_info");
			while (rs.next()) {

				Map<String, List<String[]>> m = new HashMap<String, List<String[]>>();
				String actionName = rs.getString("vActionName");
				String[] name = { actionName };
				List<String[]> tmp_name = new ArrayList<String[]>();
				tmp_name.add(name);
				m.put("Name", tmp_name);

				// Period
				List<String[]> tmp_Period = new ArrayList<String[]>();
				if(rs.getString("iPeriod")==null||"".equals(rs.getString("iPeriod"))){
					tmp_Period.add(templateIPeriod.split(","));
				}else{
					tmp_Period.add(rs.getString("iPeriod").split(","));
				}
				m.put("Period", tmp_Period);
				

				// Step
				List<String[]> tmp_Step = new ArrayList<String[]>();
				if(rs.getString("iStep")==null||"".equals(rs.getString("iStep"))){
					tmp_Step.add(templateIStep.split(","));
				}else{
					tmp_Step.add(rs.getString("iStep").split(","));
				}
				m.put("Step", tmp_Step);

				// GameId comma separate
				List<String[]> tmp_GameId = new ArrayList<String[]>();
				if(rs.getString("iGameId")==null||"".equals(rs.getString("iGameId"))){
					tmp_GameId.add(templateIGameId.split(","));
				}else{
					tmp_GameId.add(rs.getString("iGameId").split(","));
				}
				m.put("GameId", tmp_GameId);

				// WorldId comma separate
				if(rs.getString("iWorldId")==null||"".equals(rs.getString("iWorldId"))){
					String[] WorldId = getIWorldId("templateIWorldId",templateIWorldId).split(",");
					List<String[]> tmp_WorldId = new ArrayList<String[]>();
					tmp_WorldId.add(WorldId);
					m.put("WorldId", tmp_WorldId);
				}else{
					boolean b = "false".equals(getIWorldId(actionName,rs.getString("iWorldId")))?true:false;
					if(!b){
						String[] WorldId = getIWorldId(actionName,rs.getString("iWorldId")).split(",");
						List<String[]> tmp_WorldId = new ArrayList<String[]>();
						tmp_WorldId.add(WorldId);
						m.put("WorldId", tmp_WorldId);
					}else{
						String[] WorldId = getIWorldId("templateIWorldId",templateIWorldId).split(",");
						List<String[]> tmp_WorldId = new ArrayList<String[]>();
						tmp_WorldId.add(WorldId);
						m.put("WorldId", tmp_WorldId);
					}
				}

				// AccountType comma separate
				List<String[]> tmp_AccountType = new ArrayList<String[]>();
				if(rs.getString("iAccountType")==null||"".equals(rs.getString("iAccountType"))){
					tmp_AccountType.add(templateIAccountType.split(","));
				}else{
					tmp_AccountType.add(rs.getString("iAccountType").split(","));
				}
				m.put("AccountType", tmp_AccountType);

				putFinalIntoMap(conn, m, actionName);
				
				putMysqlIntoMap(conn, m, actionName);

				putTableIntoMap(conn, m, actionName);

				putSqlIntoMap(conn, m, actionName);

				l.add(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return l;
    }
    
    private static void putMysqlIntoMap(Connection connection, Map<String, List<String[]>> map,
            String actionName) {
        Statement st = null;
        ResultSet fianlResultSet = null;
        try {
            st = connection.createStatement();
            fianlResultSet = st
                    .executeQuery("SELECT * FROM t_action_mysql WHERE vActionName = '"
                            + actionName + "'");
            List<String[]> tmp_Final = new ArrayList<String[]>();
            while (fianlResultSet.next()) {
                String[] s = { fianlResultSet.getString("vURL"),
                        fianlResultSet.getString("vSql")
                       };
                tmp_Final.add(s);
            }
            map.put("Mysql", tmp_Final);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fianlResultSet != null) {
                try {
                    fianlResultSet.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        
    }

    private static void putFinalIntoMap(Connection connection,
			Map<String, List<String[]>> map, String actionName){
		Statement st = null;
		ResultSet fianlResultSet = null;
		try {
			st = connection.createStatement();
			fianlResultSet = st
					.executeQuery("SELECT * FROM t_action_final WHERE vActionName = '"
							+ actionName + "'" + " ORDER BY iExeOrder asc");
			List<String[]> tmp_Final = new ArrayList<String[]>();
			while (fianlResultSet.next()) {
				String[] s = { fianlResultSet.getString("vURL"),
						fianlResultSet.getString("vFinalTable"),
						fianlResultSet.getString("vFinalSql") };
				tmp_Final.add(s);
			}
			map.put("Final", tmp_Final);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fianlResultSet != null) {
				try {
					fianlResultSet.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
    }
    
    private static void putTableIntoMap(Connection connection,
			Map<String, List<String[]>> map, String actionName){
		Statement st = null;
		ResultSet tableResultSet = null;
		try {
			st = connection.createStatement();
			tableResultSet = st
					.executeQuery("SELECT * FROM t_action_table WHERE vActionName = '"
							+ actionName + "'" + " ORDER BY iExeOrder asc");
			List<String[]> tmp_Table = new ArrayList<String[]>();
			tableResultSet.last();
			String[] tableName = new String[tableResultSet.getRow()];
			tableResultSet.first();
			tableResultSet.beforeFirst();
				
			int index=0;
			while (tableResultSet.next()) {
				tableName[index] = tableResultSet.getString("vActionTable");
				index = index + 1;
			}
			tmp_Table.add(tableName);
			map.put("Table", tmp_Table);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (tableResultSet != null) {
				try {
					tableResultSet.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
    }
    
    private static void putSqlIntoMap(Connection connection,
			Map<String, List<String[]>> map, String actionName){
		Statement st = null;
		ResultSet sqlResultSet = null;
		try {
			st = connection.createStatement();
			sqlResultSet = st
					.executeQuery("SELECT * FROM t_action_sql WHERE vActionName = '"
							+ actionName + "'" + " ORDER BY iExeOrder asc");
			List<String[]> tmp_Sql = new ArrayList<String[]>();
			while (sqlResultSet.next()) {
				String[] s = { sqlResultSet.getString("vTempTable"),
						sqlResultSet.getString("vActionSql") };
				tmp_Sql.add(s);
			}
			map.put("Sql", tmp_Sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlResultSet != null) {
				try {
					sqlResultSet.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
    }
    
   private static String getIWorldId(String actionName,String arg) {
    	StringBuffer sb = new StringBuffer();
		//String[] worlds = "1-10:3-5|6|7,14-19,21".split(",");//1,2,8,9,10,14,15,16,17,18,19,21
		String[] worlds = arg.split(",");
		for(int i=0;i<worlds.length;i++){
			if(worlds[i].contains(":")){
				//1-10:3-5|6|7
				String[] values = worlds[i].split(":");
				if(values.length<2){
					System.out.println("Action :" + actionName + " iWorldId  error expression ! ':' values length less than 2");
					return "false";
				}
				if(!values[0].contains("-")){
					System.out.println("Action :" + actionName + " iWorldId error expression ! key error");
					return "false";
				}
				StringBuffer resultSB = new StringBuffer();
				//先拆冒号后面 3-5|6|7 -> 3,4,5,6,7
				String[] value = values[1].split("\\|");
				StringBuffer excludeSB = new StringBuffer();
				for(String v:value){
					if(v.contains("-")){
						String[] sToE = v.split("-");//3-5
						int s = Integer.parseInt(sToE[0]);//3
						int e = Integer.parseInt(sToE[1]);//5
						for(int j=s;j<=e;j++){
							excludeSB.append(String.valueOf(j)).append(",");
						}
					}else{
						excludeSB.append(v).append(",");
					}
				}
				//去掉末尾 分号
				excludeSB =  excludeSB.deleteCharAt(excludeSB.length()-1);
				String[] excludes = excludeSB.toString().split(",");
				
				//后处理冒号前1-10
				String[] key = values[0].split("-");
				int ss = Integer.parseInt(key[0]);
				int ee = Integer.parseInt(key[1]);
				for(int k=ss;k<=ee;k++){
					boolean match = false;
					for(String exclude:excludes){
						if(k==Integer.parseInt(exclude)){
							match = true;
						}
					}
					if(!match){
						resultSB.append(k).append(",");
					}
				}
				resultSB = resultSB.deleteCharAt(resultSB.length()-1);
				sb.append(",").append(resultSB);
			}else{
				//没有冒号
				if(worlds[i].contains("-")){
					//14-19
					String[] aa = worlds[i].split("-");
					if(aa.length<2){
						System.out.println("Action :" + actionName + " iWorldId error expression ! ':' values length less than 2");
						return "false";
					}
					StringBuffer resultSB = new StringBuffer();
					int s = Integer.parseInt(aa[0]);
					int e = Integer.parseInt(aa[1]);
					for(int j=s;j<=e;j++){
						resultSB.append(String.valueOf(j)).append(",");
					}
					resultSB = resultSB.deleteCharAt(resultSB.length()-1);
					sb.append(",").append(resultSB);
				}else{
					//21
					sb.append(",").append(worlds[i]);
				}
			}
		}
		String r = sb.toString().substring(1);
		return r;
	}
   
}
