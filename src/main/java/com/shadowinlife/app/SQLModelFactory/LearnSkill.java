package com.shadowinlife.app.SQLModelFactory;
import java.io.*;
import java.sql.*;

public class LearnSkill extends BaseBean implements Serializable {
    public LearnSkill() {
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

    private Long iMapId;
    public void setiMapId(Long iMapId){
        this.iMapId=iMapId;
    }
   public Long getiMapId() {
        return iMapId;
    }

    private Long iPosX;
    public void setiPosX(Long iPosX){
        this.iPosX=iPosX;
    }
   public Long getiPosX() {
        return iPosX;
    }

    private Long iPosY;
    public void setiPosY(Long iPosY){
        this.iPosY=iPosY;
    }
   public Long getiPosY() {
        return iPosY;
    }

    private Long iSkillTpye;
    public void setiSkillTpye(Long iSkillTpye){
        this.iSkillTpye=iSkillTpye;
    }
   public Long getiSkillTpye() {
        return iSkillTpye;
    }

    private Long iSkillId;
    public void setiSkillId(Long iSkillId){
        this.iSkillId=iSkillId;
    }
   public Long getiSkillId() {
        return iSkillId;
    }

    private Long iCurrSkillLv;
    public void setiCurrSkillLv(Long iCurrSkillLv){
        this.iCurrSkillLv=iCurrSkillLv;
    }
   public Long getiCurrSkillLv() {
        return iCurrSkillLv;
    }

    private Long iSkillLvAfterLearn;
    public void setiSkillLvAfterLearn(Long iSkillLvAfterLearn){
        this.iSkillLvAfterLearn=iSkillLvAfterLearn;
    }
   public Long getiSkillLvAfterLearn() {
        return iSkillLvAfterLearn;
    }

    private Long iNpcId;
    public void setiNpcId(Long iNpcId){
        this.iNpcId=iNpcId;
    }
   public Long getiNpcId() {
        return iNpcId;
    }

    private Long iNpcPosX;
    public void setiNpcPosX(Long iNpcPosX){
        this.iNpcPosX=iNpcPosX;
    }
   public Long getiNpcPosX() {
        return iNpcPosX;
    }

    private Long iNpcPosY;
    public void setiNpcPosY(Long iNpcPosY){
        this.iNpcPosY=iNpcPosY;
    }
   public Long getiNpcPosY() {
        return iNpcPosY;
    }

    public LearnSkill parseFromLogFile(String[] args, String GameId, String AccountType, String WorldId){
        return new LearnSkill(GameId, AccountType, WorldId, args[1],args[2],args[3],args[4],args[5],args[6],args[7],args[8],args[9],args[10],args[11],args[12],args[13],args[14],args[15],args[16],args[17],args[18],args[19],args[20],args[21],args[22],args[23]);
    }

    public LearnSkill(String GameId, String AccountType, String WorldId, String dtEventTime,String iEventId,String vVersionId,String vUin,String iRoleId,String vRoleName,String iRoleJob,String iRoleGender,String iRoleLevel,String iRoleVipLevel,String iRoleReputationLevel,String vRoleElse1,String vRoleElse2,String iMapId,String iPosX,String iPosY,String iSkillTpye,String iSkillId,String iCurrSkillLv,String iSkillLvAfterLearn,String iNpcId,String iNpcPosX,String iNpcPosY){
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
        this.iMapId = Long.valueOf(iMapId);
        this.iPosX = Long.valueOf(iPosX);
        this.iPosY = Long.valueOf(iPosY);
        this.iSkillTpye = Long.valueOf(iSkillTpye);
        this.iSkillId = Long.valueOf(iSkillId);
        this.iCurrSkillLv = Long.valueOf(iCurrSkillLv);
        this.iSkillLvAfterLearn = Long.valueOf(iSkillLvAfterLearn);
        this.iNpcId = Long.valueOf(iNpcId);
        this.iNpcPosX = Long.valueOf(iNpcPosX);
        this.iNpcPosY = Long.valueOf(iNpcPosY);
    }
}
