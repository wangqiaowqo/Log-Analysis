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

    private Long iPetType;
    public void setiPetType(Long iPetType){
        this.iPetType=iPetType;
    }
   public Long getiPetType() {
        return iPetType;
    }

    private Long iPetID;
    public void setiPetID(Long iPetID){
        this.iPetID=iPetID;
    }
   public Long getiPetID() {
        return iPetID;
    }

    private String vPetName;
    public void setvPetName(String vPetName){
        this.vPetName=vPetName;
    }
   public String getvPetName() {
        return vPetName;
    }

    private Long iPetStar;
    public void setiPetStar(Long iPetStar){
        this.iPetStar=iPetStar;
    }
   public Long getiPetStar() {
        return iPetStar;
    }

    private Long iPetlevel;
    public void setiPetlevel(Long iPetlevel){
        this.iPetlevel=iPetlevel;
    }
   public Long getiPetlevel() {
        return iPetlevel;
    }

    private String jPetAttribute;
    public void setjPetAttribute(String jPetAttribute){
        this.jPetAttribute=jPetAttribute;
    }
   public String getjPetAttribute() {
        return jPetAttribute;
    }

    private Long iPetWay;
    public void setiPetWay(Long iPetWay){
        this.iPetWay=iPetWay;
    }
   public Long getiPetWay() {
        return iPetWay;
    }

    private String jItemCost;
    public void setjItemCost(String jItemCost){
        this.jItemCost=jItemCost;
    }
   public String getjItemCost() {
        return jItemCost;
    }

    private Long iMoneyType;
    public void setiMoneyType(Long iMoneyType){
        this.iMoneyType=iMoneyType;
    }
   public Long getiMoneyType() {
        return iMoneyType;
    }

    private Long iPayBefore;
    public void setiPayBefore(Long iPayBefore){
        this.iPayBefore=iPayBefore;
    }
   public Long getiPayBefore() {
        return iPayBefore;
    }

    private Long iPayDelta;
    public void setiPayDelta(Long iPayDelta){
        this.iPayDelta=iPayDelta;
    }
   public Long getiPayDelta() {
        return iPayDelta;
    }

    private Long iPayAfter;
    public void setiPayAfter(Long iPayAfter){
        this.iPayAfter=iPayAfter;
    }
   public Long getiPayAfter() {
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
        this.iPetType = Long.valueOf(iPetType);
        this.iPetID = Long.valueOf(iPetID);
        this.vPetName = String.valueOf(vPetName);
        this.iPetStar = Long.valueOf(iPetStar);
        this.iPetlevel = Long.valueOf(iPetlevel);
        this.jPetAttribute = String.valueOf(jPetAttribute);
        this.iPetWay = Long.valueOf(iPetWay);
        this.jItemCost = String.valueOf(jItemCost);
        this.iMoneyType = Long.valueOf(iMoneyType);
        this.iPayBefore = Long.valueOf(iPayBefore);
        this.iPayDelta = Long.valueOf(iPayDelta);
        this.iPayAfter = Long.valueOf(iPayAfter);
    }
}
