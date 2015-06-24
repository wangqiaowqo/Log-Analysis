package com.shadowinlife.app.SQLModelFactory;

import java.io.*;
import java.sql.*;

public class MarketTakeOn extends BaseBean implements Serializable {
    public MarketTakeOn() {
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

    private Integer iMoneyBeforeStall;
    public void setiMoneyBeforeStall(Integer iMoneyBeforeStall){
        this.iMoneyBeforeStall=iMoneyBeforeStall;
    }
   public Integer getiMoneyBeforeStall() {
        return iMoneyBeforeStall;
    }

    private Integer iMoneyAfterStall;
    public void setiMoneyAfterStall(Integer iMoneyAfterStall){
        this.iMoneyAfterStall=iMoneyAfterStall;
    }
   public Integer getiMoneyAfterStall() {
        return iMoneyAfterStall;
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

    private Integer iPrice;
    public void setiPrice(Integer iPrice){
        this.iPrice=iPrice;
    }
   public Integer getiPrice() {
        return iPrice;
    }

    private Integer iTransactionCosts;
    public void setiTransactionCosts(Integer iTransactionCosts){
        this.iTransactionCosts=iTransactionCosts;
    }
   public Integer getiTransactionCosts() {
        return iTransactionCosts;
    }

    public MarketTakeOn parseFromLogFile(String[] args, String GameId, String AccountType, String WorldId){
        return new MarketTakeOn(GameId, AccountType, WorldId, args[1],args[2],args[3],args[4],args[5],args[6],args[7],args[8],args[9],args[10],args[11],args[12],args[13],args[14],args[15],args[16],args[17],args[18],args[19],args[20],args[21]);
    }

    public MarketTakeOn(String GameId, String AccountType, String WorldId, String dtEventTime,String iEventId,String vVersionId,String vUin,String iRoleId,String vRoleName,String iRoleJob,String iRoleGender,String iRoleLevel,String iRoleVipLevel,String iRoleReputationLevel,String vRoleElse1,String vRoleElse2,String iMoneyBeforeStall,String iMoneyAfterStall,String iItemId,String iItemType,String iItemNum,String iItemGuid,String iPrice,String iTransactionCosts){
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
        this.iMoneyBeforeStall = Integer.valueOf(iMoneyBeforeStall);
        this.iMoneyAfterStall = Integer.valueOf(iMoneyAfterStall);
        this.iItemId = Integer.valueOf(iItemId);
        this.iItemType = Integer.valueOf(iItemType);
        this.iItemNum = Integer.valueOf(iItemNum);
        this.iItemGuid = Integer.valueOf(iItemGuid);
        this.iPrice = Integer.valueOf(iPrice);
        this.iTransactionCosts = Integer.valueOf(iTransactionCosts);
    }
}
