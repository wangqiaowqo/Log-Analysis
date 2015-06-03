package com.shadowinlife.app.LogAnalyse.SQLModelFactory;

import java.io.*;
import java.sql.*;

public class RoleStatus implements Serializable {
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

    private Integer iJobId;
    public void setiJobId(Integer iJobId){
        this.iJobId=iJobId;
    }
   public Integer getiJobId() {
        return iJobId;
    }

    private Integer iGender;
    public void setiGender(Integer iGender){
        this.iGender=iGender;
    }
   public Integer getiGender() {
        return iGender;
    }

    private Integer iRoleLevel;
    public void setiRoleLevel(Integer iRoleLevel){
        this.iRoleLevel=iRoleLevel;
    }
   public Integer getiRoleLevel() {
        return iRoleLevel;
    }

    private Integer iRoleExp;
    public void setiRoleExp(Integer iRoleExp){
        this.iRoleExp=iRoleExp;
    }
   public Integer getiRoleExp() {
        return iRoleExp;
    }

    private Timestamp dtCreateTime;
    public void setdtCreateTime(Timestamp dtCreateTime){
        this.dtCreateTime=dtCreateTime;
    }
   public Timestamp getdtCreateTime() {
        return dtCreateTime;
    }

    private Timestamp dtRoleLastSaveTime;
    public void setdtRoleLastSaveTime(Timestamp dtRoleLastSaveTime){
        this.dtRoleLastSaveTime=dtRoleLastSaveTime;
    }
   public Timestamp getdtRoleLastSaveTime() {
        return dtRoleLastSaveTime;
    }

    private Integer iPoints;
    public void setiPoints(Integer iPoints){
        this.iPoints=iPoints;
    }
   public Integer getiPoints() {
        return iPoints;
    }

    private Integer iDepositPoints;
    public void setiDepositPoints(Integer iDepositPoints){
        this.iDepositPoints=iDepositPoints;
    }
   public Integer getiDepositPoints() {
        return iDepositPoints;
    }

    private Integer iMoney;
    public void setiMoney(Integer iMoney){
        this.iMoney=iMoney;
    }
   public Integer getiMoney() {
        return iMoney;
    }

    public static RoleStatus parseFromLogFile(String[] args){
        return new RoleStatus(args[1],args[2],args[3],args[4],args[5],args[6],args[7],args[8],args[9],args[10],args[11],args[12],args[13],args[14],args[15]);
    }

    public RoleStatus(String dtEventTime,String iEventId,String vVersionId,String vUin,String iRoleId,String vRoleName,String iJobId,String iGender,String iRoleLevel,String iRoleExp,String dtCreateTime,String dtRoleLastSaveTime,String iPoints,String iDepositPoints,String iMoney){
        this.dtEventTime = Timestamp.valueOf(dtEventTime);
        this.iEventId = Long.valueOf(iEventId);
        this.vVersionId = String.valueOf(vVersionId);
        this.vUin = String.valueOf(vUin);
        this.iRoleId = Integer.valueOf(iRoleId);
        this.vRoleName = String.valueOf(vRoleName);
        this.iJobId = Integer.valueOf(iJobId);
        this.iGender = Integer.valueOf(iGender);
        this.iRoleLevel = Integer.valueOf(iRoleLevel);
        this.iRoleExp = Integer.valueOf(iRoleExp);
        this.dtCreateTime = Timestamp.valueOf(dtCreateTime);
        this.dtRoleLastSaveTime = Timestamp.valueOf(dtRoleLastSaveTime);
        this.iPoints = Integer.valueOf(iPoints);
        this.iDepositPoints = Integer.valueOf(iDepositPoints);
        this.iMoney = Integer.valueOf(iMoney);
    }
}
