package com.shadowinlife.app.SQLModelFactory;

import java.io.*;
import java.sql.*;

public class MarketTrade extends BaseBean implements Serializable {
    public MarketTrade() {
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

    private String vBuyerUin;
    public void setvBuyerUin(String vBuyerUin){
        this.vBuyerUin=vBuyerUin;
    }
   public String getvBuyerUin() {
        return vBuyerUin;
    }

    private Integer iBuyerRoleId;
    public void setiBuyerRoleId(Integer iBuyerRoleId){
        this.iBuyerRoleId=iBuyerRoleId;
    }
   public Integer getiBuyerRoleId() {
        return iBuyerRoleId;
    }

    private String vBuyerName;
    public void setvBuyerName(String vBuyerName){
        this.vBuyerName=vBuyerName;
    }
   public String getvBuyerName() {
        return vBuyerName;
    }

    private Integer iBuyerJobId;
    public void setiBuyerJobId(Integer iBuyerJobId){
        this.iBuyerJobId=iBuyerJobId;
    }
   public Integer getiBuyerJobId() {
        return iBuyerJobId;
    }

    private Integer iBuyerGender;
    public void setiBuyerGender(Integer iBuyerGender){
        this.iBuyerGender=iBuyerGender;
    }
   public Integer getiBuyerGender() {
        return iBuyerGender;
    }

    private Integer iBuyerLevel;
    public void setiBuyerLevel(Integer iBuyerLevel){
        this.iBuyerLevel=iBuyerLevel;
    }
   public Integer getiBuyerLevel() {
        return iBuyerLevel;
    }

    private Integer iBuyerVipLevel;
    public void setiBuyerVipLevel(Integer iBuyerVipLevel){
        this.iBuyerVipLevel=iBuyerVipLevel;
    }
   public Integer getiBuyerVipLevel() {
        return iBuyerVipLevel;
    }

    private Integer iBuyerReputationLevel;
    public void setiBuyerReputationLevel(Integer iBuyerReputationLevel){
        this.iBuyerReputationLevel=iBuyerReputationLevel;
    }
   public Integer getiBuyerReputationLevel() {
        return iBuyerReputationLevel;
    }

    private String vBuyerRoleElse1;
    public void setvBuyerRoleElse1(String vBuyerRoleElse1){
        this.vBuyerRoleElse1=vBuyerRoleElse1;
    }
   public String getvBuyerRoleElse1() {
        return vBuyerRoleElse1;
    }

    private String vBuyerRoleElse2;
    public void setvBuyerRoleElse2(String vBuyerRoleElse2){
        this.vBuyerRoleElse2=vBuyerRoleElse2;
    }
   public String getvBuyerRoleElse2() {
        return vBuyerRoleElse2;
    }

    private Integer iMoneyBeforeTrade;
    public void setiMoneyBeforeTrade(Integer iMoneyBeforeTrade){
        this.iMoneyBeforeTrade=iMoneyBeforeTrade;
    }
   public Integer getiMoneyBeforeTrade() {
        return iMoneyBeforeTrade;
    }

    private Integer iMoneyAfterTrade;
    public void setiMoneyAfterTrade(Integer iMoneyAfterTrade){
        this.iMoneyAfterTrade=iMoneyAfterTrade;
    }
   public Integer getiMoneyAfterTrade() {
        return iMoneyAfterTrade;
    }

    private Integer iSellerType;
    public void setiSellerType(Integer iSellerType){
        this.iSellerType=iSellerType;
    }
   public Integer getiSellerType() {
        return iSellerType;
    }

    private String vSellerUin;
    public void setvSellerUin(String vSellerUin){
        this.vSellerUin=vSellerUin;
    }
   public String getvSellerUin() {
        return vSellerUin;
    }

    private Integer iSellerRoleId;
    public void setiSellerRoleId(Integer iSellerRoleId){
        this.iSellerRoleId=iSellerRoleId;
    }
   public Integer getiSellerRoleId() {
        return iSellerRoleId;
    }

    private String vSellerName;
    public void setvSellerName(String vSellerName){
        this.vSellerName=vSellerName;
    }
   public String getvSellerName() {
        return vSellerName;
    }

    private Integer iSellerJobId;
    public void setiSellerJobId(Integer iSellerJobId){
        this.iSellerJobId=iSellerJobId;
    }
   public Integer getiSellerJobId() {
        return iSellerJobId;
    }

    private Integer iSellerGender;
    public void setiSellerGender(Integer iSellerGender){
        this.iSellerGender=iSellerGender;
    }
   public Integer getiSellerGender() {
        return iSellerGender;
    }

    private Integer iSellerLevel;
    public void setiSellerLevel(Integer iSellerLevel){
        this.iSellerLevel=iSellerLevel;
    }
   public Integer getiSellerLevel() {
        return iSellerLevel;
    }

    private Integer iSellerVipLevel;
    public void setiSellerVipLevel(Integer iSellerVipLevel){
        this.iSellerVipLevel=iSellerVipLevel;
    }
   public Integer getiSellerVipLevel() {
        return iSellerVipLevel;
    }

    private Integer iSellerReputationLevel;
    public void setiSellerReputationLevel(Integer iSellerReputationLevel){
        this.iSellerReputationLevel=iSellerReputationLevel;
    }
   public Integer getiSellerReputationLevel() {
        return iSellerReputationLevel;
    }

    private String vBuyerClientIp;
    public void setvBuyerClientIp(String vBuyerClientIp){
        this.vBuyerClientIp=vBuyerClientIp;
    }
   public String getvBuyerClientIp() {
        return vBuyerClientIp;
    }

    private String vSellerClientIp;
    public void setvSellerClientIp(String vSellerClientIp){
        this.vSellerClientIp=vSellerClientIp;
    }
   public String getvSellerClientIp() {
        return vSellerClientIp;
    }

    private Integer iPayment;
    public void setiPayment(Integer iPayment){
        this.iPayment=iPayment;
    }
   public Integer getiPayment() {
        return iPayment;
    }

    private Integer iGoodsCount;
    public void setiGoodsCount(Integer iGoodsCount){
        this.iGoodsCount=iGoodsCount;
    }
   public Integer getiGoodsCount() {
        return iGoodsCount;
    }

    private Integer iItemId;
    public void setiItemId(Integer iItemId){
        this.iItemId=iItemId;
    }
   public Integer getiItemId() {
        return iItemId;
    }

    private Integer iItemType;
    public void setiItemType(Integer iItemType){
        this.iItemType=iItemType;
    }
   public Integer getiItemType() {
        return iItemType;
    }

    private Integer iItemNum;
    public void setiItemNum(Integer iItemNum){
        this.iItemNum=iItemNum;
    }
   public Integer getiItemNum() {
        return iItemNum;
    }

    private Integer iItemGuid;
    public void setiItemGuid(Integer iItemGuid){
        this.iItemGuid=iItemGuid;
    }
   public Integer getiItemGuid() {
        return iItemGuid;
    }

    public MarketTrade parseFromLogFile(String[] args, String GameId, String AccountType, String WorldId){
        return new MarketTrade(GameId, AccountType, WorldId, args[1],args[2],args[3],args[4],args[5],args[6],args[7],args[8],args[9],args[10],args[11],args[12],args[13],args[14],args[15],args[16],args[17],args[18],args[19],args[20],args[21],args[22],args[23],args[24],args[25],args[26],args[27],args[28],args[29],args[30],args[31],args[32]);
    }

    public MarketTrade(String GameId, String AccountType, String WorldId, String dtEventTime,String iEventId,String vVersionId,String vBuyerUin,String iBuyerRoleId,String vBuyerName,String iBuyerJobId,String iBuyerGender,String iBuyerLevel,String iBuyerVipLevel,String iBuyerReputationLevel,String vBuyerRoleElse1,String vBuyerRoleElse2,String iMoneyBeforeTrade,String iMoneyAfterTrade,String iSellerType,String vSellerUin,String iSellerRoleId,String vSellerName,String iSellerJobId,String iSellerGender,String iSellerLevel,String iSellerVipLevel,String iSellerReputationLevel,String vBuyerClientIp,String vSellerClientIp,String iPayment,String iGoodsCount,String iItemId,String iItemType,String iItemNum,String iItemGuid){
        this.iGameId = Integer.valueOf(GameId);
        this.iAccountType = Integer.valueOf(AccountType);
        this.iWorldId = Integer.valueOf(WorldId);
        this.dtEventTime = Timestamp.valueOf(dtEventTime);
        this.iEventId = Long.valueOf(iEventId);
        this.vVersionId = String.valueOf(vVersionId);
        this.vBuyerUin = String.valueOf(vBuyerUin);
        this.iBuyerRoleId = Integer.valueOf(iBuyerRoleId);
        this.vBuyerName = String.valueOf(vBuyerName);
        this.iBuyerJobId = Integer.valueOf(iBuyerJobId);
        this.iBuyerGender = Integer.valueOf(iBuyerGender);
        this.iBuyerLevel = Integer.valueOf(iBuyerLevel);
        this.iBuyerVipLevel = Integer.valueOf(iBuyerVipLevel);
        this.iBuyerReputationLevel = Integer.valueOf(iBuyerReputationLevel);
        this.vBuyerRoleElse1 = String.valueOf(vBuyerRoleElse1);
        this.vBuyerRoleElse2 = String.valueOf(vBuyerRoleElse2);
        this.iMoneyBeforeTrade = Integer.valueOf(iMoneyBeforeTrade);
        this.iMoneyAfterTrade = Integer.valueOf(iMoneyAfterTrade);
        this.iSellerType = Integer.valueOf(iSellerType);
        this.vSellerUin = String.valueOf(vSellerUin);
        this.iSellerRoleId = Integer.valueOf(iSellerRoleId);
        this.vSellerName = String.valueOf(vSellerName);
        this.iSellerJobId = Integer.valueOf(iSellerJobId);
        this.iSellerGender = Integer.valueOf(iSellerGender);
        this.iSellerLevel = Integer.valueOf(iSellerLevel);
        this.iSellerVipLevel = Integer.valueOf(iSellerVipLevel);
        this.iSellerReputationLevel = Integer.valueOf(iSellerReputationLevel);
        this.vBuyerClientIp = String.valueOf(vBuyerClientIp);
        this.vSellerClientIp = String.valueOf(vSellerClientIp);
        this.iPayment = Integer.valueOf(iPayment);
        this.iGoodsCount = Integer.valueOf(iGoodsCount);
        this.iItemId = Integer.valueOf(iItemId);
        this.iItemType = Integer.valueOf(iItemType);
        this.iItemNum = Integer.valueOf(iItemNum);
        this.iItemGuid = Integer.valueOf(iItemGuid);
    }
}
