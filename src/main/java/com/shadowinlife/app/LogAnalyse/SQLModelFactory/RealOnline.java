package com.shadowinlife.app.LogAnalyse.SQLModelFactory;

import java.io.Serializable;
import java.sql.Timestamp;

public class RealOnline implements Serializable{
  
    private static final long serialVersionUID = 394313239288834971L;
    
    private String vTag;
    private Timestamp dtEventTime;
    private int iChannelID;
    private long userCount;
    private String iLoginWay;
    
    public RealOnline(String vTag, String dtEventTime, String iChannelID, String userCount, String iLoginWay) {
        this.vTag = vTag;
        this.dtEventTime = Timestamp.valueOf(dtEventTime);
        this.iChannelID = Integer.valueOf(iChannelID);
        this.userCount = Long.valueOf(userCount);
        this.iLoginWay = iLoginWay;
    }
    
    public static RealOnline parseFromLog(String logline) {
        String[] columnList = logline.split("\\|");
        if (columnList.length == 5) {
           
            return new RealOnline(columnList[0], columnList[1], columnList[2], columnList[3],columnList[4]);
        } else {
            return null;
        }
    }
    
    public String getvTag() {
        return vTag;
    }
    public void setvTag(String vTag) {
        this.vTag = vTag;
    }
    public Timestamp getDtEventTime() {
        return dtEventTime;
    }
    public void setDtEventTime(Timestamp dtEventTime) {
        this.dtEventTime = dtEventTime;
    }
    public int getiChannelID() {
        return iChannelID;
    }
    public void setiChannelID(int iChannelID) {
        this.iChannelID = iChannelID;
    }
    public long getUserCount() {
        return userCount;
    }
    public void setUserCount(long userCount) {
        this.userCount = userCount;
    }
    public String getiLoginWay() {
        return iLoginWay;
    }
    public void setiLoginWay(String iLoginWay) {
        this.iLoginWay = iLoginWay;
    }
}
