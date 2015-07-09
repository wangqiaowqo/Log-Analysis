package com.shadowinlife.app.SQLModelFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.codesnippets4all.json.parsers.JSONParser;
import com.codesnippets4all.json.parsers.JsonParserFactory;

public abstract class BaseBean {
    public abstract BaseBean parseFromLogFile(String[] array, String GameId, String AccountType, String WorldId);
    public BaseBean(){
       
    }
    
    public List getArrayFromJson(String inputJson){
        JsonParserFactory factory=JsonParserFactory.getInstance();
        JSONParser parser=factory.newJsonParser();
        Map jsonData=parser.parseJson(inputJson);
        List value=(List)jsonData.get("root");
        return value;
    }
    
    public Map getMapFromJson(String inputJson){
        JsonParserFactory factory=JsonParserFactory.getInstance();
        JSONParser parser=factory.newJsonParser();
        Map jsonData=parser.parseJson(inputJson);
        return jsonData;
    }
}