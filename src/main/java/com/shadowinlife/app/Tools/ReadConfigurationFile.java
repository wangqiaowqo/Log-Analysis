package com.shadowinlife.app.Tools;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                
                List<String[]> tmp = new ArrayList<String[]>();
                Node nNode = nList.item(i);
                Element eElement = (Element) nNode;
                // Date Time [begin, end)
                String[] date = { eElement.getAttribute("Begin"), eElement.getAttribute("End") };
                tmp.add(date);
                m.put("Date", tmp);
                
                System.out.println(date[0] + date[1]);

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
                    String[] s = {e.getAttribute("url"), e.getAttribute("Table"), e.getTextContent()};
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
                    String[] SQL = { e.getAttribute("TempTable"), e.getTextContent() };
                    tmp_SQL.add(SQL);
                }
                m.put("Sql", tmp_SQL);
                
                List<String[]> tmp_Hive = new ArrayList<String[]>();
                NodeList HiveList = eElement.getElementsByTagName("Hive");
                for (int z = 0; z < SqlList.getLength(); z++) {
                    Element e = (Element) SqlList.item(z);
                    String[] SQL = { e.getAttribute("HiveTalbe"), e.getTextContent() };
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
}
