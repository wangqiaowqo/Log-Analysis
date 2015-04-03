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
| vClientIp   | varchar(32)      | NO   |     |         |       |
| iRoleId     | int(11) unsigned | NO   |     | 0       |       |
| vRoleName   | varchar(32)      | NO   |     |         |       |
| iJobId      | int(11) unsigned | NO   |     | 0       |       |
| iGender     | int(11) unsigned | NO   |     | 0       |       |
| iLoginWay   | int(11) unsigned | NO   |     | 0       |       |
+-------------+------------------+------+-----+---------+-------+
 */

public class CreateRole {
    private String vTableFlage;
    private String iEventId;
    private String iUin;
    private Date dtEventTime;
    private String vClientIp;
    private int iRoleId;
    private String vRoleName;
    private int iJobId;
    private int iGender;
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

    public int getiLoginWay() {
        return iLoginWay;
    }

    public void setiLoginWay(int iLoginWay) {
        this.iLoginWay = iLoginWay;
    }
}
