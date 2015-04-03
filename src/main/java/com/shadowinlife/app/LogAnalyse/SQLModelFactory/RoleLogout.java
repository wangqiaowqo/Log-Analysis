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

    public RoleLogout(String iEventId, String iUin, String dtEventTime, String dtLoginTime,
            String vClientIp, String vZoneId, String iRoleId, String vRoleName, String iRoleLevel,
            String iRoleExp, String iRepute, String iMainSpeExp, String iMoney, String iGamePoints,
            String iOnlineTime, String iGameTime, String iLoginWay) {
        try {
            this.iEventId = iEventId;
            this.iUin = iUin;
            this.dtEventTime = Date.valueOf(dtEventTime);
            this.dtLoginTime = Date.valueOf(dtLoginTime);
            this.vClientIp = vClientIp;
            this.vZoneId = vZoneId;
            this.iRoleId = Integer.valueOf(iRoleId);
            this.vRoleName = vRoleName;
            this.iRoleLevel = Integer.valueOf(iRoleLevel);
            this.iRoleExp = Integer.valueOf(iRoleExp);
            this.iRepute = Integer.valueOf(iRepute);
            this.iMainSpeExp = Integer.valueOf(iMainSpeExp);
            this.iMoney = Integer.valueOf(iMoney);
            this.iGamePoints = Integer.valueOf(iGamePoints);
            this.iOnlineTime = Integer.valueOf(iOnlineTime);
            this.iGameTime = Integer.valueOf(iGameTime);
            this.iLoginWay = Integer.valueOf(iLoginWay);
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

    public static RoleLogout parseFromLogFile(String logline) {
        String[] columnList = logline.split("\\|", 18);
        if (columnList.length == 18) {
            for (int i = 0; i < 18; i++) {
                if (columnList[i] == null) {
                    columnList[i] = "-1";
                }
            }
            return new RoleLogout(columnList[1], columnList[2], columnList[3], columnList[4],
                    columnList[5], columnList[6], columnList[7], columnList[8], columnList[9],
                    columnList[10], columnList[11], columnList[12], columnList[13], columnList[14],
                    columnList[15], columnList[16], columnList[17]);
        } else {
            return null;
        }
    }
}
