package com.shadowinlife.app.SQLModelFactory;
import java.io.*;
import java.sql.*;

public class PartyBankProfit extends BaseBean implements Serializable {
    public PartyBankProfit() {
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

    private Integer iPartyLevel;
    public void setiPartyLevel(Integer iPartyLevel){
        this.iPartyLevel=iPartyLevel;
    }
   public Integer getiPartyLevel() {
        return iPartyLevel;
    }

    private String jProfit;
    public void setjProfit(String jProfit){
        this.jProfit=jProfit;
    }
   public String getjProfit() {
        return jProfit;
    }

    public PartyBankProfit parseFromLogFile(String[] args, String GameId, String AccountType, String WorldId){
        return new PartyBankProfit(GameId, AccountType, WorldId, args[1],args[2],args[3],args[4],args[5],args[6],args[7],args[8],args[9],args[10],args[11],args[12],args[13],args[14],args[15],args[16],args[17],args[18],args[19]);
    }

    public PartyBankProfit(String GameId, String AccountType, String WorldId, String dtEventTime,String iEventId,String vVersionId,String vUin,String iRoleId,String vRoleName,String iRoleJob,String iRoleGender,String iRoleLevel,String iRoleVipLevel,String iRoleReputationLevel,String vRoleElse1,String vRoleElse2,String iRoleExperience,String iRoleSword,String iPartyId,String vPartyName,String iPartyLevel,String jProfit){
        this.iGameId = Long.valueOf(GameId);
        this.iAccountType = Long.valueOf(AccountType);
        this.iWorldId = Long.valueOf(WorldId);
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
        this.iPartyId = Long.valueOf(iPartyId);
        this.vPartyName = String.valueOf(vPartyName);
        this.iPartyLevel = Integer.valueOf(iPartyLevel);
        this.jProfit = String.valueOf(jProfit);
    }
}
