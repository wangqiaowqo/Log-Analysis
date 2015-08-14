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
    public static List<String[]> ReadSplitConfiguration(String Path) {
        List<String[]> l = new ArrayList<String[]>();
        try {
            File fXmlFile = new File(Path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("struct");

            for (int i = 0; i < nList.getLength(); i++) {

                Node nNode = nList.item(i);
                Element eElement = (Element) nNode;
                String[] t = { eElement.getAttribute("JavaBean"),
                        eElement.getAttribute("TableName") };
                System.out.println("Node element :" + t[0] + " " + t[1]);
                l.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return l;
    }

    /**
     * &lt; < less than &gt; > greater than &amp; & ampersand &apos; '
     * apostrophe &quot; " quotation mark
     */
    public static List<Map<String, List<String[]>>> ReadLogAnalyseConfiguration(String Path) {
        List<Map<String, List<String[]>>> l = new ArrayList<Map<String, List<String[]>>>();
        try {
            File fXmlFile = new File(Path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("Action");

            for (int i = 0; i < nList.getLength(); i++) {
                Map<String, List<String[]>> m = new HashMap<String, List<String[]>>();
                
                
                Node nNode = nList.item(i);
                Element eElement = (Element) nNode;
                
                String[] name = {eElement.getAttribute("Name").trim()};
                List<String[]> tmp_name = new ArrayList<String[]>();
                tmp_name.add(name);
                m.put("Name", tmp_name);
                
                //Period
                String[] Period = eElement.getElementsByTagName("Period").item(0).getTextContent().split(",");
                List<String[]> tmp_Period = new ArrayList<String[]>();
                tmp_Period.add(Period);
                m.put("Period", tmp_Period);
                
                //Step
                String[] Step = eElement.getElementsByTagName("Step").item(0).getTextContent().split(",");
                List<String[]> tmp_Step = new ArrayList<String[]>();
                tmp_Step.add(Step);
                m.put("Step", tmp_Step);

                // GameId comma separate
                String[] GameId = eElement.getElementsByTagName("GameId").item(0).getTextContent()
                        .split(",");
                List<String[]> tmp_GameId = new ArrayList<String[]>();
                tmp_GameId.add(GameId);
                m.put("GameId", tmp_GameId);
                
                // WorldId comma separate
                String[] WorldId = eElement.getElementsByTagName("WorldId").item(0)
                        .getTextContent().split(",");
                List<String[]> tmp_WorldId = new ArrayList<String[]>();
                tmp_WorldId.add(WorldId);
                m.put("WorldId", tmp_WorldId);

                // AccountType comma separate
                String[] AccountType = eElement.getElementsByTagName("AccountType").item(0)
                        .getTextContent().split(",");
                List<String[]> tmp_AccountType = new ArrayList<String[]>();
                tmp_AccountType.add(AccountType);
                m.put("AccountType", tmp_AccountType);
                
                
                NodeList finalList = eElement.getElementsByTagName("Final");
                List<String[]> tmp_Final = new ArrayList<String[]>();
                for (int j = 0; j < finalList.getLength(); j++) {
                    Element e = (Element) finalList.item(j);
                    String[] s = {e.getAttribute("URL").trim(), e.getAttribute("Table").trim(), e.getTextContent()};
                    tmp_Final.add(s);
                }
                m.put("Final", tmp_Final);
                
                String[] Table = eElement.getElementsByTagName("Table").item(0).getTextContent()
                        .split(",");
                List<String[]> tmp_Table = new ArrayList<String[]>();
                tmp_Table.add(Table);
                m.put("Table", tmp_Table);
                
                List<String[]> tmp_SQL = new ArrayList<String[]>();
                NodeList SqlList = eElement.getElementsByTagName("Sql");
                for (int j = 0; j < SqlList.getLength(); j++) {
                    Element e = (Element) SqlList.item(j);
                    String[] SQL = { e.getAttribute("TempTable").trim(), e.getTextContent() };
                    tmp_SQL.add(SQL);
                }
                m.put("Sql", tmp_SQL);
                
                List<String[]> tmp_Hive = new ArrayList<String[]>();
                NodeList HiveList = eElement.getElementsByTagName("Hive");
                for (int z = 0; z < SqlList.getLength(); z++) {
                    Element e = (Element) SqlList.item(z);
                    String[] SQL = { e.getAttribute("HiveTalbe").trim(), e.getTextContent() };
                    tmp_Hive.add(SQL);
                }
                m.put("Hive", tmp_Hive);
                
                l.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }
    
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
			rs = st.executeQuery("SELECT * FROM t_action_info");
			while (rs.next()) {

				Map<String, List<String[]>> m = new HashMap<String, List<String[]>>();
				String actionName = rs.getString("vActionName");
				String[] name = { actionName };
				List<String[]> tmp_name = new ArrayList<String[]>();
				tmp_name.add(name);
				m.put("Name", tmp_name);

				// Period
				String[] Period = rs.getString("iPeriod").split(",");
				List<String[]> tmp_Period = new ArrayList<String[]>();
				tmp_Period.add(Period);
				m.put("Period", tmp_Period);

				// Step
				String[] Step = rs.getString("iStep").split(",");
				List<String[]> tmp_Step = new ArrayList<String[]>();
				tmp_Step.add(Step);
				m.put("Step", tmp_Step);

				// GameId comma separate
				String[] GameId = rs.getString("iGameId").split(",");
				List<String[]> tmp_GameId = new ArrayList<String[]>();
				tmp_GameId.add(GameId);
				m.put("GameId", tmp_GameId);

				// WorldId comma separate
				String[] WorldId = rs.getString("iWorldId").split(",");
				List<String[]> tmp_WorldId = new ArrayList<String[]>();
				tmp_WorldId.add(WorldId);
				m.put("WorldId", tmp_WorldId);

				// AccountType comma separate
				String[] AccountType = rs.getString("iAccountType").split(",");
				List<String[]> tmp_AccountType = new ArrayList<String[]>();
				tmp_AccountType.add(AccountType);
				m.put("AccountType", tmp_AccountType);

				putFinalIntoMap(conn, m, actionName);

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
}
