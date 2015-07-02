package com.shadowinlife.app.SQLModelFactory;
import java.io.*;
import java.sql.*;

public class AccountLogin extends BaseBean implements Serializable {
    public AccountLogin() {
        super();
    }
    private Long iAccountType;
    private Long iGameId;
    private Long iWorldId;
    public Long getiAccountType() {
        return iAccountType;
    }
    public Long getiGameId() {
        return iGameId;
    }
    public Long getiWorldId() {
        return iWorldId;
    }
    public void setiAccountType(Long iAccountType) {
        this.iAccountType = iAccountType;
    }
    public void setiGameId(Long iGameId) {
        this.iGameId = iGameId;
    }
    public void setiWorldId(Long iWorldId) {
        this.iWorldId = iWorldId;
    }
    private Timestamp dtEventTime;
    public void setdtEventTime(Timestamp dtEventTime){
        this.dtEventTime=dtEventTime;
    }
   public Timestamp getdtEventTime() {
        return dtEventTime;
    }

    private Long iEventId;
    public void setiEventId(Long iEventId){
        this.iEventId=iEventId;
    }
   public Long getiEventId() {
        return iEventId;
    }

    private String vVersionId;
    public void setvVersionId(String vVersionId){
        this.vVersionId=vVersionId;
    }
   public String getvVersionId() {
        return vVersionId;
    }

    private String vUin;
    public void setvUin(String vUin){
        this.vUin=vUin;
    }
   public String getvUin() {
        return vUin;
    }

    private String vClientIp;
    public void setvClientIp(String vClientIp){
        this.vClientIp=vClientIp;
    }
   public String getvClientIp() {
        return vClientIp;
    }

    private String vZoneId;
    public void setvZoneId(String vZoneId){
        this.vZoneId=vZoneId;
    }
   public String getvZoneId() {
        return vZoneId;
    }

    private Timestamp dtCreateTime;
    public void setdtCreateTime(Timestamp dtCreateTime){
        this.dtCreateTime=dtCreateTime;
    }
   public Timestamp getdtCreateTime() {
        return dtCreateTime;
    }

    private Long iFeeFlag;
    public void setiFeeFlag(Long iFeeFlag){
        this.iFeeFlag=iFeeFlag;
    }
   public Long getiFeeFlag() {
        return iFeeFlag;
    }

    private String vLoginWay;
    public void setvLoginWay(String vLoginWay){
        this.vLoginWay=vLoginWay;
    }
   public String getvLoginWay() {
        return vLoginWay;
    }

    public AccountLogin parseFromLogFile(String[] args, String GameId, String AccountType, String WorldId){
        return new AccountLogin(GameId, AccountType, WorldId, args[1],args[2],args[3],args[4],args[5],args[6],args[7],args[8],args[9]);
    }

    public AccountLogin(String GameId, String AccountType, String WorldId, String dtEventTime,String iEventId,String vVersionId,String vUin,String vClientIp,String vZoneId,String dtCreateTime,String iFeeFlag,String vLoginWay){
        this.iGameId = Long.valueOf(GameId);
        this.iAccountType = Long.valueOf(AccountType);
        this.iWorldId = Long.valueOf(WorldId);
        this.dtEventTime = Timestamp.valueOf(dtEventTime);
        this.iEventId = Long.valueOf(iEventId);
        this.vVersionId = String.valueOf(vVersionId);
        this.vUin = String.valueOf(vUin);
        this.vClientIp = String.valueOf(vClientIp);
        this.vZoneId = String.valueOf(vZoneId);
        this.dtCreateTime = Timestamp.valueOf(dtCreateTime);
        this.iFeeFlag = Long.valueOf(iFeeFlag);
        this.vLoginWay = String.valueOf(vLoginWay);
    }
}
