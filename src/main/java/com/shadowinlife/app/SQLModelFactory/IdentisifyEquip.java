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

    private Long iEquipIdTpye;
    public void setiEquipIdTpye(Long iEquipIdTpye){
        this.iEquipIdTpye=iEquipIdTpye;
    }
   public Long getiEquipIdTpye() {
        return iEquipIdTpye;
    }

    private Long iEquipId;
    public void setiEquipId(Long iEquipId){
        this.iEquipId=iEquipId;
    }
   public Long getiEquipId() {
        return iEquipId;
    }

    private Long iEquipGuid;
    public void setiEquipGuid(Long iEquipGuid){
        this.iEquipGuid=iEquipGuid;
    }
   public Long getiEquipGuid() {
        return iEquipGuid;
    }

    private Long iItemTpye;
    public void setiItemTpye(Long iItemTpye){
        this.iItemTpye=iItemTpye;
    }
   public Long getiItemTpye() {
        return iItemTpye;
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

    private Long iItemRemain;
    public void setiItemRemain(Long iItemRemain){
        this.iItemRemain=iItemRemain;
    }
   public Long getiItemRemain() {
        return iItemRemain;
    }

    private Long iItemLevel;
    public void setiItemLevel(Long iItemLevel){
        this.iItemLevel=iItemLevel;
    }
   public Long getiItemLevel() {
        return iItemLevel;
    }

    private Long iItemExp;
    public void setiItemExp(Long iItemExp){
        this.iItemExp=iItemExp;
    }
   public Long getiItemExp() {
        return iItemExp;
    }

    private Long iItemStar;
    public void setiItemStar(Long iItemStar){
        this.iItemStar=iItemStar;
    }
   public Long getiItemStar() {
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
        this.iRoleId = Long.valueOf(iRoleId);
        this.vRoleName = String.valueOf(vRoleName);
        this.iRoleJob = Long.valueOf(iRoleJob);
        this.iRoleGender = Long.valueOf(iRoleGender);
        this.iRoleLevel = Long.valueOf(iRoleLevel);
        this.iRoleVipLevel = Long.valueOf(iRoleVipLevel);
        this.iRoleReputationLevel = Long.valueOf(iRoleReputationLevel);
        this.vRoleElse1 = String.valueOf(vRoleElse1);
        this.vRoleElse2 = String.valueOf(vRoleElse2);
        this.iEquipIdTpye = Long.valueOf(iEquipIdTpye);
        this.iEquipId = Long.valueOf(iEquipId);
        this.iEquipGuid = Long.valueOf(iEquipGuid);
        this.iItemTpye = Long.valueOf(iItemTpye);
        this.iItemId = Long.valueOf(iItemId);
        this.iItemNum = Long.valueOf(iItemNum);
        this.iItemRemain = Long.valueOf(iItemRemain);
        this.iItemLevel = Long.valueOf(iItemLevel);
        this.iItemExp = Long.valueOf(iItemExp);
        this.iItemStar = Long.valueOf(iItemStar);
        this.bResult = Boolean.valueOf(bResult);
        this.jItemAtrrbute = String.valueOf(jItemAtrrbute);
    }
}
