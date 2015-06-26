package com.shadowinlife.app.SQLModelFactory;
import java.io.*;
import java.sql.*;

public class CopyExit extends BaseBean implements Serializable {
    public CopyExit() {
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

    private Long iRoleExperience;
    public void setiRoleExperience(Long iRoleExperience){
        this.iRoleExperience=iRoleExperience;
    }
   public Long getiRoleExperience() {
        return iRoleExperience;
    }

    private Long iRoleSword;
    public void setiRoleSword(Long iRoleSword){
        this.iRoleSword=iRoleSword;
    }
   public Long getiRoleSword() {
        return iRoleSword;
    }

    private Long iLogType;
    public void setiLogType(Long iLogType){
        this.iLogType=iLogType;
    }
   public Long getiLogType() {
        return iLogType;
    }

    private Long iExitType;
    public void setiExitType(Long iExitType){
        this.iExitType=iExitType;
    }
   public Long getiExitType() {
        return iExitType;
    }

    private Long iCopyType;
    public void setiCopyType(Long iCopyType){
        this.iCopyType=iCopyType;
    }
   public Long getiCopyType() {
        return iCopyType;
    }

    private Long iCopyId;
    public void setiCopyId(Long iCopyId){
        this.iCopyId=iCopyId;
    }
   public Long getiCopyId() {
        return iCopyId;
    }

    private String vCopyName;
    public void setvCopyName(String vCopyName){
        this.vCopyName=vCopyName;
    }
   public String getvCopyName() {
        return vCopyName;
    }

    private Long iTeamId;
    public void setiTeamId(Long iTeamId){
        this.iTeamId=iTeamId;
    }
   public Long getiTeamId() {
        return iTeamId;
    }

    private Long iCopyLevel;
    public void setiCopyLevel(Long iCopyLevel){
        this.iCopyLevel=iCopyLevel;
    }
   public Long getiCopyLevel() {
        return iCopyLevel;
    }

    private Long iCopystar;
    public void setiCopystar(Long iCopystar){
        this.iCopystar=iCopystar;
    }
   public Long getiCopystar() {
        return iCopystar;
    }

    private Long iCopyLevelId;
    public void setiCopyLevelId(Long iCopyLevelId){
        this.iCopyLevelId=iCopyLevelId;
    }
   public Long getiCopyLevelId() {
        return iCopyLevelId;
    }

    private Long iTrunkId;
    public void setiTrunkId(Long iTrunkId){
        this.iTrunkId=iTrunkId;
    }
   public Long getiTrunkId() {
        return iTrunkId;
    }

    private Long iTotalDamage;
    public void setiTotalDamage(Long iTotalDamage){
        this.iTotalDamage=iTotalDamage;
    }
   public Long getiTotalDamage() {
        return iTotalDamage;
    }

    private String jPetHurt;
    public void setjPetHurt(String jPetHurt){
        this.jPetHurt=jPetHurt;
    }
   public String getjPetHurt() {
        return jPetHurt;
    }

    private String jMoney;
    public void setjMoney(String jMoney){
        this.jMoney=jMoney;
    }
   public String getjMoney() {
        return jMoney;
    }

    private String jItem;
    public void setjItem(String jItem){
        this.jItem=jItem;
    }
   public String getjItem() {
        return jItem;
    }

    public CopyExit parseFromLogFile(String[] args, String GameId, String AccountType, String WorldId){
        return new CopyExit(GameId, AccountType, WorldId, args[1],args[2],args[3],args[4],args[5],args[6],args[7],args[8],args[9],args[10],args[11],args[12],args[13],args[14],args[15],args[16],args[17],args[18],args[19],args[20],args[21],args[22],args[23],args[24],args[25],args[26],args[27],args[28],args[29]);
    }

    public CopyExit(String GameId, String AccountType, String WorldId, String dtEventTime,String iEventId,String vVersionId,String vUin,String iRoleId,String vRoleName,String iRoleJob,String iRoleGender,String iRoleLevel,String iRoleVipLevel,String iRoleReputationLevel,String vRoleElse1,String vRoleElse2,String iRoleExperience,String iRoleSword,String iLogType,String iExitType,String iCopyType,String iCopyId,String vCopyName,String iTeamId,String iCopyLevel,String iCopystar,String iCopyLevelId,String iTrunkId,String iTotalDamage,String jPetHurt,String jMoney,String jItem){
        this.iGameId = Integer.valueOf(GameId);
        this.iAccountType = Integer.valueOf(AccountType);
        this.iWorldId = Integer.valueOf(WorldId);
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
        this.iRoleExperience = Long.valueOf(iRoleExperience);
        this.iRoleSword = Long.valueOf(iRoleSword);
        this.iLogType = Long.valueOf(iLogType);
        this.iExitType = Long.valueOf(iExitType);
        this.iCopyType = Long.valueOf(iCopyType);
        this.iCopyId = Long.valueOf(iCopyId);
        this.vCopyName = String.valueOf(vCopyName);
        this.iTeamId = Long.valueOf(iTeamId);
        this.iCopyLevel = Long.valueOf(iCopyLevel);
        this.iCopystar = Long.valueOf(iCopystar);
        this.iCopyLevelId = Long.valueOf(iCopyLevelId);
        this.iTrunkId = Long.valueOf(iTrunkId);
        this.iTotalDamage = Long.valueOf(iTotalDamage);
        this.jPetHurt = String.valueOf(jPetHurt);
        this.jMoney = String.valueOf(jMoney);
        this.jItem = String.valueOf(jItem);
    }
}
