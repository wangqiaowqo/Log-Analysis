package com.shadowinlife.app.SQLModelFactory;
import java.io.*;
import java.sql.*;

public class MarketTrade extends BaseBean implements Serializable {
    public MarketTrade() {
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

    private String vBuyerUin;
    public void setvBuyerUin(String vBuyerUin){
        this.vBuyerUin=vBuyerUin;
    }
   public String getvBuyerUin() {
        return vBuyerUin;
    }

    private Long iBuyerRoleId;
    public void setiBuyerRoleId(Long iBuyerRoleId){
        this.iBuyerRoleId=iBuyerRoleId;
    }
   public Long getiBuyerRoleId() {
        return iBuyerRoleId;
    }

    private String vBuyerName;
    public void setvBuyerName(String vBuyerName){
        this.vBuyerName=vBuyerName;
    }
   public String getvBuyerName() {
        return vBuyerName;
    }

    private Long iBuyerJobId;
    public void setiBuyerJobId(Long iBuyerJobId){
        this.iBuyerJobId=iBuyerJobId;
    }
   public Long getiBuyerJobId() {
        return iBuyerJobId;
    }

    private Long iBuyerGender;
    public void setiBuyerGender(Long iBuyerGender){
        this.iBuyerGender=iBuyerGender;
    }
   public Long getiBuyerGender() {
        return iBuyerGender;
    }

    private Long iBuyerLevel;
    public void setiBuyerLevel(Long iBuyerLevel){
        this.iBuyerLevel=iBuyerLevel;
    }
   public Long getiBuyerLevel() {
        return iBuyerLevel;
    }

    private Long iBuyerVipLevel;
    public void setiBuyerVipLevel(Long iBuyerVipLevel){
        this.iBuyerVipLevel=iBuyerVipLevel;
    }
   public Long getiBuyerVipLevel() {
        return iBuyerVipLevel;
    }

    private Long iBuyerReputationLevel;
    public void setiBuyerReputationLevel(Long iBuyerReputationLevel){
        this.iBuyerReputationLevel=iBuyerReputationLevel;
    }
   public Long getiBuyerReputationLevel() {
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

    private Long iMoneyBeforeTrade;
    public void setiMoneyBeforeTrade(Long iMoneyBeforeTrade){
        this.iMoneyBeforeTrade=iMoneyBeforeTrade;
    }
   public Long getiMoneyBeforeTrade() {
        return iMoneyBeforeTrade;
    }

    private Long iMoneyAfterTrade;
    public void setiMoneyAfterTrade(Long iMoneyAfterTrade){
        this.iMoneyAfterTrade=iMoneyAfterTrade;
    }
   public Long getiMoneyAfterTrade() {
        return iMoneyAfterTrade;
    }

    private Long iSellerType;
    public void setiSellerType(Long iSellerType){
        this.iSellerType=iSellerType;
    }
   public Long getiSellerType() {
        return iSellerType;
    }

    private String vSellerUin;
    public void setvSellerUin(String vSellerUin){
        this.vSellerUin=vSellerUin;
    }
   public String getvSellerUin() {
        return vSellerUin;
    }

    private Long iSellerRoleId;
    public void setiSellerRoleId(Long iSellerRoleId){
        this.iSellerRoleId=iSellerRoleId;
    }
   public Long getiSellerRoleId() {
        return iSellerRoleId;
    }

    private String vSellerName;
    public void setvSellerName(String vSellerName){
        this.vSellerName=vSellerName;
    }
   public String getvSellerName() {
        return vSellerName;
    }

    private Long iSellerJobId;
    public void setiSellerJobId(Long iSellerJobId){
        this.iSellerJobId=iSellerJobId;
    }
   public Long getiSellerJobId() {
        return iSellerJobId;
    }

    private Long iSellerGender;
    public void setiSellerGender(Long iSellerGender){
        this.iSellerGender=iSellerGender;
    }
   public Long getiSellerGender() {
        return iSellerGender;
    }

    private Long iSellerLevel;
    public void setiSellerLevel(Long iSellerLevel){
        this.iSellerLevel=iSellerLevel;
    }
   public Long getiSellerLevel() {
        return iSellerLevel;
    }

    private Long iSellerVipLevel;
    public void setiSellerVipLevel(Long iSellerVipLevel){
        this.iSellerVipLevel=iSellerVipLevel;
    }
   public Long getiSellerVipLevel() {
        return iSellerVipLevel;
    }

    private Long iSellerReputationLevel;
    public void setiSellerReputationLevel(Long iSellerReputationLevel){
        this.iSellerReputationLevel=iSellerReputationLevel;
    }
   public Long getiSellerReputationLevel() {
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

    private Long iPayment;
    public void setiPayment(Long iPayment){
        this.iPayment=iPayment;
    }
   public Long getiPayment() {
        return iPayment;
    }

    private Long iGoodsCount;
    public void setiGoodsCount(Long iGoodsCount){
        this.iGoodsCount=iGoodsCount;
    }
   public Long getiGoodsCount() {
        return iGoodsCount;
    }

    private Long iItemId;
    public void setiItemId(Long iItemId){
        this.iItemId=iItemId;
    }
   public Long getiItemId() {
        return iItemId;
    }

    private Long iItemType;
    public void setiItemType(Long iItemType){
        this.iItemType=iItemType;
    }
   public Long getiItemType() {
        return iItemType;
    }

    private Long iItemNum;
    public void setiItemNum(Long iItemNum){
        this.iItemNum=iItemNum;
    }
   public Long getiItemNum() {
        return iItemNum;
    }

    private Long iItemGuid;
    public void setiItemGuid(Long iItemGuid){
        this.iItemGuid=iItemGuid;
    }
   public Long getiItemGuid() {
        return iItemGuid;
    }

    public MarketTrade parseFromLogFile(String[] args, String GameId, String AccountType, String WorldId){
        return new MarketTrade(GameId, AccountType, WorldId, args[1],args[2],args[3],args[4],args[5],args[6],args[7],args[8],args[9],args[10],args[11],args[12],args[13],args[14],args[15],args[16],args[17],args[18],args[19],args[20],args[21],args[22],args[23],args[24],args[25],args[26],args[27],args[28],args[29],args[30],args[31],args[32]);
    }

    public MarketTrade(String GameId, String AccountType, String WorldId, String dtEventTime,String iEventId,String vVersionId,String vBuyerUin,String iBuyerRoleId,String vBuyerName,String iBuyerJobId,String iBuyerGender,String iBuyerLevel,String iBuyerVipLevel,String iBuyerReputationLevel,String vBuyerRoleElse1,String vBuyerRoleElse2,String iMoneyBeforeTrade,String iMoneyAfterTrade,String iSellerType,String vSellerUin,String iSellerRoleId,String vSellerName,String iSellerJobId,String iSellerGender,String iSellerLevel,String iSellerVipLevel,String iSellerReputationLevel,String vBuyerClientIp,String vSellerClientIp,String iPayment,String iGoodsCount,String iItemId,String iItemType,String iItemNum,String iItemGuid){
        this.iGameId = Long.valueOf(GameId);
        this.iAccountType = Long.valueOf(AccountType);
        this.iWorldId = Long.valueOf(WorldId);
        this.dtEventTime = Timestamp.valueOf(dtEventTime);
        this.iEventId = Long.valueOf(iEventId);
        this.vVersionId = String.valueOf(vVersionId);
        this.vBuyerUin = String.valueOf(vBuyerUin);
        this.iBuyerRoleId = Long.valueOf(iBuyerRoleId);
        this.vBuyerName = String.valueOf(vBuyerName);
        this.iBuyerJobId = Long.valueOf(iBuyerJobId);
        this.iBuyerGender = Long.valueOf(iBuyerGender);
        this.iBuyerLevel = Long.valueOf(iBuyerLevel);
        this.iBuyerVipLevel = Long.valueOf(iBuyerVipLevel);
        this.iBuyerReputationLevel = Long.valueOf(iBuyerReputationLevel);
        this.vBuyerRoleElse1 = String.valueOf(vBuyerRoleElse1);
        this.vBuyerRoleElse2 = String.valueOf(vBuyerRoleElse2);
        this.iMoneyBeforeTrade = Long.valueOf(iMoneyBeforeTrade);
        this.iMoneyAfterTrade = Long.valueOf(iMoneyAfterTrade);
        this.iSellerType = Long.valueOf(iSellerType);
        this.vSellerUin = String.valueOf(vSellerUin);
        this.iSellerRoleId = Long.valueOf(iSellerRoleId);
        this.vSellerName = String.valueOf(vSellerName);
        this.iSellerJobId = Long.valueOf(iSellerJobId);
        this.iSellerGender = Long.valueOf(iSellerGender);
        this.iSellerLevel = Long.valueOf(iSellerLevel);
        this.iSellerVipLevel = Long.valueOf(iSellerVipLevel);
        this.iSellerReputationLevel = Long.valueOf(iSellerReputationLevel);
        this.vBuyerClientIp = String.valueOf(vBuyerClientIp);
        this.vSellerClientIp = String.valueOf(vSellerClientIp);
        this.iPayment = Long.valueOf(iPayment);
        this.iGoodsCount = Long.valueOf(iGoodsCount);
        this.iItemId = Long.valueOf(iItemId);
        this.iItemType = Long.valueOf(iItemType);
        this.iItemNum = Long.valueOf(iItemNum);
        this.iItemGuid = Long.valueOf(iItemGuid);
    }
}
