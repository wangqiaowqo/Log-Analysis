package com.shadowinlife.app.SQLModelFactory;

import java.io.*;
import java.sql.*;

public class EquipChange extends BaseBean implements Serializable {
    public EquipChange() {
        super();
    }
    private int iAccountType;
    private int iGameId;
    private int iWorldId;
    public int getiAccountType() {
        return iAccountType;
    }
    public int getiGameId() {
        return iGameId;
    }
    public int getiWorldId() {
        return iWorldId;
    }
    public void setiAccountType(int iAccountType) {
        this.iAccountType = iAccountType;
    }
    public void setiGameId(int iGameId) {
        this.iGameId = iGameId;
    }
    public void setiWorldId(int iWorldId) {
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

    private Integer iRoleId;
    public void setiRoleId(Integer iRoleId){
        this.iRoleId=iRoleId;
    }
   public Integer getiRoleId() {
        return iRoleId;
    }

    private String vRoleName;
    public void setvRoleName(String vRoleName){
        this.vRoleName=vRoleName;
    }
   public String getvRoleName() {
        return vRoleName;
    }

    private Integer iRoleJob;
    public void setiRoleJob(Integer iRoleJob){
        this.iRoleJob=iRoleJob;
    }
   public Integer getiRoleJob() {
        return iRoleJob;
    }

    private Integer iRoleGender;
    public void setiRoleGender(Integer iRoleGender){
        this.iRoleGender=iRoleGender;
    }
   public Integer getiRoleGender() {
        return iRoleGender;
    }

    private Integer iRoleLevel;
    public void setiRoleLevel(Integer iRoleLevel){
        this.iRoleLevel=iRoleLevel;
    }
   public Integer getiRoleLevel() {
        return iRoleLevel;
    }

    private Integer iRoleVipLevel;
    public void setiRoleVipLevel(Integer iRoleVipLevel){
        this.iRoleVipLevel=iRoleVipLevel;
    }
   public Integer getiRoleVipLevel() {
        return iRoleVipLevel;
    }

    private Integer iRoleReputationLevel;
    public void setiRoleReputationLevel(Integer iRoleReputationLevel){
        this.iRoleReputationLevel=iRoleReputationLevel;
    }
   public Integer getiRoleReputationLevel() {
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

    private String vClientIP;
    public void setvClientIP(String vClientIP){
        this.vClientIP=vClientIP;
    }
   public String getvClientIP() {
        return vClientIP;
    }

    private Integer iEquipWareID;
    public void setiEquipWareID(Integer iEquipWareID){
        this.iEquipWareID=iEquipWareID;
    }
   public Integer getiEquipWareID() {
        return iEquipWareID;
    }

    private Integer iEquipWareGuid;
    public void setiEquipWareGuid(Integer iEquipWareGuid){
        this.iEquipWareGuid=iEquipWareGuid;
    }
   public Integer getiEquipWareGuid() {
        return iEquipWareGuid;
    }

    private Integer iEquipWareGrade;
    public void setiEquipWareGrade(Integer iEquipWareGrade){
        this.iEquipWareGrade=iEquipWareGrade;
    }
   public Integer getiEquipWareGrade() {
        return iEquipWareGrade;
    }

    private String jEquipWareProperty;
    public void setjEquipWareProperty(String jEquipWareProperty){
        this.jEquipWareProperty=jEquipWareProperty;
    }
   public String getjEquipWareProperty() {
        return jEquipWareProperty;
    }

    private Integer iEquipTakeOffID;
    public void setiEquipTakeOffID(Integer iEquipTakeOffID){
        this.iEquipTakeOffID=iEquipTakeOffID;
    }
   public Integer getiEquipTakeOffID() {
        return iEquipTakeOffID;
    }

    private Integer iEquipTakeOffGuid;
    public void setiEquipTakeOffGuid(Integer iEquipTakeOffGuid){
        this.iEquipTakeOffGuid=iEquipTakeOffGuid;
    }
   public Integer getiEquipTakeOffGuid() {
        return iEquipTakeOffGuid;
    }

    private Integer iEquipTakeOffGrade;
    public void setiEquipTakeOffGrade(Integer iEquipTakeOffGrade){
        this.iEquipTakeOffGrade=iEquipTakeOffGrade;
    }
   public Integer getiEquipTakeOffGrade() {
        return iEquipTakeOffGrade;
    }

    private String jEquipTakeOffProperty;
    public void setjEquipTakeOffProperty(String jEquipTakeOffProperty){
        this.jEquipTakeOffProperty=jEquipTakeOffProperty;
    }
   public String getjEquipTakeOffProperty() {
        return jEquipTakeOffProperty;
    }

    public EquipChange parseFromLogFile(String[] args, String GameId, String AccountType, String WorldId){
        return new EquipChange(GameId, AccountType, WorldId, args[1],args[2],args[3],args[4],args[5],args[6],args[7],args[8],args[9],args[10],args[11],args[12],args[13],args[14],args[15],args[16],args[17],args[18],args[19],args[20],args[21],args[22]);
    }

    public EquipChange(String GameId, String AccountType, String WorldId, String dtEventTime,String iEventId,String vVersionId,String vUin,String iRoleId,String vRoleName,String iRoleJob,String iRoleGender,String iRoleLevel,String iRoleVipLevel,String iRoleReputationLevel,String vRoleElse1,String vRoleElse2,String vClientIP,String iEquipWareID,String iEquipWareGuid,String iEquipWareGrade,String jEquipWareProperty,String iEquipTakeOffID,String iEquipTakeOffGuid,String iEquipTakeOffGrade,String jEquipTakeOffProperty){
        this.iGameId = Integer.valueOf(GameId);
        this.iAccountType = Integer.valueOf(AccountType);
        this.iWorldId = Integer.valueOf(WorldId);
        this.dtEventTime = Timestamp.valueOf(dtEventTime);
        this.iEventId = Long.valueOf(iEventId);
        this.vVersionId = String.valueOf(vVersionId);
        this.vUin = String.valueOf(vUin);
        this.iRoleId = Integer.valueOf(iRoleId);
        this.vRoleName = String.valueOf(vRoleName);
        this.iRoleJob = Integer.valueOf(iRoleJob);
        this.iRoleGender = Integer.valueOf(iRoleGender);
        this.iRoleLevel = Integer.valueOf(iRoleLevel);
        this.iRoleVipLevel = Integer.valueOf(iRoleVipLevel);
        this.iRoleReputationLevel = Integer.valueOf(iRoleReputationLevel);
        this.vRoleElse1 = String.valueOf(vRoleElse1);
        this.vRoleElse2 = String.valueOf(vRoleElse2);
        this.vClientIP = String.valueOf(vClientIP);
        this.iEquipWareID = Integer.valueOf(iEquipWareID);
        this.iEquipWareGuid = Integer.valueOf(iEquipWareGuid);
        this.iEquipWareGrade = Integer.valueOf(iEquipWareGrade);
        this.jEquipWareProperty = String.valueOf(jEquipWareProperty);
        this.iEquipTakeOffID = Integer.valueOf(iEquipTakeOffID);
        this.iEquipTakeOffGuid = Integer.valueOf(iEquipTakeOffGuid);
        this.iEquipTakeOffGrade = Integer.valueOf(iEquipTakeOffGrade);
        this.jEquipTakeOffProperty = String.valueOf(jEquipTakeOffProperty);
    }
}
