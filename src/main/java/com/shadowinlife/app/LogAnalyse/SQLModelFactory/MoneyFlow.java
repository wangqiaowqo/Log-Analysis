package com.shadowinlife.app.LogAnalyse.SQLModelFactory;

import java.io.Serializable;
import java.sql.Timestamp;

import jodd.util.StringUtil;
/**
+--------------+------------------+------+-----+---------+-------+
| Field        | Type             | Null | Key | Default | Extra |
+--------------+------------------+------+-----+---------+-------+
| vTableFlage  | varchar(64)      | NO   |     |Chongzhi |       |
| dtEventTime  | datetime         | YES  |     | NULL    |       |
| iEventId     | varchar(64)      | NO   |     |         |       |
| version      | varchar(64)      | NO   |     |         |       |
| iUin         | varchar(64)      | NO   |     |         |       |
| iRoleId      | int(11) unsigned | NO   |     | 0       |       |
| vRoleName    | varchar(32)      | NO   |     |         |       |
| iRoleJob     | int(11) unsigned | NO   |     | 0       |       |
| iRoleSex     | int(11) unsigned | NO   |     | 0       |       |
| iRoleLevel   | int(11) unsigned | NO   |     | 0       |       |
| iRoleVipLevel| int(11) unsigned | NO   |     | 0       |       |
| iRepuLevel   | int(11) unsigned | NO   |     | 0       |       |
| v1           | varchar(32)      | NO   |     |         |       |
| v2           | varchar(32)      | NO   |     |         |       |
| iBeforeMoney | long             | NO   |     |         |       |
| iAfterMoney  | long             | NO   |     |         |       |
| iMoneyType   | int(11) unsigned | NO   |     | 0       |       |
| iMoney       | long             | NO   |     | 0       |       |
| iFlowType    | int              | NO   |     | 0       |       |
| iAction      | int              | NO   |     | 0       |       |
+--------------+------------------+------+-----+---------+-------+
*/
public class MoneyFlow extends BaseBean implements Serializable {
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
    
    private long iBeforeMoney;
    private long iAfterMoney;
    private int iMoneyType;
    private Long iMoney;
    private int iFlowType;
    private int iAction;
    
    public MoneyFlow(String dtEventTime, String iEventId, String version, String iUin,
            String iRoleId, String vRoleName, String iRoleJob, String iRoleSex, String iRoleLevel,
            String iRoleVipLevel, String iRepuLevel, String v1, String v2, String iBeforeMoney,
            String iAfterMoney, String iMoneyType, String iMoney, String iFlowType, String iAction) {

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
        this.iBeforeMoney = Long.valueOf(iBeforeMoney);
        this.iAfterMoney = Long.valueOf(iAfterMoney);
        this.iMoneyType = Integer.valueOf(iMoneyType);
        this.iMoney = Math.abs(Long.valueOf(iMoney));
        this.iFlowType = Integer.valueOf(iFlowType);
        this.iAction = Integer.valueOf(iAction);
    }

    public MoneyFlow parseFromLogFile(String[] array) {
        String[] columnList = array;

        if (columnList.length == 20) {
            for (int i = 0; i < 19; i++) {
                if (StringUtil.isEmpty(columnList[i])) {
                    columnList[i] = "0";
                }
            }
            return new MoneyFlow(columnList[1], columnList[2], columnList[3], columnList[4],
                    columnList[5], columnList[6], columnList[7], columnList[8], columnList[9],
                    columnList[10], columnList[11], columnList[12], columnList[13], columnList[14],
                    columnList[15], columnList[16], columnList[17], columnList[18], columnList[19]);
        } else {
            return null;
        }
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

    public long getiBeforeMoney() {
        return iBeforeMoney;
    }

    public void setiBeforeMoney(long iBeforeMoney) {
        this.iBeforeMoney = iBeforeMoney;
    }

    public long getiAfterMoney() {
        return iAfterMoney;
    }

    public void setiAfterMoney(long iAfterMoney) {
        this.iAfterMoney = iAfterMoney;
    }

    public int getiMoneyType() {
        return iMoneyType;
    }

    public void setiMoneyType(int iMoneyType) {
        this.iMoneyType = iMoneyType;
    }

    public long getiMoney() {
        return iMoney;
    }

    public void setiMoney(long iMoney) {
        this.iMoney = iMoney;
    }

    public int getiFlowType() {
        return iFlowType;
    }

    public void setiFlowType(int iFlowType) {
        this.iFlowType = iFlowType;
    }

    public int getiAction() {
        return iAction;
    }

    public void setiAction(int iAction) {
        this.iAction = iAction;
    }
}
