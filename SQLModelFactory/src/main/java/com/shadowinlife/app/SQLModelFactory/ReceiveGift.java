package com.shadowinlife.app.SQLModelFactory;
import java.io.*;
import java.sql.*;

public class ReceiveGift extends BaseBean implements Serializable {
    public ReceiveGift() {
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

    private String vClientIp;
    public void setvClientIp(String vClientIp){
        this.vClientIp=vClientIp;
    }
   public String getvClientIp() {
        return vClientIp;
    }

    private Long iItemType;
    public void setiItemType(Long iItemType){
        this.iItemType=iItemType;
    }
   public Long getiItemType() {
        return iItemType;
    }

    private Long iItemId;
    public void setiItemId(Long iItemId){
        this.iItemId=iItemId;
    }
   public Long getiItemId() {
        return iItemId;
    }

    private Long iItemNum;
    public void setiItemNum(Long iItemNum){
        this.iItemNum=iItemNum;
    }
   public Long getiItemNum() {
        return iItemNum;
    }

    private Long iCanReceiveTimes;
    public void setiCanReceiveTimes(Long iCanReceiveTimes){
        this.iCanReceiveTimes=iCanReceiveTimes;
    }
   public Long getiCanReceiveTimes() {
        return iCanReceiveTimes;
    }

    private Long iItemRemain;
    public void setiItemRemain(Long iItemRemain){
        this.iItemRemain=iItemRemain;
    }
   public Long getiItemRemain() {
        return iItemRemain;
    }

    private Long iItemStor;
    public void setiItemStor(Long iItemStor){
        this.iItemStor=iItemStor;
    }
   public Long getiItemStor() {
        return iItemStor;
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

    public ReceiveGift parseFromLogFile(String[] args, String GameId, String AccountType, String WorldId){
        return new ReceiveGift(GameId, AccountType, WorldId, args[1],args[2],args[3],args[4],args[5],args[6],args[7],args[8],args[9],args[10],args[11],args[12],args[13],args[14],args[15],args[16],args[17],args[18],args[19],args[20],args[21],args[22],args[23],args[24],args[25],args[26],args[27],args[28]);
    }

    public ReceiveGift(String GameId, String AccountType, String WorldId, String dtEventTime,String iEventId,String vVersionId,String vUin,String iRoleId,String vRoleName,String iRoleJob,String iRoleGender,String iRoleLevel,String iRoleVipLevel,String iRoleReputationLevel,String vRoleElse1,String vRoleElse2,String vClientIp,String iItemType,String iItemId,String iItemNum,String iCanReceiveTimes,String iItemRemain,String iItemStor,String vOtherUin,String iOtherRoleId,String vOtherRoleName,String iOtherRoleJob,String iOtherRoleGender,String iOtherRoleLevel,String iOtherRoleVipLevel,String iOtherRoleReputationLevel){
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
        this.vClientIp = String.valueOf(vClientIp);
        this.iItemType = Long.valueOf(iItemType);
        this.iItemId = Long.valueOf(iItemId);
        this.iItemNum = Long.valueOf(iItemNum);
        this.iCanReceiveTimes = Long.valueOf(iCanReceiveTimes);
        this.iItemRemain = Long.valueOf(iItemRemain);
        this.iItemStor = Long.valueOf(iItemStor);
        this.vOtherUin = String.valueOf(vOtherUin);
        this.iOtherRoleId = Long.valueOf(iOtherRoleId);
        this.vOtherRoleName = String.valueOf(vOtherRoleName);
        this.iOtherRoleJob = Long.valueOf(iOtherRoleJob);
        this.iOtherRoleGender = Long.valueOf(iOtherRoleGender);
        this.iOtherRoleLevel = Long.valueOf(iOtherRoleLevel);
        this.iOtherRoleVipLevel = Long.valueOf(iOtherRoleVipLevel);
        this.iOtherRoleReputationLevel = Long.valueOf(iOtherRoleReputationLevel);
    }
}
