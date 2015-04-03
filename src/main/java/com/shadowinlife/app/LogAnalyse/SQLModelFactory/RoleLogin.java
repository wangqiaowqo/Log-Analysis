package com.shadowinlife.app.LogAnalyse.SQLModelFactory;

import java.sql.Date;

/**
 * 
 * @author shadowinlife
 *
+--------------+------------------+------+-----+---------+-------+
| Field        | Type             | Null | Key | Default | Extra |
+--------------+------------------+------+-----+---------+-------+
| vTableFlage  | varchar(64)      | NO   |     |         |       |
| iEventId     | varchar(64)      | NO   |     |         |       |
| iUin         | varchar(64)      | NO   |     |         |       |
| dtEventTime  | datetime         | YES  |     | NULL    |       |
| vClientIp    | varchar(32)      | NO   |     |         |       |
| vZoneId      | varchar(8)       | NO   |     |         |       |
| iRoleId      | int(11) unsigned | NO   |     | 0       |       |
| vRoleName    | varchar(32)      | NO   |     |         |       |
| iRoleLevel   | int(11) unsigned | NO   |     | 0       |       |
| iRoleExp     | int(11) unsigned | NO   |     | 0       |       |
| iRoleSpe     | int(11) unsigned | NO   |     | 0       |       |
| iEnergy      | int(11) unsigned | NO   |     | 0       |       |
| iMoney       | int(11) unsigned | NO   |     | 0       |       |
| iGamePoints  | int(11) unsigned | NO   |     | 0       |       |
| dtCreateTime | datetime         | YES  |     | NULL    |       |
| iOnlineTime  | int(11) unsigned | NO   |     | 0       |       |
| iLoginWay    | int(11) unsigned | NO   |     | 0       |       |
+--------------+------------------+------+-----+---------+-------+
 *
 */
public class RoleLogin {
    private String vTableFlage;
    private String iEventId;
    private String iUin;
    private Date dtEventTime;
    private String vClientIp;
    private String vZoneId;
    private int iRoleId;
    private String vRoleName;
    private int iRoleLevel;
    private int iRoleSpe;
    private int iEnergy;
    private int iMoney;
    private int iGamePoints;
    private Date dtCreateTime;
    private int iOnlineTime;
    private int iLoginWay;

    public String getvTableFlage() {
        return vTableFlage;
    }

    public void setvTableFlage(String vTableFlage) {
        this.vTableFlage = vTableFlage;
    }

    public String getiEventId() {
        return iEventId;
    }

    public void setiEventId(String iEventId) {
        this.iEventId = iEventId;
    }

    public String getiUin() {
        return iUin;
    }

    public void setiUin(String iUin) {
        this.iUin = iUin;
    }

    public Date getDtEventTime() {
        return dtEventTime;
    }

    public void setDtEventTime(Date dtEventTime) {
        this.dtEventTime = dtEventTime;
    }

    public String getvClientIp() {
        return vClientIp;
    }

    public void setvClientIp(String vClientIp) {
        this.vClientIp = vClientIp;
    }

    public String getvZoneId() {
        return vZoneId;
    }

    public void setvZoneId(String vZoneId) {
        this.vZoneId = vZoneId;
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

    public int getiRoleLevel() {
        return iRoleLevel;
    }

    public void setiRoleLevel(int iRoleLevel) {
        this.iRoleLevel = iRoleLevel;
    }

    public int getiRoleSpe() {
        return iRoleSpe;
    }

    public void setiRoleSpe(int iRoleSpe) {
        this.iRoleSpe = iRoleSpe;
    }

    public int getiEnergy() {
        return iEnergy;
    }

    public void setiEnergy(int iEnergy) {
        this.iEnergy = iEnergy;
    }

    public int getiMoney() {
        return iMoney;
    }

    public void setiMoney(int iMoney) {
        this.iMoney = iMoney;
    }

    public int getiGamePoints() {
        return iGamePoints;
    }

    public void setiGamePoints(int iGamePoints) {
        this.iGamePoints = iGamePoints;
    }

    public Date getDtCreateTime() {
        return dtCreateTime;
    }

    public void setDtCreateTime(Date dtCreateTime) {
        this.dtCreateTime = dtCreateTime;
    }

    public int getiOnlineTime() {
        return iOnlineTime;
    }

    public void setiOnlineTime(int iOnlineTime) {
        this.iOnlineTime = iOnlineTime;
    }

    public int getiLoginWay() {
        return iLoginWay;
    }

    public void setiLoginWay(int iLoginWay) {
        this.iLoginWay = iLoginWay;
    }
}
