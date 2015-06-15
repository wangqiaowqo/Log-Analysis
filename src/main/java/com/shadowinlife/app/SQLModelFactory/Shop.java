package com.shadowinlife.app.SQLModelFactory;

import java.io.*;
import java.sql.*;

public class Shop extends BaseBean implements Serializable {
    public Shop() {
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

    private String vClientIp;
    public void setvClientIp(String vClientIp){
        this.vClientIp=vClientIp;
    }
   public String getvClientIp() {
        return vClientIp;
    }

    private Integer iTradeType;
    public void setiTradeType(Integer iTradeType){
        this.iTradeType=iTradeType;
    }
   public Integer getiTradeType() {
        return iTradeType;
    }

    private String vReceiverUin;
    public void setvReceiverUin(String vReceiverUin){
        this.vReceiverUin=vReceiverUin;
    }
   public String getvReceiverUin() {
        return vReceiverUin;
    }

    private Integer iReceiverRoleId;
    public void setiReceiverRoleId(Integer iReceiverRoleId){
        this.iReceiverRoleId=iReceiverRoleId;
    }
   public Integer getiReceiverRoleId() {
        return iReceiverRoleId;
    }

    private Integer iShopType;
    public void setiShopType(Integer iShopType){
        this.iShopType=iShopType;
    }
   public Integer getiShopType() {
        return iShopType;
    }

    private Integer iShopID;
    public void setiShopID(Integer iShopID){
        this.iShopID=iShopID;
    }
   public Integer getiShopID() {
        return iShopID;
    }

    private Integer iCostType;
    public void setiCostType(Integer iCostType){
        this.iCostType=iCostType;
    }
   public Integer getiCostType() {
        return iCostType;
    }

    private Integer iCostWay;
    public void setiCostWay(Integer iCostWay){
        this.iCostWay=iCostWay;
    }
   public Integer getiCostWay() {
        return iCostWay;
    }

    private Integer iCost;
    public void setiCost(Integer iCost){
        this.iCost=iCost;
    }
   public Integer getiCost() {
        return iCost;
    }

    private Integer iRemain;
    public void setiRemain(Integer iRemain){
        this.iRemain=iRemain;
    }
   public Integer getiRemain() {
        return iRemain;
    }

    private Integer iGoodsType;
    public void setiGoodsType(Integer iGoodsType){
        this.iGoodsType=iGoodsType;
    }
   public Integer getiGoodsType() {
        return iGoodsType;
    }

    private Integer iGoodsPage;
    public void setiGoodsPage(Integer iGoodsPage){
        this.iGoodsPage=iGoodsPage;
    }
   public Integer getiGoodsPage() {
        return iGoodsPage;
    }

    private Integer iGoodsId;
    public void setiGoodsId(Integer iGoodsId){
        this.iGoodsId=iGoodsId;
    }
   public Integer getiGoodsId() {
        return iGoodsId;
    }

    private Integer iGoodsNum;
    public void setiGoodsNum(Integer iGoodsNum){
        this.iGoodsNum=iGoodsNum;
    }
   public Integer getiGoodsNum() {
        return iGoodsNum;
    }

    private Integer iRebateType;
    public void setiRebateType(Integer iRebateType){
        this.iRebateType=iRebateType;
    }
   public Integer getiRebateType() {
        return iRebateType;
    }

    private Integer iRebateRate;
    public void setiRebateRate(Integer iRebateRate){
        this.iRebateRate=iRebateRate;
    }
   public Integer getiRebateRate() {
        return iRebateRate;
    }

    private Integer iItemGuid;
    public void setiItemGuid(Integer iItemGuid){
        this.iItemGuid=iItemGuid;
    }
   public Integer getiItemGuid() {
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
        this.vClientIp = String.valueOf(vClientIp);
        this.iTradeType = Integer.valueOf(iTradeType);
        this.vReceiverUin = String.valueOf(vReceiverUin);
        this.iReceiverRoleId = Integer.valueOf(iReceiverRoleId);
        this.iShopType = Integer.valueOf(iShopType);
        this.iShopID = Integer.valueOf(iShopID);
        this.iCostType = Integer.valueOf(iCostType);
        this.iCostWay = Integer.valueOf(iCostWay);
        this.iCost = Integer.valueOf(iCost);
        this.iRemain = Integer.valueOf(iRemain);
        this.iGoodsType = Integer.valueOf(iGoodsType);
        this.iGoodsPage = Integer.valueOf(iGoodsPage);
        this.iGoodsId = Integer.valueOf(iGoodsId);
        this.iGoodsNum = Integer.valueOf(iGoodsNum);
        this.iRebateType = Integer.valueOf(iRebateType);
        this.iRebateRate = Integer.valueOf(iRebateRate);
        this.iItemGuid = Integer.valueOf(iItemGuid);
        this.vReMarks = String.valueOf(vReMarks);
        this.vLoginWay = String.valueOf(vLoginWay);
    }
}
