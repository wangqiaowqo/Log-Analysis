package com.shadowinlife.app.Tools;
/**
 * FileSplit is used as the Map Class split log into different Files Based on
 * the eventTAG Login/Logout/Levelup
 * 
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
        String[] splitIndex = logline.split("\\|", 31);
        if (splitIndex.length > 2) {

            return new LogLineSplit(splitIndex[0], splitIndex);

        } else {
            return new LogLineSplit("null", logline);
        }
    }

    public static LogLineSplit parseLogFileToKV(String logline) {
        String[] splitIndex = logline.split("\\|", 31);
        if (splitIndex.length > 2) {
            if (splitIndex[1].length() < 10) {
                return new LogLineSplit(splitIndex[0] + "0000-00-00", logline);
            }
            String key = null;
            switch(splitIndex[0]){
            case "RoleLogin":
                key = "RoleLogin" + splitIndex[1].substring(0, 10);
                break;
            case "RoleLogout":
                key = "RoleLogin" + splitIndex[1].substring(0, 10);
                break;
            case "TaskStart":
                key = "Task" + splitIndex[1].substring(0, 10);
                break;
            case "TaskFinished":
                key = "Task" + splitIndex[1].substring(0, 10);
                break;
            default:
                key = splitIndex[0] + splitIndex[1].substring(0, 10);
            }
            
            return new LogLineSplit(key, logline);

        } else {
            return new LogLineSplit("null", logline);
        }
    }

    public String getFileValue() {
        return fileValue;
    }

    public void setFileValue(String fileValue) {
        this.fileValue = fileValue;
    }

}
