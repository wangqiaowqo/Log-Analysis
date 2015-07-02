package com.shadowinlife.app.SQLModelFactory;
import java.io.*;
import java.sql.*;

public class CopyMonsterKill extends BaseBean implements Serializable {
    public CopyMonsterKill() {
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

    private Long iCopyLevelId;
    public void setiCopyLevelId(Long iCopyLevelId){
        this.iCopyLevelId=iCopyLevelId;
    }
   public Long getiCopyLevelId() {
        return iCopyLevelId;
    }

    private Long iCopyTrunkId;
    public void setiCopyTrunkId(Long iCopyTrunkId){
        this.iCopyTrunkId=iCopyTrunkId;
    }
   public Long getiCopyTrunkId() {
        return iCopyTrunkId;
    }

    private Long iMonsterId;
    public void setiMonsterId(Long iMonsterId){
        this.iMonsterId=iMonsterId;
    }
   public Long getiMonsterId() {
        return iMonsterId;
    }

    private Timestamp dtEnterTime;
    public void setdtEnterTime(Timestamp dtEnterTime){
        this.dtEnterTime=dtEnterTime;
    }
   public Timestamp getdtEnterTime() {
        return dtEnterTime;
    }

    public CopyMonsterKill parseFromLogFile(String[] args, String GameId, String AccountType, String WorldId){
        return new CopyMonsterKill(GameId, AccountType, WorldId, args[1],args[2],args[3],args[4],args[5],args[6],args[7],args[8],args[9],args[10],args[11],args[12],args[13],args[14],args[15],args[16],args[17],args[18],args[19],args[20],args[21]);
    }

    public CopyMonsterKill(String GameId, String AccountType, String WorldId, String dtEventTime,String iEventId,String vVersionId,String vUin,String iRoleId,String vRoleName,String iRoleJob,String iRoleGender,String iRoleLevel,String iRoleVipLevel,String iRoleReputationLevel,String vRoleElse1,String vRoleElse2,String iRoleExperience,String iRoleSword,String iCopyType,String iCopyId,String iCopyLevelId,String iCopyTrunkId,String iMonsterId,String dtEnterTime){
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
        this.iRoleExperience = Long.valueOf(iRoleExperience);
        this.iRoleSword = Long.valueOf(iRoleSword);
        this.iCopyType = Long.valueOf(iCopyType);
        this.iCopyId = Long.valueOf(iCopyId);
        this.iCopyLevelId = Long.valueOf(iCopyLevelId);
        this.iCopyTrunkId = Long.valueOf(iCopyTrunkId);
        this.iMonsterId = Long.valueOf(iMonsterId);
        this.dtEnterTime = Timestamp.valueOf(dtEnterTime);
    }
}
