package com.shadowinlife.app.SQLModelFactory;
import java.io.*;
import java.sql.*;

public class RoleLogout extends BaseBean implements Serializable {
    public RoleLogout() {
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

    private Long iRoleId;
    public void setiRoleId(Long iRoleId){
        this.iRoleId=iRoleId;
    }
   public Long getiRoleId() {
        return iRoleId;
    }

    private String vRoleName;
    public void setvRoleName(String vRoleName){
        this.vRoleName=vRoleName;
    }
   public String getvRoleName() {
        return vRoleName;
    }

    private Long iRoleJob;
    public void setiRoleJob(Long iRoleJob){
        this.iRoleJob=iRoleJob;
    }
   public Long getiRoleJob() {
        return iRoleJob;
    }

    private Long iRoleGender;
    public void setiRoleGender(Long iRoleGender){
        this.iRoleGender=iRoleGender;
    }
   public Long getiRoleGender() {
        return iRoleGender;
    }

    private Long iRoleLevel;
    public void setiRoleLevel(Long iRoleLevel){
        this.iRoleLevel=iRoleLevel;
    }
   public Long getiRoleLevel() {
        return iRoleLevel;
    }

    private Long iRoleVipLevel;
    public void setiRoleVipLevel(Long iRoleVipLevel){
        this.iRoleVipLevel=iRoleVipLevel;
    }
   public Long getiRoleVipLevel() {
        return iRoleVipLevel;
    }

    private Long iRoleReputationLevel;
    public void setiRoleReputationLevel(Long iRoleReputationLevel){
        this.iRoleReputationLevel=iRoleReputationLevel;
    }
   public Long getiRoleReputationLevel() {
        return iRoleReputationLevel;
    }

    private String vRoleElse1;
    public void setvRoleElse1(String vRoleElse1){
        this.vRoleElse1=vRoleElse1;
    }
   public String getvRoleElse1() {
        return vRoleElse1;
    }

    private String vRoleElse2;
    public void setvRoleElse2(String vRoleElse2){
        this.vRoleElse2=vRoleElse2;
    }
   public String getvRoleElse2() {
        return vRoleElse2;
    }

    private Timestamp dtLogintime;
    public void setdtLogintime(Timestamp dtLogintime){
        this.dtLogintime=dtLogintime;
    }
   public Timestamp getdtLogintime() {
        return dtLogintime;
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

    private Long iExp;
    public void setiExp(Long iExp){
        this.iExp=iExp;
    }
   public Long getiExp() {
        return iExp;
    }

    private Long iReputation;
    public void setiReputation(Long iReputation){
        this.iReputation=iReputation;
    }
   public Long getiReputation() {
        return iReputation;
    }

    private Long iEnergy;
    public void setiEnergy(Long iEnergy){
        this.iEnergy=iEnergy;
    }
   public Long getiEnergy() {
        return iEnergy;
    }

    private String jMoney;
    public void setjMoney(String jMoney){
        this.jMoney=jMoney;
    }
   public String getjMoney() {
        return jMoney;
    }

    private Long lOnlineTotalTime;
    public void setlOnlineTotalTime(Long lOnlineTotalTime){
        this.lOnlineTotalTime=lOnlineTotalTime;
    }
   public Long getlOnlineTotalTime() {
        return lOnlineTotalTime;
    }

    private String vLoginWay;
    public void setvLoginWay(String vLoginWay){
        this.vLoginWay=vLoginWay;
    }
   public String getvLoginWay() {
        return vLoginWay;
    }

    public RoleLogout parseFromLogFile(String[] args, String GameId, String AccountType, String WorldId){
        return new RoleLogout(GameId, AccountType, WorldId, args[1],args[2],args[3],args[4],args[5],args[6],args[7],args[8],args[9],args[10],args[11],args[12],args[13],args[14],args[15],args[16],args[17],args[18],args[19],args[20],args[21],args[22]);
    }

    public RoleLogout(String GameId, String AccountType, String WorldId, String dtEventTime,String iEventId,String vVersionId,String vUin,String iRoleId,String vRoleName,String iRoleJob,String iRoleGender,String iRoleLevel,String iRoleVipLevel,String iRoleReputationLevel,String vRoleElse1,String vRoleElse2,String dtLogintime,String vClientIp,String vZoneId,String iExp,String iReputation,String iEnergy,String jMoney,String lOnlineTotalTime,String vLoginWay){
        this.iGameId = Long.valueOf(GameId);
        this.iAccountType = Long.valueOf(AccountType);
        this.iWorldId = Long.valueOf(WorldId);
        this.dtEventTime = Timestamp.valueOf(dtEventTime);
        this.iEventId = Long.valueOf(iEventId);
        this.vVersionId = String.valueOf(vVersionId);
        this.vUin = String.valueOf(vUin);
        this.iRoleId = Long.valueOf(iRoleId);
        this.vRoleName = String.valueOf(vRoleName);
        this.iRoleJob = Long.valueOf(iRoleJob);
        this.iRoleGender = Long.valueOf(iRoleGender);
        this.iRoleLevel = Long.valueOf(iRoleLevel);
        this.iRoleVipLevel = Long.valueOf(iRoleVipLevel);
        this.iRoleReputationLevel = Long.valueOf(iRoleReputationLevel);
        this.vRoleElse1 = String.valueOf(vRoleElse1);
        this.vRoleElse2 = String.valueOf(vRoleElse2);
        this.dtLogintime = Timestamp.valueOf(dtLogintime);
        this.vClientIp = String.valueOf(vClientIp);
        this.vZoneId = String.valueOf(vZoneId);
        this.iExp = Long.valueOf(iExp);
        this.iReputation = Long.valueOf(iReputation);
        this.iEnergy = Long.valueOf(iEnergy);
        this.jMoney = String.valueOf(jMoney);
        this.lOnlineTotalTime = Long.valueOf(lOnlineTotalTime);
        this.vLoginWay = String.valueOf(vLoginWay);
    }
}
