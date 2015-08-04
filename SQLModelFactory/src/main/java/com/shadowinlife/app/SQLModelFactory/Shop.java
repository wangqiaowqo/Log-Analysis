package com.shadowinlife.app.SQLModelFactory;
import java.io.*;
import java.sql.*;

public class Shop extends BaseBean implements Serializable {
    public Shop() {
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

    private String vClientIp;
    public void setvClientIp(String vClientIp){
        this.vClientIp=vClientIp;
    }
   public String getvClientIp() {
        return vClientIp;
    }

    private Long iTradeType;
    public void setiTradeType(Long iTradeType){
        this.iTradeType=iTradeType;
    }
   public Long getiTradeType() {
        return iTradeType;
    }

    private String vReceiverUin;
    public void setvReceiverUin(String vReceiverUin){
        this.vReceiverUin=vReceiverUin;
    }
   public String getvReceiverUin() {
        return vReceiverUin;
    }

    private Long iReceiverRoleId;
    public void setiReceiverRoleId(Long iReceiverRoleId){
        this.iReceiverRoleId=iReceiverRoleId;
    }
   public Long getiReceiverRoleId() {
        return iReceiverRoleId;
    }

    private Long iShopType;
    public void setiShopType(Long iShopType){
        this.iShopType=iShopType;
    }
   public Long getiShopType() {
        return iShopType;
    }

    private Long iShopID;
    public void setiShopID(Long iShopID){
        this.iShopID=iShopID;
    }
   public Long getiShopID() {
        return iShopID;
    }

    private Long iCostType;
    public void setiCostType(Long iCostType){
        this.iCostType=iCostType;
    }
   public Long getiCostType() {
        return iCostType;
    }

    private Long iCostWay;
    public void setiCostWay(Long iCostWay){
        this.iCostWay=iCostWay;
    }
   public Long getiCostWay() {
        return iCostWay;
    }

    private Long iCost;
    public void setiCost(Long iCost){
        this.iCost=iCost;
    }
   public Long getiCost() {
        return iCost;
    }

    private Long iRemain;
    public void setiRemain(Long iRemain){
        this.iRemain=iRemain;
    }
   public Long getiRemain() {
        return iRemain;
    }

    private Long iGoodsType;
    public void setiGoodsType(Long iGoodsType){
        this.iGoodsType=iGoodsType;
    }
   public Long getiGoodsType() {
        return iGoodsType;
    }

    private Long iGoodsPage;
    public void setiGoodsPage(Long iGoodsPage){
        this.iGoodsPage=iGoodsPage;
    }
   public Long getiGoodsPage() {
        return iGoodsPage;
    }

    private Long iGoodsId;
    public void setiGoodsId(Long iGoodsId){
        this.iGoodsId=iGoodsId;
    }
   public Long getiGoodsId() {
        return iGoodsId;
    }

    private Long iGoodsNum;
    public void setiGoodsNum(Long iGoodsNum){
        this.iGoodsNum=iGoodsNum;
    }
   public Long getiGoodsNum() {
        return iGoodsNum;
    }

    private Long iRebateType;
    public void setiRebateType(Long iRebateType){
        this.iRebateType=iRebateType;
    }
   public Long getiRebateType() {
        return iRebateType;
    }

    private Long iRebateRate;
    public void setiRebateRate(Long iRebateRate){
        this.iRebateRate=iRebateRate;
    }
   public Long getiRebateRate() {
        return iRebateRate;
    }

    private Long iItemGuid;
    public void setiItemGuid(Long iItemGuid){
        this.iItemGuid=iItemGuid;
    }
   public Long getiItemGuid() {
        return iItemGuid;
    }

    private String vReMarks;
    public void setvReMarks(String vReMarks){
        this.vReMarks=vReMarks;
    }
   public String getvReMarks() {
        return vReMarks;
    }

    private String vLoginWay;
    public void setvLoginWay(String vLoginWay){
        this.vLoginWay=vLoginWay;
    }
   public String getvLoginWay() {
        return vLoginWay;
    }

    public Shop parseFromLogFile(String[] args, String GameId, String AccountType, String WorldId){
        return new Shop(GameId, AccountType, WorldId, args[1],args[2],args[3],args[4],args[5],args[6],args[7],args[8],args[9],args[10],args[11],args[12],args[13],args[14],args[15],args[16],args[17],args[18],args[19],args[20],args[21],args[22],args[23],args[24],args[25],args[26],args[27],args[28],args[29],args[30],args[31],args[32],args[33],args[34]);
    }

    public Shop(String GameId, String AccountType, String WorldId, String dtEventTime,String iEventId,String vVersionId,String vUin,String iRoleId,String vRoleName,String iRoleJob,String iRoleGender,String iRoleLevel,String iRoleVipLevel,String iRoleReputationLevel,String vRoleElse1,String vRoleElse2,String iRoleExperience,String iRoleSword,String vClientIp,String iTradeType,String vReceiverUin,String iReceiverRoleId,String iShopType,String iShopID,String iCostType,String iCostWay,String iCost,String iRemain,String iGoodsType,String iGoodsPage,String iGoodsId,String iGoodsNum,String iRebateType,String iRebateRate,String iItemGuid,String vReMarks,String vLoginWay){
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
        this.vClientIp = String.valueOf(vClientIp);
        this.iTradeType = Long.valueOf(iTradeType);
        this.vReceiverUin = String.valueOf(vReceiverUin);
        this.iReceiverRoleId = Long.valueOf(iReceiverRoleId);
        this.iShopType = Long.valueOf(iShopType);
        this.iShopID = Long.valueOf(iShopID);
        this.iCostType = Long.valueOf(iCostType);
        this.iCostWay = Long.valueOf(iCostWay);
        this.iCost = Long.valueOf(iCost);
        this.iRemain = Long.valueOf(iRemain);
        this.iGoodsType = Long.valueOf(iGoodsType);
        this.iGoodsPage = Long.valueOf(iGoodsPage);
        this.iGoodsId = Long.valueOf(iGoodsId);
        this.iGoodsNum = Long.valueOf(iGoodsNum);
        this.iRebateType = Long.valueOf(iRebateType);
        this.iRebateRate = Long.valueOf(iRebateRate);
        this.iItemGuid = Long.valueOf(iItemGuid);
        this.vReMarks = String.valueOf(vReMarks);
        this.vLoginWay = String.valueOf(vLoginWay);
    }
}
