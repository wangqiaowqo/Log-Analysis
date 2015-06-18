package com.shadowinlife.app.SQLModelFactory;

import java.io.*;
import java.sql.*;

public class TaskFinished extends BaseBean implements Serializable {
    public TaskFinished() {
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

    private Timestamp dtTaskStartTime;
    public void setdtTaskStartTime(Timestamp dtTaskStartTime){
        this.dtTaskStartTime=dtTaskStartTime;
    }
   public Timestamp getdtTaskStartTime() {
        return dtTaskStartTime;
    }

    private Integer iTaskType;
    public void setiTaskType(Integer iTaskType){
        this.iTaskType=iTaskType;
    }
   public Integer getiTaskType() {
        return iTaskType;
    }

    private Integer iTaskWay;
    public void setiTaskWay(Integer iTaskWay){
        this.iTaskWay=iTaskWay;
    }
   public Integer getiTaskWay() {
        return iTaskWay;
    }

    private Integer iTaskCircle;
    public void setiTaskCircle(Integer iTaskCircle){
        this.iTaskCircle=iTaskCircle;
    }
   public Integer getiTaskCircle() {
        return iTaskCircle;
    }

    private Integer iTaskId;
    public void setiTaskId(Integer iTaskId){
        this.iTaskId=iTaskId;
    }
   public Integer getiTaskId() {
        return iTaskId;
    }

    private String vTaskName;
    public void setvTaskName(String vTaskName){
        this.vTaskName=vTaskName;
    }
   public String getvTaskName() {
        return vTaskName;
    }

    private Integer iTaskStar;
    public void setiTaskStar(Integer iTaskStar){
        this.iTaskStar=iTaskStar;
    }
   public Integer getiTaskStar() {
        return iTaskStar;
    }

    private Integer iNpcId;
    public void setiNpcId(Integer iNpcId){
        this.iNpcId=iNpcId;
    }
   public Integer getiNpcId() {
        return iNpcId;
    }

    private String vNpcName;
    public void setvNpcName(String vNpcName){
        this.vNpcName=vNpcName;
    }
   public String getvNpcName() {
        return vNpcName;
    }

    private Integer iFlag;
    public void setiFlag(Integer iFlag){
        this.iFlag=iFlag;
    }
   public Integer getiFlag() {
        return iFlag;
    }

    private Integer iItemType;
    public void setiItemType(Integer iItemType){
        this.iItemType=iItemType;
    }
   public Integer getiItemType() {
        return iItemType;
    }

    private Integer iItemId;
    public void setiItemId(Integer iItemId){
        this.iItemId=iItemId;
    }
   public Integer getiItemId() {
        return iItemId;
    }

    private Integer iItemNum;
    public void setiItemNum(Integer iItemNum){
        this.iItemNum=iItemNum;
    }
   public Integer getiItemNum() {
        return iItemNum;
    }

    private Integer iItemRemain;
    public void setiItemRemain(Integer iItemRemain){
        this.iItemRemain=iItemRemain;
    }
   public Integer getiItemRemain() {
        return iItemRemain;
    }

    private Integer iItemGuid;
    public void setiItemGuid(Integer iItemGuid){
        this.iItemGuid=iItemGuid;
    }
   public Integer getiItemGuid() {
        return iItemGuid;
    }

    private Integer iFinshType;
    public void setiFinshType(Integer iFinshType){
        this.iFinshType=iFinshType;
    }
   public Integer getiFinshType() {
        return iFinshType;
    }

    public TaskFinished parseFromLogFile(String[] args, String GameId, String AccountType, String WorldId){
        return new TaskFinished(GameId, AccountType, WorldId, args[1],args[2],args[3],args[4],args[5],args[6],args[7],args[8],args[9],args[10],args[11],args[12],args[13],args[14],args[15],args[16],args[17],args[18],args[19],args[20],args[21],args[22],args[23],args[24],args[25],args[26],args[27],args[28],args[29],args[30],args[31]);
    }

    public TaskFinished(String GameId, String AccountType, String WorldId, String dtEventTime,String iEventId,String vVersionId,String vUin,String iRoleId,String vRoleName,String iRoleJob,String iRoleGender,String iRoleLevel,String iRoleVipLevel,String iRoleReputationLevel,String vRoleElse1,String vRoleElse2,String iRoleExperience,String iRoleSword,String dtTaskStartTime,String iTaskType,String iTaskWay,String iTaskCircle,String iTaskId,String vTaskName,String iTaskStar,String iNpcId,String vNpcName,String iFlag,String iItemType,String iItemId,String iItemNum,String iItemRemain,String iItemGuid,String iFinshType){
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
        this.dtTaskStartTime = Timestamp.valueOf(dtTaskStartTime);
        this.iTaskType = Integer.valueOf(iTaskType);
        this.iTaskWay = Integer.valueOf(iTaskWay);
        this.iTaskCircle = Integer.valueOf(iTaskCircle);
        this.iTaskId = Integer.valueOf(iTaskId);
        this.vTaskName = String.valueOf(vTaskName);
        this.iTaskStar = Integer.valueOf(iTaskStar);
        this.iNpcId = Integer.valueOf(iNpcId);
        this.vNpcName = String.valueOf(vNpcName);
        this.iFlag = Integer.valueOf(iFlag);
        this.iItemType = Integer.valueOf(iItemType);
        this.iItemId = Integer.valueOf(iItemId);
        this.iItemNum = Integer.valueOf(iItemNum);
        this.iItemRemain = Integer.valueOf(iItemRemain);
        this.iItemGuid = Integer.valueOf(iItemGuid);
        this.iFinshType = Integer.valueOf(iFinshType);
    }
}
