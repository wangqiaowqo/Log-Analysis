package com.shadowinlife.app.Split;

import java.util.List;
import java.util.Map;

import com.codesnippets4all.json.parsers.JSONParser;
import com.codesnippets4all.json.parsers.JsonParserFactory;

/**
 * @author shadowinlife
 * @since 2015-04-05
 */

public class LogLineSplit {

    private String keyName = "Default";
    private String[] lineValues;
    private String dtEvetnTime;
    private String fileValue;

    private LogLineSplit(String keyName, String[] lineValues) {
        this.keyName = keyName;
        this.lineValues = lineValues;

    }

    private LogLineSplit(String dtEventTime, String fileValue) {
        this.dtEvetnTime = dtEventTime;
        this.fileValue = fileValue;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String[] getLineValues() {
        return lineValues;
    }

    public void setLineValues(String[] lineValues) {
        this.lineValues = lineValues;
    }

    public String getDtEvetnTime() {
        return dtEvetnTime;
    }

    public void setDtEvetnTime(String dtEvetnTime) {
        this.dtEvetnTime = dtEvetnTime;
    }

    public static LogLineSplit parseFromLogFile(String logline) {
        String[] splitIndex = logline.split("\\|", 50);
        if (splitIndex.length > 2) {

            return new LogLineSplit(splitIndex[0], splitIndex);

        } else {
            return new LogLineSplit("null", logline);
        }
    }

    public static List<Long> getArrayFromJson(String inputJson) {
        JsonParserFactory factory = JsonParserFactory.getInstance();
        JSONParser parser = factory.newJsonParser();
        @SuppressWarnings("rawtypes")
        Map jsonData = parser.parseJson(inputJson);
        @SuppressWarnings("unchecked")
        List<Long> value = (List<Long>) jsonData.get("root");
        return value;
    }

    public static Map<String, Long> getMapFromJson(String inputJson) {
        JsonParserFactory factory = JsonParserFactory.getInstance();
        JSONParser parser = factory.newJsonParser();
        @SuppressWarnings("unchecked")
        Map<String, Long> jsonData = (Map<String, Long>) parser.parseJson(inputJson);
        return jsonData;
    }

    public String getFileValue() {
        return fileValue;
    }

    public void setFileValue(String fileValue) {
        this.fileValue = fileValue;
    }

}
