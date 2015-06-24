package com.shadowinlife.app.SQLModelFactory;

import java.io.*;
import java.sql.*;

public class BuildingUpgrade extends BaseBean implements Serializable {
    public BuildingUpgrade() {
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

    private Integer iPartyId;
    public void setiPartyId(Integer iPartyId){
        this.iPartyId=iPartyId;
    }
   public Integer getiPartyId() {
        return iPartyId;
    }

    private String vPartyName;
    public void setvPartyName(String vPartyName){
        this.vPartyName=vPartyName;
    }
   public String getvPartyName() {
        return vPartyName;
    }

    private Integer iPartyLevel;
    public void setiPartyLevel(Integer iPartyLevel){
        this.iPartyLevel=iPartyLevel;
    }
   public Integer getiPartyLevel() {
        return iPartyLevel;
    }

    private Integer iBuildingParty;
    public void setiBuildingParty(Integer iBuildingParty){
        this.iBuildingParty=iBuildingParty;
    }
   public Integer getiBuildingParty() {
        return iBuildingParty;
    }

    private Integer iUpgrateBulidingId;
    public void setiUpgrateBulidingId(Integer iUpgrateBulidingId){
        this.iUpgrateBulidingId=iUpgrateBulidingId;
    }
   public Integer getiUpgrateBulidingId() {
        return iUpgrateBulidingId;
    }

    private String vUpgrateBulidingName;
    public void setvUpgrateBulidingName(String vUpgrateBulidingName){
        this.vUpgrateBulidingName=vUpgrateBulidingName;
    }
   public String getvUpgrateBulidingName() {
        return vUpgrateBulidingName;
    }

    private Integer iBulidingLevel;
    public void setiBulidingLevel(Integer iBulidingLevel){
        this.iBulidingLevel=iBulidingLevel;
    }
   public Integer getiBulidingLevel() {
        return iBulidingLevel;
    }

    private Integer iUpgrateCostParty;
    public void setiUpgrateCostParty(Integer iUpgrateCostParty){
        this.iUpgrateCostParty=iUpgrateCostParty;
    }
   public Integer getiUpgrateCostParty() {
        return iUpgrateCostParty;
    }

    private String jUpgrateCostMoney;
    public void setjUpgrateCostMoney(String jUpgrateCostMoney){
        this.jUpgrateCostMoney=jUpgrateCostMoney;
    }
   public String getjUpgrateCostMoney() {
        return jUpgrateCostMoney;
    }

    private String jPartyLeftMoney;
    public void setjPartyLeftMoney(String jPartyLeftMoney){
        this.jPartyLeftMoney=jPartyLeftMoney;
    }
   public String getjPartyLeftMoney() {
        return jPartyLeftMoney;
    }

    private Integer iPartyLeftPolity;
    public void setiPartyLeftPolity(Integer iPartyLeftPolity){
        this.iPartyLeftPolity=iPartyLeftPolity;
    }
   public Integer getiPartyLeftPolity() {
        return iPartyLeftPolity;
    }

    private Integer iCostTime;
    public void setiCostTime(Integer iCostTime){
        this.iCostTime=iCostTime;
    }
   public Integer getiCostTime() {
        return iCostTime;
    }

    private Timestamp dtEventEndTime;
    public void setdtEventEndTime(Timestamp dtEventEndTime){
        this.dtEventEndTime=dtEventEndTime;
    }
   public Timestamp getdtEventEndTime() {
        return dtEventEndTime;
    }

    public BuildingUpgrade parseFromLogFile(String[] args, String GameId, String AccountType, String WorldId){
        return new BuildingUpgrade(GameId, AccountType, WorldId, args[1],args[2],args[3],args[4],args[5],args[6],args[7],args[8],args[9],args[10],args[11],args[12],args[13],args[14],args[15],args[16],args[17],args[18],args[19],args[20],args[21],args[22],args[23],args[24],args[25],args[26],args[27],args[28]);
    }

    public BuildingUpgrade(String GameId, String AccountType, String WorldId, String dtEventTime,String iEventId,String vVersionId,String vUin,String iRoleId,String vRoleName,String iRoleJob,String iRoleGender,String iRoleLevel,String iRoleVipLevel,String iRoleReputationLevel,String vRoleElse1,String vRoleElse2,String iRoleExperience,String iRoleSword,String iPartyId,String vPartyName,String iPartyLevel,String iBuildingParty,String iUpgrateBulidingId,String vUpgrateBulidingName,String iBulidingLevel,String iUpgrateCostParty,String jUpgrateCostMoney,String jPartyLeftMoney,String iPartyLeftPolity,String iCostTime,String dtEventEndTime){
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
        this.iPartyId = Integer.valueOf(iPartyId);
        this.vPartyName = String.valueOf(vPartyName);
        this.iPartyLevel = Integer.valueOf(iPartyLevel);
        this.iBuildingParty = Integer.valueOf(iBuildingParty);
        this.iUpgrateBulidingId = Integer.valueOf(iUpgrateBulidingId);
        this.vUpgrateBulidingName = String.valueOf(vUpgrateBulidingName);
        this.iBulidingLevel = Integer.valueOf(iBulidingLevel);
        this.iUpgrateCostParty = Integer.valueOf(iUpgrateCostParty);
        this.jUpgrateCostMoney = String.valueOf(jUpgrateCostMoney);
        this.jPartyLeftMoney = String.valueOf(jPartyLeftMoney);
        this.iPartyLeftPolity = Integer.valueOf(iPartyLeftPolity);
        this.iCostTime = Integer.valueOf(iCostTime);
        this.dtEventEndTime = Timestamp.valueOf(dtEventEndTime);
    }
}
