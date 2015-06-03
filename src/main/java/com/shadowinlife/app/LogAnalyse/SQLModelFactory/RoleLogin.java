package com.shadowinlife.app.LogAnalyse.SQLModelFactory;

import java.io.Serializable;
import java.sql.Timestamp;

import jodd.util.StringUtil;

import com.shadowinlife.app.Tools.CONSTANT;

/**
 * 
 * @author shadowinlife
 *
+--------------+------------------+------+-----+---------+-------+
| Field        | Type             | Null | Key | Default | Extra |
+--------------+------------------+------+-----+---------+-------+
| vTableFlage  | varchar(64)      | NO   |     |         |       |
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
| vClientIp    | long             | NO   |     |         |       |
| vZoneId      | varchar(8)       | NO   |     |         |       |
| iRoleExp     | int(11) unsigned | NO   |     | 0       |       |
| iReputation  | int(11) unsigned | NO   |     | 0       |       |
| iEnergy      | int(11) unsigned | NO   |     | 0       |       |
| iMoney       | "json"           | NO   |     | 0       |       |
| dtCreateTime | datetime         | YES  |     | N  ULL    |       |
| iOnlineTime  | int(11) unsigned | NO   |     | 0       |       |
| iLoginWay    | String           | NO   |     | 0       |       |
+--------------+------------------+------+-----+---------+-------+
 *
 */
public class RoleLogin extends BaseBean implements Serializable {

    private static final long serialVersionUID = -5208592223307470669L;
    private int iAccountType;
    private int iGameId;
    private int iWorldId;
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

    private long vClientIp;
    private String vZoneId;
    private long iRoleExp;
    private long iReputation;
    private long iEnergy;
    private String iMoney;
    private Timestamp dtCreateTime;
    private Long iOnlineTime;
    private String iLoginWay;

    private void getBean(String GameId, String AccountType, String WorldId, String dtEventTime, String iEventId, String version, String iUin,
            String iRoleId, String vRoleName, String iRoleJob, String iRoleSex, String iRoleLevel,
            String iRoleVipLevel, String iRepuLevel, String v1, String v2, String vClientIp,
            String vZoneId, String iRoleExp, String iReputation, String iEnergy, String iMoney,
            String dtCreateTime, String iOnlineTime, String iLoginWay) {
        this.iGameId = Integer.valueOf(GameId);
        this.iAccountType = Integer.valueOf(AccountType);
        this.iWorldId = Integer.valueOf(WorldId);
        this.dtEventTime = Timestamp.valueOf(dtEventTime);
        this.iEventId = iEventId;
        this.version = version;
        this.iUin = iUin;
        this.iRoleId = Integer.valueOf(iRoleId);
        this.vRoleName = vRoleName;
        this.iRoleJob = Integer.valueOf(iRoleJob);
        this.iRoleSex = Integer.valueOf(iRoleSex);
        this.iRoleLevel=Integer.valueOf(iRoleLevel);
        this.iRoleVipLevel = Integer.valueOf(iRoleVipLevel);
        this.iRepuLevel = Integer.valueOf(iRepuLevel);
        this.v1 = v1;
        this.v2 = v2;
        this.vClientIp = CONSTANT.ipToLong(vClientIp);
        this.vZoneId=vZoneId;
        this.iRoleExp=Long.valueOf(iRoleExp);
        this.iReputation=Long.valueOf(iReputation);
        this.iEnergy = Long.valueOf(iEnergy);
        this.iMoney = iMoney;
        this.dtCreateTime=Timestamp.valueOf(dtCreateTime);
        this.iOnlineTime=Long.valueOf(iOnlineTime);
        this.iLoginWay=iLoginWay;             
    }

    public RoleLogin() {
        super();
    }

    public  RoleLogin parseFromLogFile(String[] array, String GameId, String AccountType, String WorldId) {
        String[] columnList = array;
        try {
            // TODO Some column may be null,should handle it
            if (columnList.length == 23) {
                for (int i = 0; i < 23; i++) {
                    if (StringUtil.isEmpty(columnList[i])) {
                        columnList[i] = "0";
                    }
                }
                 getBean(GameId, AccountType, WorldId, columnList[1], columnList[2], columnList[3], columnList[4],
                        columnList[5], columnList[6], columnList[7], columnList[8], columnList[9],
                        columnList[10], columnList[11], columnList[12], columnList[13],
                        columnList[14], columnList[15], columnList[16], columnList[17],
                        columnList[18], columnList[19], columnList[20], columnList[21],
                        columnList[22]);
            } else {
                 getBean("0","0","0","1970-01-01 00:00:00", "id", "version", "error_uin", "0", " ",
                        "0", "0", "0", "0", "0", " ", " ", "0.0.0.0", " ", "0", "0", "0", " ",
                        "1970-01-01 00:00:00", "0", " ");
            }
        } catch (Exception e) {
            e.printStackTrace();
             getBean("0","0","0","1970-01-01 00:00:00", "id", "version", "error_uin", "0", " ",
                    "0", "0", "0", "0", "0", " ", " ", "0.0.0.0", " ", "0", "0", "0", " ",
                    "1970-01-01 00:00:00", "0", " ");
        }
        return this;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s %s %s %s %s %s %s %s %s %s  %s  %s  %s", getiEventId(),
                getiUin(), getDtEventTime(), getvClientIp(), getvZoneId(), getiRoleId(),
                getvRoleName(), getiRoleLevel(), getiRoleExp(), getiReputation(), getiEnergy(),
                getiMoney(), getDtCreateTime(), getiOnlineTime(), getiLoginWay());
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

    public long getiRoleExp() {
        return iRoleExp;
    }

    public void setiRoleExp(long iRoleExp) {
        this.iRoleExp = iRoleExp;
    }

    public long getiReputation() {
        return iReputation;
    }

    public void setiReputation(long iReputation) {
        this.iReputation = iReputation;
    }

    public long getiEnergy() {
        return iEnergy;
    }

    public void setiEnergy(long iEnergy) {
        this.iEnergy = iEnergy;
    }

    public String getiMoney() {
        return iMoney;
    }

    public void setiMoney(String iMoney) {
        this.iMoney = iMoney;
    }

    public Timestamp getDtCreateTime() {
        return dtCreateTime;
    }

    public void setDtCreateTime(Timestamp dtCreateTime) {
        this.dtCreateTime = dtCreateTime;
    }

    public long getiOnlineTime() {
        return iOnlineTime;
    }

    public void setiOnlineTime(long iOnlineTime) {
        this.iOnlineTime = iOnlineTime;
    }

    public String getiLoginWay() {
        return iLoginWay;
    }

    public void setiLoginWay(String iLoginWay) {
        this.iLoginWay = iLoginWay;
    }

    public int getiAccountType() {
        return iAccountType;
    }

    public void setiAccountType(int iAccountType) {
        this.iAccountType = iAccountType;
    }

    public int getiGameId() {
        return iGameId;
    }

    public void setiGameId(int iGameId) {
        this.iGameId = iGameId;
    }

    public int getiWorldId() {
        return iWorldId;
    }

    public void setiWorldId(int iWorldId) {
        this.iWorldId = iWorldId;
    }
}
