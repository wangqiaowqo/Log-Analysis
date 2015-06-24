package com.shadowinlife.app.SQLModelFactory;

import java.io.*;
import java.sql.*;

public class ReceiveGift extends BaseBean implements Serializable {
    public ReceiveGift() {
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

    private String vClientIp;
    public void setvClientIp(String vClientIp){
        this.vClientIp=vClientIp;
    }
   public String getvClientIp() {
        return vClientIp;
    }

    private Integer iItemType;
    public void setiItemType(Integer iItemType){
        this.iItemType=iItemType;
    }
   public Integer getiItemType() {
        return iItemType;
    }

    private Integer iItemId;
    public void setiItemId(Integer iItemId){
        this.iItemId=iItemId;
    }
   public Integer getiItemId() {
        return iItemId;
    }

    private Integer iItemNum;
    public void setiItemNum(Integer iItemNum){
        this.iItemNum=iItemNum;
    }
   public Integer getiItemNum() {
        return iItemNum;
    }

    private Integer iCanReceiveTimes;
    public void setiCanReceiveTimes(Integer iCanReceiveTimes){
        this.iCanReceiveTimes=iCanReceiveTimes;
    }
   public Integer getiCanReceiveTimes() {
        return iCanReceiveTimes;
    }

    private Integer iItemRemain;
    public void setiItemRemain(Integer iItemRemain){
        this.iItemRemain=iItemRemain;
    }
   public Integer getiItemRemain() {
        return iItemRemain;
    }

    private Integer iItemStor;
    public void setiItemStor(Integer iItemStor){
        this.iItemStor=iItemStor;
    }
   public Integer getiItemStor() {
        return iItemStor;
    }

    private String vOtherUin;
    public void setvOtherUin(String vOtherUin){
        this.vOtherUin=vOtherUin;
    }
   public String getvOtherUin() {
        return vOtherUin;
    }

    private Integer iOtherRoleId;
    public void setiOtherRoleId(Integer iOtherRoleId){
        this.iOtherRoleId=iOtherRoleId;
    }
   public Integer getiOtherRoleId() {
        return iOtherRoleId;
    }

    private String vOtherRoleName;
    public void setvOtherRoleName(String vOtherRoleName){
        this.vOtherRoleName=vOtherRoleName;
    }
   public String getvOtherRoleName() {
        return vOtherRoleName;
    }

    private Integer iOtherRoleJob;
    public void setiOtherRoleJob(Integer iOtherRoleJob){
        this.iOtherRoleJob=iOtherRoleJob;
    }
   public Integer getiOtherRoleJob() {
        return iOtherRoleJob;
    }

    private Integer iOtherRoleGender;
    public void setiOtherRoleGender(Integer iOtherRoleGender){
        this.iOtherRoleGender=iOtherRoleGender;
    }
   public Integer getiOtherRoleGender() {
        return iOtherRoleGender;
    }

    private Integer iOtherRoleLevel;
    public void setiOtherRoleLevel(Integer iOtherRoleLevel){
        this.iOtherRoleLevel=iOtherRoleLevel;
    }
   public Integer getiOtherRoleLevel() {
        return iOtherRoleLevel;
    }

    private Integer iOtherRoleVipLevel;
    public void setiOtherRoleVipLevel(Integer iOtherRoleVipLevel){
        this.iOtherRoleVipLevel=iOtherRoleVipLevel;
    }
   public Integer getiOtherRoleVipLevel() {
        return iOtherRoleVipLevel;
    }

    private Integer iOtherRoleReputationLevel;
    public void setiOtherRoleReputationLevel(Integer iOtherRoleReputationLevel){
        this.iOtherRoleReputationLevel=iOtherRoleReputationLevel;
    }
   public Integer getiOtherRoleReputationLevel() {
        return iOtherRoleReputationLevel;
    }

    public ReceiveGift parseFromLogFile(String[] args, String GameId, String AccountType, String WorldId){
        return new ReceiveGift(GameId, AccountType, WorldId, args[1],args[2],args[3],args[4],args[5],args[6],args[7],args[8],args[9],args[10],args[11],args[12],args[13],args[14],args[15],args[16],args[17],args[18],args[19],args[20],args[21],args[22],args[23],args[24],args[25],args[26],args[27],args[28]);
    }

    public ReceiveGift(String GameId, String AccountType, String WorldId, String dtEventTime,String iEventId,String vVersionId,String vUin,String iRoleId,String vRoleName,String iRoleJob,String iRoleGender,String iRoleLevel,String iRoleVipLevel,String iRoleReputationLevel,String vRoleElse1,String vRoleElse2,String vClientIp,String iItemType,String iItemId,String iItemNum,String iCanReceiveTimes,String iItemRemain,String iItemStor,String vOtherUin,String iOtherRoleId,String vOtherRoleName,String iOtherRoleJob,String iOtherRoleGender,String iOtherRoleLevel,String iOtherRoleVipLevel,String iOtherRoleReputationLevel){
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
        this.vClientIp = String.valueOf(vClientIp);
        this.iItemType = Integer.valueOf(iItemType);
        this.iItemId = Integer.valueOf(iItemId);
        this.iItemNum = Integer.valueOf(iItemNum);
        this.iCanReceiveTimes = Integer.valueOf(iCanReceiveTimes);
        this.iItemRemain = Integer.valueOf(iItemRemain);
        this.iItemStor = Integer.valueOf(iItemStor);
        this.vOtherUin = String.valueOf(vOtherUin);
        this.iOtherRoleId = Integer.valueOf(iOtherRoleId);
        this.vOtherRoleName = String.valueOf(vOtherRoleName);
        this.iOtherRoleJob = Integer.valueOf(iOtherRoleJob);
        this.iOtherRoleGender = Integer.valueOf(iOtherRoleGender);
        this.iOtherRoleLevel = Integer.valueOf(iOtherRoleLevel);
        this.iOtherRoleVipLevel = Integer.valueOf(iOtherRoleVipLevel);
        this.iOtherRoleReputationLevel = Integer.valueOf(iOtherRoleReputationLevel);
    }
}
