package com.shadowinlife.app.LogAnalyse.SQLModelFactory;

import java.sql.Date;

/**
 * 
 * @author shadowinlife
 
+--------------------+------------------+------+-----+---------+-------+
| Field              | Type             | Null | Key | Default | Extra |
+--------------------+------------------+------+-----+---------+-------+
| vTableFlage        | varchar(64)      | NO   |     |         |       |
| iEventId           | varchar(64)      | NO   |     |         |       |
| Uin                | varchar(64)      | NO   |     |         |       |
| dtEventTime        | datetime         | YES  |     | NULL    |       |
| iRoleId            | int(11) unsigned | NO   |     | 0       |       |
| vRoleName          | varchar(32)      | NO   |     |         |       |
| iJobId             | int(11) unsigned | NO   |     | 0       |       |
| iGender            | int(11) unsigned | NO   |     | 0       |       |
| iRoleLevel         | int(11) unsigned | NO   |     | 0       |       |
| iRoleExp           | int(11) unsigned | NO   |     | 0       |       |
| dtRoleCreateTime   | datetime         | YES  |     | NULL    |       |
| dtRoleLastSaveTime | datetime         | YES  |     | NULL    |       |
| iPoints            | int(11) unsigned | NO   |     | 0       |       |
| iDepositPoints     | int(11) unsigned | NO   |     | 0       |       |
| iMoney             | int(11) unsigned | NO   |     | 0       |       |
+--------------------+------------------+------+-----+---------+-------+
 */
public class RoleStatus {

    private String iEventId;
    private String Uin;
    private Date dtEventTime;
    private int iRoleId;
    private String vRoleName;
    private int iJobId;
    private int iGender;
    private int iRoleLevel;
    private int iRoleExp;
    private Date dtRoleCreateTime;
    private Date dtRoleLstSaveTime;
    private int iPoints;
    private int iDepositPoints;
    private int iMoney;

    private RoleStatus(String iEventId, String Uin, String dtEventTime, String iRoleId,
            String vRoleName, String iJobId, String iGender, String iRoleLevel, String iRoleExp,
            String dtRoleCreateTime, String dtRoleLstSaveTime, String iPoints,
            String iDepositPoints, String iMoney) {
        try {

            this.iEventId = iEventId;
            this.Uin = Uin;
            this.dtEventTime = Date.valueOf(dtEventTime);
            this.iRoleId = Integer.valueOf(iRoleId);
            this.vRoleName = vRoleName;
            this.iJobId = Integer.valueOf(iJobId);
            this.iGender = Integer.valueOf(iGender);
            this.iRoleLevel = Integer.valueOf(iRoleLevel);
            this.iRoleExp = Integer.valueOf(iRoleExp);
            this.dtRoleCreateTime = Date.valueOf(dtRoleCreateTime);
            this.dtRoleLstSaveTime = Date.valueOf(dtRoleLstSaveTime);
            this.iPoints = Integer.valueOf(iPoints);
            this.iDepositPoints = Integer.valueOf(iDepositPoints);
            this.iMoney = Integer.valueOf(iMoney);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public String getiEventId() {
        return iEventId;
    }

    public void setiEventId(String iEventId) {
        this.iEventId = iEventId;
    }

    public String getUin() {
        return Uin;
    }

    public void setUin(String uin) {
        Uin = uin;
    }

    public Date getDtEventTime() {
        return dtEventTime;
    }

    public void setDtEventTime(Date dtEventTime) {
        this.dtEventTime = dtEventTime;
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

    public int getiJobId() {
        return iJobId;
    }

    public void setiJobId(int iJobId) {
        this.iJobId = iJobId;
    }

    public int getiGender() {
        return iGender;
    }

    public void setiGender(int iGender) {
        this.iGender = iGender;
    }

    public int getiRoleLevel() {
        return iRoleLevel;
    }

    public void setiRoleLevel(int iRoleLevel) {
        this.iRoleLevel = iRoleLevel;
    }

    public int getiRoleExp() {
        return iRoleExp;
    }

    public void setiRoleExp(int iRoleExp) {
        this.iRoleExp = iRoleExp;
    }

    public Date getDtRoleCreateTime() {
        return dtRoleCreateTime;
    }

    public void setDtRoleCreateTime(Date dtRoleCreateTime) {
        this.dtRoleCreateTime = dtRoleCreateTime;
    }

    public Date getDtRoleLstSaveTime() {
        return dtRoleLstSaveTime;
    }

    public void setDtRoleLstSaveTime(Date dtRoleLstSaveTime) {
        this.dtRoleLstSaveTime = dtRoleLstSaveTime;
    }

    public int getiPoints() {
        return iPoints;
    }

    public void setiPoints(int iPoints) {
        this.iPoints = iPoints;
    }

    public int getiDepositPoints() {
        return iDepositPoints;
    }

    public void setiDepositPoints(int iDepositPoints) {
        this.iDepositPoints = iDepositPoints;
    }

    public int getiMoney() {
        return iMoney;
    }

    public void setiMoney(int iMoney) {
        this.iMoney = iMoney;
    }

    public static RoleStatus parseFromLogFile(String logline) {
        String[] columnList = logline.split("\\|", 15);
        if (columnList.length == 15) {
            for (int i = 0; i < 15; i++) {
                if (columnList[i] == null) {
                    columnList[i] = "-1";
                }
            }
            return new RoleStatus(columnList[1], columnList[2], columnList[3], columnList[4],
                    columnList[5], columnList[6], columnList[7], columnList[8], columnList[9],
                    columnList[10], columnList[11], columnList[12], columnList[13], columnList[14]);
        } else {
            return null;
        }
    }
}
