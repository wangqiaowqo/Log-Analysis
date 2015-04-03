package com.shadowinlife.app.LogAnalyse.SQLModelFactory;

import java.sql.Date;

/**
 * 
 * @author shadowinlife
 *
+-------------+------------------+------+-----+---------+-------+
| Field       | Type             | Null | Key | Default | Extra |
+-------------+------------------+------+-----+---------+-------+
| vTableFlage | varchar(64)      | NO   |     |         |       |
| dtEventTime | datetime         | YES  |     | NULL    |       |
| iArea       | int(11) unsigned | NO   |     | 0       |       |
| SrcUin      | varchar(64)      | NO   |     |         |       |
| DstUin      | varchar(64)      | NO   |     |         |       |
| iSourceWay  | int(11) unsigned | NO   |     | 0       |       |
| vClientIp   | varchar(32)      | NO   |     |         |       |
| vReMarks    | varchar(32)      | NO   |     |         |       |
| iRoleId     | int(11) unsigned | NO   |     | 0       |       |
| vRoleName   | varchar(32)      | NO   |     |         |       |
| iJobId      | int(11) unsigned | NO   |     | 0       |       |
| iGender     | int(11) unsigned | NO   |     | 0       |       |
| iRoleLevel  | int(11) unsigned | NO   |     | 0       |       |
| iPayBefore  | int(11) unsigned | NO   |     | 0       |       |
| iPayDelta   | int(11) unsigned | NO   |     | 0       |       |
| iPayAfter   | int(11) unsigned | NO   |     | 0       |       |
| vDesc       | varchar(32)      | NO   |     |         |       |
| iLoginWay   | int(11) unsigned | NO   |     | 0       |       |
+-------------+------------------+------+-----+---------+-------+
 *
 */
public class Recharge {

    private String vTableFlage;
    private Date dtEventTime;
    private int iArea;
    private String srcUin;
    private String dstUin;
    private int iSourceWay;
    private String vClientIp;
    private String vReMarks;
    private int iRoleId;
    private String vRoleName;
    private int iJobId;
    private int iGender;
    private int iRoleLevel;
    private int iPayBefore;
    private int iPayDelta;
    private int iPayAfter;
    private String vDesc;
    private int iLoginWay;

    public String getvTableFlage() {
        return vTableFlage;
    }

    public void setvTableFlage(String vTableFlage) {
        this.vTableFlage = vTableFlage;
    }

    public Date getDtEventTime() {
        return dtEventTime;
    }

    public void setDtEventTime(Date dtEventTime) {
        this.dtEventTime = dtEventTime;
    }

    public int getiArea() {
        return iArea;
    }

    public void setiArea(int iArea) {
        this.iArea = iArea;
    }

    public String getSrcUin() {
        return srcUin;
    }

    public void setSrcUin(String srcUin) {
        this.srcUin = srcUin;
    }

    public String getDstUin() {
        return dstUin;
    }

    public void setDstUin(String dstUin) {
        this.dstUin = dstUin;
    }

    public int getiSourceWay() {
        return iSourceWay;
    }

    public void setiSourceWay(int iSourceWay) {
        this.iSourceWay = iSourceWay;
    }

    public String getvClientIp() {
        return vClientIp;
    }

    public void setvClientIp(String vClientIp) {
        this.vClientIp = vClientIp;
    }

    public String getvReMarks() {
        return vReMarks;
    }

    public void setvReMarks(String vReMarks) {
        this.vReMarks = vReMarks;
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

    public int getiPayBefore() {
        return iPayBefore;
    }

    public void setiPayBefore(int iPayBefore) {
        this.iPayBefore = iPayBefore;
    }

    public int getiPayDelta() {
        return iPayDelta;
    }

    public void setiPayDelta(int iPayDelta) {
        this.iPayDelta = iPayDelta;
    }

    public int getiPayAfter() {
        return iPayAfter;
    }

    public void setiPayAfter(int iPayAfter) {
        this.iPayAfter = iPayAfter;
    }

    public String getvDesc() {
        return vDesc;
    }

    public void setvDesc(String vDesc) {
        this.vDesc = vDesc;
    }

    public int getiLoginWay() {
        return iLoginWay;
    }

    public void setiLoginWay(int iLoginWay) {
        this.iLoginWay = iLoginWay;
    }
}
