package com.shadowinlife.app.Tools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadConfigurationFile {
    public static List<String[]> ReadFile(String Path) {
        List<String[]> l = new ArrayList<String[]>();
        try {
            File fXmlFile = new File(Path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("keyvalue");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);
                Element eElement = (Element) nNode;
                String[] t = {eElement.getAttribute("JavaBean"),eElement.getAttribute("TableName")};
                l.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return l;
    }
}
