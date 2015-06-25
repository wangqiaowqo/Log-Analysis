package com.shadowinlife.app.SQLModelFactory;

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
 | iArea        | long             | NO   |     |         |       |
 | DstUin       | varchar(8)       | NO   |     |         |       |
 | iSouceWay    | int(11) unsigned | NO   |     | 0       |       |
 | vClientIp    | long             | NO   |     | 0       |       |
 | vReMarks     | String           | NO   |     | 0       |       |
 | iPayBefore   | int              | NO   |     | 0       |       |
 | iPayDelta    | int              | YES  |     | NULL    |       |
 | iPayAfter    | int(11) unsigned | NO   |     | 0       |       |
 | vDesc        | String           | NO   |     | 0       |       |
 | iLoginWay    | String           | NO   |     | 0       |       |
 +--------------+------------------+------+-----+---------+-------+
 */
import java.io.Serializable;
import java.sql.Timestamp;

import com.shadowinlife.app.Tools.CONSTANT;

import jodd.util.StringUtil;

public class Deposit extends BaseBean implements Serializable {

   
    private static final long serialVersionUID = 1L;
    private int iAccountType;
    private int iGameId;
    private int iWorldId;
    private Timestamp dtEventTime;
    private String iEventId;
    private String vVersionId;
    private String vUin;
    private int iRoleId;
    private String vRoleName;
    private int iRoleJob;
    private int iRoleGender;
    private int iRoleLevel;
    private int iRoleVipLevel;
    private int iRoleReputationLevel;
    private String vRoleElse1;
    private String vRoleElse2;

    private long iArea;
    private String vDstUin;
    private String iSourceWay;
    private Long vClientIp;
    private String vRemarks;
    private int iPayBefore;
    private int iPayDelta;
    private int iPayAfter;
    private String vDesc;
    private String vLoginWay;

    public Deposit(String GameId, String AccountType, String WorldId, String dtEventTime, String iEventId, String version, String iUin,
            String iRoleId, String vRoleName, String iRoleJob, String iRoleSex, String iRoleLevel,
            String iRoleVipLevel, String iRepuLevel, String v1, String v2, String iArea,
            String DstUin, String iSourceWay, String vClientIp, String vRemark, String iPayBefore,
            String iPayDelta, String iPayAfter, String vDesc, String iLoginWay) {
        this.iGameId = Integer.valueOf(GameId);
        this.iAccountType = Integer.valueOf(AccountType);
        this.iWorldId = Integer.valueOf(WorldId);
        this.dtEventTime = Timestamp.valueOf(dtEventTime);
        this.iEventId = iEventId;
        this.vVersionId = version;
        this.vUin = iUin;
        this.iRoleId = Integer.valueOf(iRoleId);
        this.vRoleName = vRoleName;
        this.iRoleJob = Integer.valueOf(iRoleJob);
        this.iRoleGender = Integer.valueOf(iRoleSex);
        this.iRoleLevel = Integer.valueOf(iRoleLevel);
        this.iRoleVipLevel = Integer.valueOf(iRoleVipLevel);
        this.iRoleReputationLevel = Integer.valueOf(iRepuLevel);
        this.vRoleElse1 = v1;
        this.vRoleElse2 = v2;

        this.iArea = Long.valueOf(iArea);
        this.vDstUin = DstUin;
        this.iSourceWay = iSourceWay;
        this.vClientIp = CONSTANT.ipToLong(vClientIp);
        this.vRemarks = vRemark;
        this.iPayBefore = Integer.valueOf(iPayBefore);
        this.iPayDelta = Integer.valueOf(iPayDelta);
        this.iPayAfter = Integer.valueOf(iPayAfter);
        this.vDesc = vDesc;
        this.vLoginWay = iLoginWay;
    }

    public Deposit(){
       super();
    }
    public Deposit parseFromLogFile(String[] array, String GameId, String AccountType, String WorldId) {
        String[] columnList = array;

        if (columnList.length == 24) {
            for (int i = 0; i < 24; i++) {
                if (StringUtil.isEmpty(columnList[i])) {
                    columnList[i] = "0";
                }
            }
            return new Deposit(GameId, AccountType, WorldId, columnList[1], columnList[2], columnList[3], columnList[4],
                    columnList[5], columnList[6], columnList[7], columnList[8], columnList[9],
                    columnList[10], columnList[11], columnList[12], columnList[13], columnList[14],
                    columnList[15], columnList[16], columnList[17], columnList[18], columnList[19],
                    columnList[20], columnList[21], columnList[22], columnList[23]);
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
        return vVersionId;
    }

    public void setVersion(String version) {
        this.vVersionId = version;
    }

    public String getiUin() {
        return vUin;
    }

    public void setiUin(String iUin) {
        this.vUin = iUin;
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
        return iRoleGender;
    }

    public void setiRoleSex(int iRoleSex) {
        this.iRoleGender = iRoleSex;
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
        return iRoleReputationLevel;
    }

    public void setiRepuLevel(int iRepuLevel) {
        this.iRoleReputationLevel = iRepuLevel;
    }

    public String getV1() {
        return vRoleElse1;
    }

    public void setV1(String v1) {
        this.vRoleElse1 = v1;
    }

    public String getV2() {
        return vRoleElse2;
    }

    public void setV2(String v2) {
        this.vRoleElse2 = v2;
    }

    public long getiArea() {
        return iArea;
    }

    public void setiArea(long iArea) {
        this.iArea = iArea;
    }

    public String getDstUin() {
        return vDstUin;
    }

    public void setDstUin(String dstUin) {
        vDstUin = dstUin;
    }

    public String getiSourceWay() {
        return iSourceWay;
    }

    public void setiSourceWay(String iSourceWay) {
        this.iSourceWay = iSourceWay;
    }

    public Long getvClientIp() {
        return vClientIp;
    }

    public void setvClientIp(Long vClientIp) {
        this.vClientIp = vClientIp;
    }

    public String getvRemark() {
        return vRemarks;
    }

    public void setvRemark(String vRemark) {
        this.vRemarks = vRemark;
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

    public String getiLoginWay() {
        return vLoginWay;
    }

    public void setiLoginWay(String iLoginWay) {
        this.vLoginWay = iLoginWay;
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
