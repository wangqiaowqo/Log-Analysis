package com.shadowinlife.app.LogAnalyse.SQLModelFactory;

import java.io.Serializable;
import java.sql.Timestamp;

import jodd.util.StringUtil;

import com.shadowinlife.app.LogAnalyse.ProcessTableSQL.CONSTANT;

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
public class RoleLogin implements Serializable {
   
    private static final long serialVersionUID = -5208592223307470669L;
    
    private String iEventId;
    private String iUin;
    private Timestamp dtEventTime;
    private long vClientIp;
    private String vZoneId; 
    private int iRoleId;
    private String vRoleName;
    private int iRoleLevel;
    private int iRoleExp;
    private int iRoleSpe;
    private int iEnergy;
    private int iMoney;
    private int iGamePoints;
    private Timestamp dtCreateTime;
    private int iOnlineTime;
    private int iLoginWay;

    private RoleLogin(String iEventId, String iUin, String dtEventTime, String vClientIp,
            String vZoneId, String iRoleId, String vRoleName, String iRoleLevel, String iRoleExp,
            String iRoleSpe, String iEnergy, String iMoney, String iGamePoints,
            String dtCreateTime, String iOnlineTime, String iLoginWay) {
        
            this.iEventId = iEventId;
            this.iUin = iUin;
            this.dtEventTime = Timestamp.valueOf(dtEventTime);
            this.vClientIp = CONSTANT.ipToLong(vClientIp);
            this.vZoneId = vZoneId;
            this.iRoleId = Integer.valueOf(iRoleId);
            this.vRoleName = vRoleName;
            this.iRoleLevel = Integer.valueOf(iRoleLevel);
            this.iRoleExp = Integer.valueOf(iRoleExp);
            this.iRoleSpe = Integer.valueOf(iRoleSpe);
            this.iEnergy = Integer.valueOf(iEnergy);
            this.iMoney = Integer.valueOf(iMoney);
            this.iGamePoints = Integer.valueOf(iGamePoints);
            this.dtCreateTime = Timestamp.valueOf(dtCreateTime);
            this.iOnlineTime = Integer.valueOf(iOnlineTime);
            this.iLoginWay = Integer.valueOf(iLoginWay);
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

    public Timestamp getDtEventTime() {
        return dtEventTime;
    }

    public void setDtEventTime(Timestamp dtEventTime) {
        this.dtEventTime = dtEventTime;
    }

    public long getvClientIp() {
        return vClientIp;
    }

    public void setvClientIp(long vClientIp) {
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

    public Timestamp getDtCreateTime() {
        return dtCreateTime;
    }

    public void setDtCreateTime(Timestamp dtCreateTime) {
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

    public int getiRoleExp() {
        return iRoleExp;
    }

    public void setiRoleExp(int iRoleExp) {
        this.iRoleExp = iRoleExp;
    }

    public static RoleLogin parseFromLogFile(String[] array) {
        String[] columnList = array;
        //TODO Some column may be null,should handle it
        if (columnList.length == 16) {
            for (int i = 0; i < 16; i++) {
                if (StringUtil.isEmpty(columnList[i])) {
                    columnList[i] = "-1";
                }
            }
            return new RoleLogin(columnList[1], columnList[2], columnList[3], columnList[4],
                    columnList[5], columnList[6], columnList[7], columnList[8], columnList[9],
                    columnList[10], columnList[11], columnList[12], columnList[13], columnList[14],
                    columnList[15], columnList[16]);
        } else {
            return null;
        }
    }
    @Override
    public String toString(){
        return String.format("%s %s %s %s %s %s %s %s %s %s %s %s %s  %s  %s  %s",
                iEventId, iUin, dtEventTime,  vClientIp,
                vZoneId, iRoleId,  vRoleName,  iRoleLevel,  iRoleExp,
                 iRoleSpe,  iEnergy,  iMoney,  iGamePoints,
                 dtCreateTime,  iOnlineTime,  iLoginWay);
    }
}
