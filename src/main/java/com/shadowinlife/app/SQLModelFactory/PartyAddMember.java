package com.shadowinlife.app.SQLModelFactory;
import java.io.*;
import java.sql.*;

public class PartyAddMember extends BaseBean implements Serializable {
    public PartyAddMember() {
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

    private Long iFlag;
    public void setiFlag(Long iFlag){
        this.iFlag=iFlag;
    }
   public Long getiFlag() {
        return iFlag;
    }

    private Long iFlagType;
    public void setiFlagType(Long iFlagType){
        this.iFlagType=iFlagType;
    }
   public Long getiFlagType() {
        return iFlagType;
    }

    private String vOtherUin;
    public void setvOtherUin(String vOtherUin){
        this.vOtherUin=vOtherUin;
    }
   public String getvOtherUin() {
        return vOtherUin;
    }

    private Long iOtherRoleId;
    public void setiOtherRoleId(Long iOtherRoleId){
        this.iOtherRoleId=iOtherRoleId;
    }
   public Long getiOtherRoleId() {
        return iOtherRoleId;
    }

    private String vOtherRoleName;
    public void setvOtherRoleName(String vOtherRoleName){
        this.vOtherRoleName=vOtherRoleName;
    }
   public String getvOtherRoleName() {
        return vOtherRoleName;
    }

    private Long iOtherRoleJob;
    public void setiOtherRoleJob(Long iOtherRoleJob){
        this.iOtherRoleJob=iOtherRoleJob;
    }
   public Long getiOtherRoleJob() {
        return iOtherRoleJob;
    }

    private Long iOtherRoleGender;
    public void setiOtherRoleGender(Long iOtherRoleGender){
        this.iOtherRoleGender=iOtherRoleGender;
    }
   public Long getiOtherRoleGender() {
        return iOtherRoleGender;
    }

    private Long iOtherRoleLevel;
    public void setiOtherRoleLevel(Long iOtherRoleLevel){
        this.iOtherRoleLevel=iOtherRoleLevel;
    }
   public Long getiOtherRoleLevel() {
        return iOtherRoleLevel;
    }

    private Long iOtherRoleVipLevel;
    public void setiOtherRoleVipLevel(Long iOtherRoleVipLevel){
        this.iOtherRoleVipLevel=iOtherRoleVipLevel;
    }
   public Long getiOtherRoleVipLevel() {
        return iOtherRoleVipLevel;
    }

    private Long iOtherRoleReputationLevel;
    public void setiOtherRoleReputationLevel(Long iOtherRoleReputationLevel){
        this.iOtherRoleReputationLevel=iOtherRoleReputationLevel;
    }
   public Long getiOtherRoleReputationLevel() {
        return iOtherRoleReputationLevel;
    }

    private String vOtherRoleElse1;
    public void setvOtherRoleElse1(String vOtherRoleElse1){
        this.vOtherRoleElse1=vOtherRoleElse1;
    }
   public String getvOtherRoleElse1() {
        return vOtherRoleElse1;
    }

    private String vOtherRoleElse2;
    public void setvOtherRoleElse2(String vOtherRoleElse2){
        this.vOtherRoleElse2=vOtherRoleElse2;
    }
   public String getvOtherRoleElse2() {
        return vOtherRoleElse2;
    }

    private Long iOtherRoleExperience;
    public void setiOtherRoleExperience(Long iOtherRoleExperience){
        this.iOtherRoleExperience=iOtherRoleExperience;
    }
   public Long getiOtherRoleExperience() {
        return iOtherRoleExperience;
    }

    private Long iOtherRoleSword;
    public void setiOtherRoleSword(Long iOtherRoleSword){
        this.iOtherRoleSword=iOtherRoleSword;
    }
   public Long getiOtherRoleSword() {
        return iOtherRoleSword;
    }

    private Long iPartyId;
    public void setiPartyId(Long iPartyId){
        this.iPartyId=iPartyId;
    }
   public Long getiPartyId() {
        return iPartyId;
    }

    private String vPartyName;
    public void setvPartyName(String vPartyName){
        this.vPartyName=vPartyName;
    }
   public String getvPartyName() {
        return vPartyName;
    }

    private Long iPartyLevel;
    public void setiPartyLevel(Long iPartyLevel){
        this.iPartyLevel=iPartyLevel;
    }
   public Long getiPartyLevel() {
        return iPartyLevel;
    }

    private Long iPartyMoney;
    public void setiPartyMoney(Long iPartyMoney){
        this.iPartyMoney=iPartyMoney;
    }
   public Long getiPartyMoney() {
        return iPartyMoney;
    }

    private Long iPartyPolity;
    public void setiPartyPolity(Long iPartyPolity){
        this.iPartyPolity=iPartyPolity;
    }
   public Long getiPartyPolity() {
        return iPartyPolity;
    }

    private Long iMemeberNum;
    public void setiMemeberNum(Long iMemeberNum){
        this.iMemeberNum=iMemeberNum;
    }
   public Long getiMemeberNum() {
        return iMemeberNum;
    }

    public PartyAddMember parseFromLogFile(String[] args, String GameId, String AccountType, String WorldId){
        return new PartyAddMember(GameId, AccountType, WorldId, args[1],args[2],args[3],args[4],args[5],args[6],args[7],args[8],args[9],args[10],args[11],args[12],args[13],args[14],args[15],args[16],args[17],args[18],args[19],args[20],args[21],args[22],args[23],args[24],args[25],args[26],args[27],args[28],args[29],args[30],args[31],args[32],args[33],args[34],args[35]);
    }

    public PartyAddMember(String GameId, String AccountType, String WorldId, String dtEventTime,String iEventId,String vVersionId,String vUin,String iRoleId,String vRoleName,String iRoleJob,String iRoleGender,String iRoleLevel,String iRoleVipLevel,String iRoleReputationLevel,String vRoleElse1,String vRoleElse2,String iRoleExperience,String iRoleSword,String iFlag,String iFlagType,String vOtherUin,String iOtherRoleId,String vOtherRoleName,String iOtherRoleJob,String iOtherRoleGender,String iOtherRoleLevel,String iOtherRoleVipLevel,String iOtherRoleReputationLevel,String vOtherRoleElse1,String vOtherRoleElse2,String iOtherRoleExperience,String iOtherRoleSword,String iPartyId,String vPartyName,String iPartyLevel,String iPartyMoney,String iPartyPolity,String iMemeberNum){
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
        this.iFlag = Long.valueOf(iFlag);
        this.iFlagType = Long.valueOf(iFlagType);
        this.vOtherUin = String.valueOf(vOtherUin);
        this.iOtherRoleId = Long.valueOf(iOtherRoleId);
        this.vOtherRoleName = String.valueOf(vOtherRoleName);
        this.iOtherRoleJob = Long.valueOf(iOtherRoleJob);
        this.iOtherRoleGender = Long.valueOf(iOtherRoleGender);
        this.iOtherRoleLevel = Long.valueOf(iOtherRoleLevel);
        this.iOtherRoleVipLevel = Long.valueOf(iOtherRoleVipLevel);
        this.iOtherRoleReputationLevel = Long.valueOf(iOtherRoleReputationLevel);
        this.vOtherRoleElse1 = String.valueOf(vOtherRoleElse1);
        this.vOtherRoleElse2 = String.valueOf(vOtherRoleElse2);
        this.iOtherRoleExperience = Long.valueOf(iOtherRoleExperience);
        this.iOtherRoleSword = Long.valueOf(iOtherRoleSword);
        this.iPartyId = Long.valueOf(iPartyId);
        this.vPartyName = String.valueOf(vPartyName);
        this.iPartyLevel = Long.valueOf(iPartyLevel);
        this.iPartyMoney = Long.valueOf(iPartyMoney);
        this.iPartyPolity = Long.valueOf(iPartyPolity);
        this.iMemeberNum = Long.valueOf(iMemeberNum);
    }
}
