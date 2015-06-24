package com.shadowinlife.app.SQLModelFactory;

import java.io.*;
import java.sql.*;

public class PetGain extends BaseBean implements Serializable {
    public PetGain() {
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

    private Integer iPetType;
    public void setiPetType(Integer iPetType){
        this.iPetType=iPetType;
    }
   public Integer getiPetType() {
        return iPetType;
    }

    private Integer iPetID;
    public void setiPetID(Integer iPetID){
        this.iPetID=iPetID;
    }
   public Integer getiPetID() {
        return iPetID;
    }

    private String vPetName;
    public void setvPetName(String vPetName){
        this.vPetName=vPetName;
    }
   public String getvPetName() {
        return vPetName;
    }

    private Integer iPetStar;
    public void setiPetStar(Integer iPetStar){
        this.iPetStar=iPetStar;
    }
   public Integer getiPetStar() {
        return iPetStar;
    }

    private Integer iPetlevel;
    public void setiPetlevel(Integer iPetlevel){
        this.iPetlevel=iPetlevel;
    }
   public Integer getiPetlevel() {
        return iPetlevel;
    }

    private String jPetAttribute;
    public void setjPetAttribute(String jPetAttribute){
        this.jPetAttribute=jPetAttribute;
    }
   public String getjPetAttribute() {
        return jPetAttribute;
    }

    private Integer iPetWay;
    public void setiPetWay(Integer iPetWay){
        this.iPetWay=iPetWay;
    }
   public Integer getiPetWay() {
        return iPetWay;
    }

    private String jItemCost;
    public void setjItemCost(String jItemCost){
        this.jItemCost=jItemCost;
    }
   public String getjItemCost() {
        return jItemCost;
    }

    private Integer iMoneyType;
    public void setiMoneyType(Integer iMoneyType){
        this.iMoneyType=iMoneyType;
    }
   public Integer getiMoneyType() {
        return iMoneyType;
    }

    private Integer iPayBefore;
    public void setiPayBefore(Integer iPayBefore){
        this.iPayBefore=iPayBefore;
    }
   public Integer getiPayBefore() {
        return iPayBefore;
    }

    private Integer iPayDelta;
    public void setiPayDelta(Integer iPayDelta){
        this.iPayDelta=iPayDelta;
    }
   public Integer getiPayDelta() {
        return iPayDelta;
    }

    private Integer iPayAfter;
    public void setiPayAfter(Integer iPayAfter){
        this.iPayAfter=iPayAfter;
    }
   public Integer getiPayAfter() {
        return iPayAfter;
    }

    public PetGain parseFromLogFile(String[] args, String GameId, String AccountType, String WorldId){
        return new PetGain(GameId, AccountType, WorldId, args[1],args[2],args[3],args[4],args[5],args[6],args[7],args[8],args[9],args[10],args[11],args[12],args[13],args[14],args[15],args[16],args[17],args[18],args[19],args[20],args[21],args[22],args[23],args[24],args[25],args[26],args[27]);
    }

    public PetGain(String GameId, String AccountType, String WorldId, String dtEventTime,String iEventId,String vVersionId,String vUin,String iRoleId,String vRoleName,String iRoleJob,String iRoleGender,String iRoleLevel,String iRoleVipLevel,String iRoleReputationLevel,String vRoleElse1,String vRoleElse2,String iRoleExperience,String iRoleSword,String iPetType,String iPetID,String vPetName,String iPetStar,String iPetlevel,String jPetAttribute,String iPetWay,String jItemCost,String iMoneyType,String iPayBefore,String iPayDelta,String iPayAfter){
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
        this.iPetType = Integer.valueOf(iPetType);
        this.iPetID = Integer.valueOf(iPetID);
        this.vPetName = String.valueOf(vPetName);
        this.iPetStar = Integer.valueOf(iPetStar);
        this.iPetlevel = Integer.valueOf(iPetlevel);
        this.jPetAttribute = String.valueOf(jPetAttribute);
        this.iPetWay = Integer.valueOf(iPetWay);
        this.jItemCost = String.valueOf(jItemCost);
        this.iMoneyType = Integer.valueOf(iMoneyType);
        this.iPayBefore = Integer.valueOf(iPayBefore);
        this.iPayDelta = Integer.valueOf(iPayDelta);
        this.iPayAfter = Integer.valueOf(iPayAfter);
    }
}
