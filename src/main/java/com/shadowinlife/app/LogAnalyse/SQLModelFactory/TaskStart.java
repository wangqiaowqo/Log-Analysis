package com.shadowinlife.app.LogAnalyse.SQLModelFactory;

import java.io.Serializable;
import java.sql.Timestamp;

public class TaskStart extends BaseBean implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private Timestamp dtEventTime;
    private String iEventId;
    private String version;
    private String iUin;
    private int iRoleId;
    private String vRoleName;
    private int iRoleJob;
    private int iRoleSex;
    private int iRoleLevel;
    private int iRoleVipLevel;
    private int iRepuLevel;
    private String v1;
    private String v2;

    private long iRoleExperience;
    private int iRoleSword;
    private int iTaskType;
    private int iTaskWay;
    private int iTaskCircle;
    private int iTaskId;
    private String vTaskName;
    private int iTaskStar;
    private int iNpcId;
    private String vNpcName;
    private int iFlag;
    private int iItemType;
    private int iItemId;
    private int iItemNum;
    private int iItemRemain;
    private int iItemGuide;

    private TaskStart(String dtEventTime, String iEventId, String version, String iUin,
            String iRoleId, String vRoleName, String iRoleJob, String iRoleSex, String iRoleLevel,
            String iRoleVipLevel, String iRepuLevel, String v1, String v2, String iRoleExperience,
            String iRoleSword, String iTaskType, String iTaskWay, String iTaskCircle,
            String iTaskId, String vTaskName, String iTaskStar, String iNpcId, String vNpcName,
            String iFlag, String iItemType, String iItemId, String iItemNum, String iItemRemain,
            String iItemGuide) {
        this.dtEventTime = Timestamp.valueOf(dtEventTime);
        this.iEventId = iEventId;
        this.version = version;
        this.iUin = iUin;
        this.iRoleId = Integer.valueOf(iRoleId);
        this.vRoleName = vRoleName;
        this.iRoleJob = Integer.valueOf(iRoleJob);
        this.iRoleSex = Integer.valueOf(iRoleSex);
        this.iRoleLevel = Integer.valueOf(iRoleLevel);
        this.iRoleVipLevel = Integer.valueOf(iRoleVipLevel);
        this.iRepuLevel = Integer.valueOf(iRepuLevel);
        this.v1 = v1;
        this.v2 = v2;

        this.iRoleExperience = Integer.valueOf(iRoleExperience);
        this.iRoleSword = Integer.valueOf(iRoleSword);
        this.iTaskType = Integer.valueOf(iTaskType);
        this.iTaskWay = Integer.valueOf(iTaskWay);
        this.iTaskCircle = Integer.valueOf(iTaskCircle);
        this.iTaskId = Integer.valueOf(iTaskId);
        this.vTaskName = vTaskName;
        this.iTaskStar = Integer.valueOf(iTaskStar);
        this.iNpcId = Integer.valueOf(iNpcId);
        this.vNpcName = vNpcName;
        this.iFlag = Integer.valueOf(iFlag);
        this.iItemType = Integer.valueOf(iItemType);
        this.iItemId = Integer.valueOf(iItemId);
        this.iItemNum = Integer.valueOf(iItemNum);
        this.iItemRemain = Integer.valueOf(iItemRemain);
        this.iItemGuide = Integer.valueOf(iItemGuide);
    }

    public TaskStart() {
        super();
    }
    
    public TaskStart parseFromLogFile(String[] array) {
        String[] columnList = array;
        for (int i = 0; i < columnList.length; i++){
            if (columnList[i].equalsIgnoreCase(" ")){
                columnList[i] = "0";}
        }
        return new TaskStart(columnList[1], columnList[2], columnList[3], columnList[4],
                columnList[5], columnList[6], columnList[7], columnList[8], columnList[9],
                columnList[10], columnList[11], columnList[12], columnList[13], columnList[14],
                columnList[15], columnList[16], columnList[17], columnList[18], columnList[19],
                columnList[20], columnList[21], columnList[22], columnList[23], columnList[24],
                columnList[25], columnList[26], columnList[27], columnList[28], columnList[29]);
    }

    public Timestamp getDtEventTime() {
        return dtEventTime;
    }

    public void setDtEventTime(Timestamp dtEventTime) {
        this.dtEventTime = dtEventTime;
    }

    public String getiEventId() {
        return iEventId;
    }

    public void setiEventId(String iEventId) {
        this.iEventId = iEventId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getiUin() {
        return iUin;
    }

    public void setiUin(String iUin) {
        this.iUin = iUin;
    }

    public int getiRoleId() {
        return iRoleId;
    }

    public void setiRoleId(int iRoleId) {
        this.iRoleId = iRoleId;
    }

    public String getvRoleName() {
        return vRoleName;
    }

    public void setvRoleName(String vRoleName) {
        this.vRoleName = vRoleName;
    }

    public int getiRoleJob() {
        return iRoleJob;
    }

    public void setiRoleJob(int iRoleJob) {
        this.iRoleJob = iRoleJob;
    }

    public int getiRoleSex() {
        return iRoleSex;
    }

    public void setiRoleSex(int iRoleSex) {
        this.iRoleSex = iRoleSex;
    }

    public int getiRoleLevel() {
        return iRoleLevel;
    }

    public void setiRoleLevel(int iRoleLevel) {
        this.iRoleLevel = iRoleLevel;
    }

    public int getiRoleVipLevel() {
        return iRoleVipLevel;
    }

    public void setiRoleVipLevel(int iRoleVipLevel) {
        this.iRoleVipLevel = iRoleVipLevel;
    }

    public int getiRepuLevel() {
        return iRepuLevel;
    }

    public void setiRepuLevel(int iRepuLevel) {
        this.iRepuLevel = iRepuLevel;
    }

    public String getV1() {
        return v1;
    }

    public void setV1(String v1) {
        this.v1 = v1;
    }

    public String getV2() {
        return v2;
    }

    public void setV2(String v2) {
        this.v2 = v2;
    }

    public long getiRoleExperience() {
        return iRoleExperience;
    }

    public void setiRoleExperience(long iRoleExperience) {
        this.iRoleExperience = iRoleExperience;
    }

    public int getiRoleSword() {
        return iRoleSword;
    }

    public void setiRoleSword(int iRoleSword) {
        this.iRoleSword = iRoleSword;
    }

    public int getiTaskType() {
        return iTaskType;
    }

    public void setiTaskType(int iTaskType) {
        this.iTaskType = iTaskType;
    }

    public int getiTaskWay() {
        return iTaskWay;
    }

    public void setiTaskWay(int iTaskWay) {
        this.iTaskWay = iTaskWay;
    }

    public int getiTaskCircle() {
        return iTaskCircle;
    }

    public void setiTaskCircle(int iTaskCircle) {
        this.iTaskCircle = iTaskCircle;
    }

    public int getiTaskId() {
        return iTaskId;
    }

    public void setiTaskId(int iTaskId) {
        this.iTaskId = iTaskId;
    }

    public String getvTaskName() {
        return vTaskName;
    }

    public void setvTaskName(String vTaskName) {
        this.vTaskName = vTaskName;
    }

    public int getiTaskStar() {
        return iTaskStar;
    }

    public void setiTaskStar(int iTaskStar) {
        this.iTaskStar = iTaskStar;
    }

    public int getiNpcId() {
        return iNpcId;
    }

    public void setiNpcId(int iNpcId) {
        this.iNpcId = iNpcId;
    }

    public String getvNpcName() {
        return vNpcName;
    }

    public void setvNpcName(String vNpcName) {
        this.vNpcName = vNpcName;
    }

    public int getiFlag() {
        return iFlag;
    }

    public void setiFlag(int iFlag) {
        this.iFlag = iFlag;
    }

    public int getiItemType() {
        return iItemType;
    }

    public void setiItemType(int iItemType) {
        this.iItemType = iItemType;
    }

    public int getiItemId() {
        return iItemId;
    }

    public void setiItemId(int iItemId) {
        this.iItemId = iItemId;
    }

    public int getiItemRemain() {
        return iItemRemain;
    }

    public void setiItemRemain(int iItemRemain) {
        this.iItemRemain = iItemRemain;
    }

    public int getiItemGuide() {
        return iItemGuide;
    }

    public void setiItemGuide(int iItemGuide) {
        this.iItemGuide = iItemGuide;
    }

    public int getiItemNum() {
        return iItemNum;
    }

    public void setiItemNum(int iItemNum) {
        this.iItemNum = iItemNum;
    }

}
