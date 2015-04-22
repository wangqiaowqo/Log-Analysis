package com.shadowinlife.app.LogAnalyse;

/**
 * FileSplit is used as the Map Class split log into different Files Based on
 * the eventTAG Login/Logout/Levelup
 * 
 * @author shadowinlife
 * @since 2015-04-01
 */

public class FileSplit {

    private String keyName = "Default";
    private String[] lineValues;

    private FileSplit(String keyName, String[] lineValues) {
        this.keyName = keyName;
        this.lineValues = lineValues;
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

    public static FileSplit parseFromLogFile(String logline) {
        String[] splitIndex = logline.split("\\|", 25);
        if (splitIndex.length > 0) {
            return new FileSplit(splitIndex[0], splitIndex);
        } else {
            return new FileSplit("null", null);
        }
    }
}
