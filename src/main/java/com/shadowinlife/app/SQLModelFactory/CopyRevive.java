package com.shadowinlife.app.SQLModelFactory;

import java.io.*;
import java.sql.*;

public class CopyRevive extends BaseBean implements Serializable {
    public CopyRevive() {
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

    private Integer iRoleExperience;
    public void setiRoleExperience(Integer iRoleExperience){
        this.iRoleExperience=iRoleExperience;
    }
   public Integer getiRoleExperience() {
        return iRoleExperience;
    }

    private Integer iRoleSword;
    public void setiRoleSword(Integer iRoleSword){
        this.iRoleSword=iRoleSword;
    }
   public Integer getiRoleSword() {
        return iRoleSword;
    }

    private Integer iLogType;
    public void setiLogType(Integer iLogType){
        this.iLogType=iLogType;
    }
   public Integer getiLogType() {
        return iLogType;
    }

    private Integer iCopyType;
    public void setiCopyType(Integer iCopyType){
        this.iCopyType=iCopyType;
    }
   public Integer getiCopyType() {
        return iCopyType;
    }

    private Integer iCopyId;
    public void setiCopyId(Integer iCopyId){
        this.iCopyId=iCopyId;
    }
   public Integer getiCopyId() {
        return iCopyId;
    }

    private String vCopyName;
    public void setvCopyName(String vCopyName){
        this.vCopyName=vCopyName;
    }
   public String getvCopyName() {
        return vCopyName;
    }

    private Integer iTeamId;
    public void setiTeamId(Integer iTeamId){
        this.iTeamId=iTeamId;
    }
   public Integer getiTeamId() {
        return iTeamId;
    }

    private Integer iCopyLevel;
    public void setiCopyLevel(Integer iCopyLevel){
        this.iCopyLevel=iCopyLevel;
    }
   public Integer getiCopyLevel() {
        return iCopyLevel;
    }

    private Integer iCopystar;
    public void setiCopystar(Integer iCopystar){
        this.iCopystar=iCopystar;
    }
   public Integer getiCopystar() {
        return iCopystar;
    }

    private Integer iCopyLevelId;
    public void setiCopyLevelId(Integer iCopyLevelId){
        this.iCopyLevelId=iCopyLevelId;
    }
   public Integer getiCopyLevelId() {
        return iCopyLevelId;
    }

    private Integer iTrunkId;
    public void setiTrunkId(Integer iTrunkId){
        this.iTrunkId=iTrunkId;
    }
   public Integer getiTrunkId() {
        return iTrunkId;
    }

    private Integer iReviveType;
    public void setiReviveType(Integer iReviveType){
        this.iReviveType=iReviveType;
    }
   public Integer getiReviveType() {
        return iReviveType;
    }

    private Integer iObjId;
    public void setiObjId(Integer iObjId){
        this.iObjId=iObjId;
    }
   public Integer getiObjId() {
        return iObjId;
    }

    private String jObjProperty;
    public void setjObjProperty(String jObjProperty){
        this.jObjProperty=jObjProperty;
    }
   public String getjObjProperty() {
        return jObjProperty;
    }

    private String jMoney;
    public void setjMoney(String jMoney){
        this.jMoney=jMoney;
    }
   public String getjMoney() {
        return jMoney;
    }

    public CopyRevive parseFromLogFile(String[] args, String GameId, String AccountType, String WorldId){
        return new CopyRevive(GameId, AccountType, WorldId, args[1],args[2],args[3],args[4],args[5],args[6],args[7],args[8],args[9],args[10],args[11],args[12],args[13],args[14],args[15],args[16],args[17],args[18],args[19],args[20],args[21],args[22],args[23],args[24],args[25],args[26],args[27],args[28]);
    }

    public CopyRevive(String GameId, String AccountType, String WorldId, String dtEventTime,String iEventId,String vVersionId,String vUin,String iRoleId,String vRoleName,String iRoleJob,String iRoleGender,String iRoleLevel,String iRoleVipLevel,String iRoleReputationLevel,String vRoleElse1,String vRoleElse2,String iRoleExperience,String iRoleSword,String iLogType,String iCopyType,String iCopyId,String vCopyName,String iTeamId,String iCopyLevel,String iCopystar,String iCopyLevelId,String iTrunkId,String iReviveType,String iObjId,String jObjProperty,String jMoney){
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
        this.iRoleExperience = Integer.valueOf(iRoleExperience);
        this.iRoleSword = Integer.valueOf(iRoleSword);
        this.iLogType = Integer.valueOf(iLogType);
        this.iCopyType = Integer.valueOf(iCopyType);
        this.iCopyId = Integer.valueOf(iCopyId);
        this.vCopyName = String.valueOf(vCopyName);
        this.iTeamId = Integer.valueOf(iTeamId);
        this.iCopyLevel = Integer.valueOf(iCopyLevel);
        this.iCopystar = Integer.valueOf(iCopystar);
        this.iCopyLevelId = Integer.valueOf(iCopyLevelId);
        this.iTrunkId = Integer.valueOf(iTrunkId);
        this.iReviveType = Integer.valueOf(iReviveType);
        this.iObjId = Integer.valueOf(iObjId);
        this.jObjProperty = String.valueOf(jObjProperty);
        this.jMoney = String.valueOf(jMoney);
    }
}
