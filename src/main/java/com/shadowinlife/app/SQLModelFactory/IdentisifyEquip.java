package com.shadowinlife.app.SQLModelFactory;

import java.io.*;
import java.sql.*;

public class IdentisifyEquip extends BaseBean implements Serializable {
    public IdentisifyEquip() {
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

    private Integer iEquipIdTpye;
    public void setiEquipIdTpye(Integer iEquipIdTpye){
        this.iEquipIdTpye=iEquipIdTpye;
    }
   public Integer getiEquipIdTpye() {
        return iEquipIdTpye;
    }

    private Integer iEquipId;
    public void setiEquipId(Integer iEquipId){
        this.iEquipId=iEquipId;
    }
   public Integer getiEquipId() {
        return iEquipId;
    }

    private Integer iEquipGuid;
    public void setiEquipGuid(Integer iEquipGuid){
        this.iEquipGuid=iEquipGuid;
    }
   public Integer getiEquipGuid() {
        return iEquipGuid;
    }

    private Integer iItemTpye;
    public void setiItemTpye(Integer iItemTpye){
        this.iItemTpye=iItemTpye;
    }
   public Integer getiItemTpye() {
        return iItemTpye;
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

    private Integer iItemRemain;
    public void setiItemRemain(Integer iItemRemain){
        this.iItemRemain=iItemRemain;
    }
   public Integer getiItemRemain() {
        return iItemRemain;
    }

    private Integer iItemLevel;
    public void setiItemLevel(Integer iItemLevel){
        this.iItemLevel=iItemLevel;
    }
   public Integer getiItemLevel() {
        return iItemLevel;
    }

    private Integer iItemExp;
    public void setiItemExp(Integer iItemExp){
        this.iItemExp=iItemExp;
    }
   public Integer getiItemExp() {
        return iItemExp;
    }

    private Integer iItemStar;
    public void setiItemStar(Integer iItemStar){
        this.iItemStar=iItemStar;
    }
   public Integer getiItemStar() {
        return iItemStar;
    }

    private Boolean bResult;
    public void setbResult(Boolean bResult){
        this.bResult=bResult;
    }
   public Boolean getbResult() {
        return bResult;
    }

    private String jItemAtrrbute;
    public void setjItemAtrrbute(String jItemAtrrbute){
        this.jItemAtrrbute=jItemAtrrbute;
    }
   public String getjItemAtrrbute() {
        return jItemAtrrbute;
    }

    public IdentisifyEquip parseFromLogFile(String[] args, String GameId, String AccountType, String WorldId){
        return new IdentisifyEquip(GameId, AccountType, WorldId, args[1],args[2],args[3],args[4],args[5],args[6],args[7],args[8],args[9],args[10],args[11],args[12],args[13],args[14],args[15],args[16],args[17],args[18],args[19],args[20],args[21],args[22],args[23],args[24],args[25]);
    }

    public IdentisifyEquip(String GameId, String AccountType, String WorldId, String dtEventTime,String iEventId,String vVersionId,String vUin,String iRoleId,String vRoleName,String iRoleJob,String iRoleGender,String iRoleLevel,String iRoleVipLevel,String iRoleReputationLevel,String vRoleElse1,String vRoleElse2,String iEquipIdTpye,String iEquipId,String iEquipGuid,String iItemTpye,String iItemId,String iItemNum,String iItemRemain,String iItemLevel,String iItemExp,String iItemStar,String bResult,String jItemAtrrbute){
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
        this.iEquipIdTpye = Integer.valueOf(iEquipIdTpye);
        this.iEquipId = Integer.valueOf(iEquipId);
        this.iEquipGuid = Integer.valueOf(iEquipGuid);
        this.iItemTpye = Integer.valueOf(iItemTpye);
        this.iItemId = Integer.valueOf(iItemId);
        this.iItemNum = Integer.valueOf(iItemNum);
        this.iItemRemain = Integer.valueOf(iItemRemain);
        this.iItemLevel = Integer.valueOf(iItemLevel);
        this.iItemExp = Integer.valueOf(iItemExp);
        this.iItemStar = Integer.valueOf(iItemStar);
        this.bResult = Boolean.valueOf(bResult);
        this.jItemAtrrbute = String.valueOf(jItemAtrrbute);
    }
}
