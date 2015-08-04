package com.shadowinlife.app.SQLModelFactory;
import java.io.*;
import java.sql.*;

public class TaskFinished extends BaseBean implements Serializable {
    public TaskFinished() {
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

    private Timestamp dtTaskStartTime;
    public void setdtTaskStartTime(Timestamp dtTaskStartTime){
        this.dtTaskStartTime=dtTaskStartTime;
    }
   public Timestamp getdtTaskStartTime() {
        return dtTaskStartTime;
    }

    private Long iTaskType;
    public void setiTaskType(Long iTaskType){
        this.iTaskType=iTaskType;
    }
   public Long getiTaskType() {
        return iTaskType;
    }

    private Long iTaskCircle;
    public void setiTaskCircle(Long iTaskCircle){
        this.iTaskCircle=iTaskCircle;
    }
   public Long getiTaskCircle() {
        return iTaskCircle;
    }

    private Long iTaskId;
    public void setiTaskId(Long iTaskId){
        this.iTaskId=iTaskId;
    }
   public Long getiTaskId() {
        return iTaskId;
    }

    private String vTaskName;
    public void setvTaskName(String vTaskName){
        this.vTaskName=vTaskName;
    }
   public String getvTaskName() {
        return vTaskName;
    }

    private Long iTaskStar;
    public void setiTaskStar(Long iTaskStar){
        this.iTaskStar=iTaskStar;
    }
   public Long getiTaskStar() {
        return iTaskStar;
    }

    private Long iNpcId;
    public void setiNpcId(Long iNpcId){
        this.iNpcId=iNpcId;
    }
   public Long getiNpcId() {
        return iNpcId;
    }

    private String vNpcName;
    public void setvNpcName(String vNpcName){
        this.vNpcName=vNpcName;
    }
   public String getvNpcName() {
        return vNpcName;
    }

    private Long iFlag;
    public void setiFlag(Long iFlag){
        this.iFlag=iFlag;
    }
   public Long getiFlag() {
        return iFlag;
    }

    private Long iItemType;
    public void setiItemType(Long iItemType){
        this.iItemType=iItemType;
    }
   public Long getiItemType() {
        return iItemType;
    }

    private Long iItemId;
    public void setiItemId(Long iItemId){
        this.iItemId=iItemId;
    }
   public Long getiItemId() {
        return iItemId;
    }

    private Long iItemNum;
    public void setiItemNum(Long iItemNum){
        this.iItemNum=iItemNum;
    }
   public Long getiItemNum() {
        return iItemNum;
    }

    private Long iItemRemain;
    public void setiItemRemain(Long iItemRemain){
        this.iItemRemain=iItemRemain;
    }
   public Long getiItemRemain() {
        return iItemRemain;
    }

    private Long iItemGuid;
    public void setiItemGuid(Long iItemGuid){
        this.iItemGuid=iItemGuid;
    }
   public Long getiItemGuid() {
        return iItemGuid;
    }

    private Long iFinshType;
    public void setiFinshType(Long iFinshType){
        this.iFinshType=iFinshType;
    }
   public Long getiFinshType() {
        return iFinshType;
    }

    public TaskFinished parseFromLogFile(String[] args, String GameId, String AccountType, String WorldId){
        return new TaskFinished(GameId, AccountType, WorldId, args[1],args[2],args[3],args[4],args[5],args[6],args[7],args[8],args[9],args[10],args[11],args[12],args[13],args[14],args[15],args[16],args[17],args[18],args[19],args[20],args[21],args[22],args[23],args[24],args[25],args[26],args[27],args[28],args[29],args[30]);
    }

    public TaskFinished(String GameId, String AccountType, String WorldId, String dtEventTime,String iEventId,String vVersionId,String vUin,String iRoleId,String vRoleName,String iRoleJob,String iRoleGender,String iRoleLevel,String iRoleVipLevel,String iRoleReputationLevel,String vRoleElse1,String vRoleElse2,String iRoleExperience,String iRoleSword,String dtTaskStartTime,String iTaskType,String iTaskCircle,String iTaskId,String vTaskName,String iTaskStar,String iNpcId,String vNpcName,String iFlag,String iItemType,String iItemId,String iItemNum,String iItemRemain,String iItemGuid,String iFinshType){
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
        this.dtTaskStartTime = Timestamp.valueOf(dtTaskStartTime);
        this.iTaskType = Long.valueOf(iTaskType);
        this.iTaskCircle = Long.valueOf(iTaskCircle);
        this.iTaskId = Long.valueOf(iTaskId);
        this.vTaskName = String.valueOf(vTaskName);
        this.iTaskStar = Long.valueOf(iTaskStar);
        this.iNpcId = Long.valueOf(iNpcId);
        this.vNpcName = String.valueOf(vNpcName);
        this.iFlag = Long.valueOf(iFlag);
        this.iItemType = Long.valueOf(iItemType);
        this.iItemId = Long.valueOf(iItemId);
        this.iItemNum = Long.valueOf(iItemNum);
        this.iItemRemain = Long.valueOf(iItemRemain);
        this.iItemGuid = Long.valueOf(iItemGuid);
        this.iFinshType = Long.valueOf(iFinshType);
    }
}
