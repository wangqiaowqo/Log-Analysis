package com.shadowinlife.app.LogAnalyse.SQLModelFactory;

import java.sql.Date;

/**
 * 
 * @author shadowinlife
+-------------+------------------+------+-----+---------+-------+
| Field       | Type             | Null | Key | Default | Extra |
+-------------+------------------+------+-----+---------+-------+
| vTableFlage | varchar(64)      | NO   |     |         |       |
| iEventId    | varchar(64)      | NO   |     |         |       |
| iUin        | varchar(64)      | NO   |     |         |       |
| dtEventTime | datetime         | YES  |     | NULL    |       |
| dtLoginTime | datetime         | YES  |     | NULL    |       |
| vClientIp   | varchar(32)      | NO   |     |         |       |
| vZoneId     | varchar(8)       | NO   |     |         |       |
| iRoleId     | int(11) unsigned | NO   |     | 0       |       |
| vRoleName   | varchar(32)      | NO   |     |         |       |
| iRoleLevel  | int(11) unsigned | NO   |     | 0       |       |
| iRoleExp    | int(11) unsigned | NO   |     | 0       |       |
| iRepute     | int(11) unsigned | NO   |     | 0       |       |
| iMainSpeExp | int(11) unsigned | NO   |     | 0       |       |
| iMoney      | int(11) unsigned | NO   |     | 0       |       |
| iGamePoints | int(11) unsigned | NO   |     | 0       |       |
| iOnlineTime | int(11) unsigned | NO   |     | 0       |       |
| iGameTime   | int(11) unsigned | NO   |     | 0       |       |
| iLoginWay   | int(11) unsigned | NO   |     | 0       |       |
+-------------+------------------+------+-----+---------+-------+
 */
public class RoleLogout {
    private String vTalbeFlage;
    private String iEventId;
    private String iUin;
    private Date dtEventTime;
    private Date dtLoginTime;
    private String vClientIp;
    private String vZoneId;
    private int iRoleId;
    private String vRoleName;
    private int iRoleLevel;
    private int iRoleExp;
    private int iRepute;
    private int iMainSpeExp;
    private int iMoney;
    private int iGamePoints;
    private int iOnlineTime;
    private int iGameTime;
    private int iLoginWay;

    public String getvTalbeFlage() {
        return vTalbeFlage;
    }

    public void setvTalbeFlage(String vTalbeFlage) {
        this.vTalbeFlage = vTalbeFlage;
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

    public Date getDtLoginTime() {
        return dtLoginTime;
    }

    public void setDtLoginTime(Date dtLoginTime) {
        this.dtLoginTime = dtLoginTime;
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

    public int getiRoleExp() {
        return iRoleExp;
    }

    public void setiRoleExp(int iRoleExp) {
        this.iRoleExp = iRoleExp;
    }

    public int getiRepute() {
        return iRepute;
    }

    public void setiRepute(int iRepute) {
        this.iRepute = iRepute;
    }

    public int getiMainSpeExp() {
        return iMainSpeExp;
    }

    public void setiMainSpeExp(int iMainSpeExp) {
        this.iMainSpeExp = iMainSpeExp;
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

    public int getiOnlineTime() {
        return iOnlineTime;
    }

    public void setiOnlineTime(int iOnlineTime) {
        this.iOnlineTime = iOnlineTime;
    }

    public int getiGameTime() {
        return iGameTime;
    }

    public void setiGameTime(int iGameTime) {
        this.iGameTime = iGameTime;
    }

    public int getiLoginWay() {
        return iLoginWay;
    }

    public void setiLoginWay(int iLoginWay) {
        this.iLoginWay = iLoginWay;
    }
}
