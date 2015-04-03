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

    private CreateRole(String vTableFlage, String iEventId, String iUin, String dtEventTime,
            String vClientIp, String iRoleId, String vRoleName, String iJobId, String iGender,
            String iLoginWay) {
        try{
        this.vTableFlage=vTableFlage;
        this.iEventId=iEventId;
        this.iUin=iUin;
        this.dtEventTime=Date.valueOf(dtEventTime);
        } catch(Exception e){
            
        }
    }

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

    public static CreateRole parseFromLogFile(String logline) {
        String[] columnList = logline.split("\\|", 10);
        if (columnList.length == 10) {
            for (int i = 0; i < 10; i++) {
                if (columnList[i] == null) {
                    columnList[i] = "-1";
                }
            }
            return new CreateRole(columnList[0], columnList[1], columnList[2], columnList[3],
                    columnList[4], columnList[5], columnList[6], columnList[7], columnList[8],
                    columnList[9]);
        } else {
            return null;
        }
    }
}
