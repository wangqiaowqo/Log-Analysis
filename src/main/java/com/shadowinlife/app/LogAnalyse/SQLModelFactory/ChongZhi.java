package com.shadowinlife.app.LogAnalyse.SQLModelFactory;

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

public class ChongZhi extends BaseBean implements Serializable {

   
    private static final long serialVersionUID = 1L;
   
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

    private long iArea;
    private String DstUin;
    private String iSourceWay;
    private Long vClientIp;
    private String vRemark;
    private int iPayBefore;
    private int iPayDelta;
    private int iPayAfter;
    private String vDesc;
    private String iLoginWay;

    public ChongZhi(String dtEventTime, String iEventId, String version, String iUin,
            String iRoleId, String vRoleName, String iRoleJob, String iRoleSex, String iRoleLevel,
            String iRoleVipLevel, String iRepuLevel, String v1, String v2, String iArea,
            String DstUin, String iSourceWay, String vClientIp, String vRemark, String iPayBefore,
            String iPayDelta, String iPayAfter, String vDesc, String iLoginWay) {

        this.dtEventTime = Timestamp.valueOf(dtEventTime);
        this.iEventId = iEventId;
        this.version = version;
        this.iUin = iUin;
        this.iRoleId = Integer.valueOf(iRoleId);
        this.vRoleName = vRoleName;
        this.iRoleJob = Integer.valueOf(iRoleJob);
        this.iRoleSex = Integer.valueOf(iRoleSex);
        this.iRoleLevel = Integer.valueOf(iRoleLevel);
        this.iRoleVipLevel = Integer.valueOf(iRoleVipLevel);
        this.iRepuLevel = Integer.valueOf(iRepuLevel);
        this.v1 = v1;
        this.v2 = v2;

        this.iArea = Long.valueOf(iArea);
        this.DstUin = DstUin;
        this.iSourceWay = iSourceWay;
        this.vClientIp = CONSTANT.ipToLong(vClientIp);
        this.vRemark = vRemark;
        this.iPayBefore = Integer.valueOf(iPayBefore);
        this.iPayDelta = Integer.valueOf(iPayDelta);
        this.iPayAfter = Integer.valueOf(iPayAfter);
        this.vDesc = vDesc;
        this.iLoginWay = iLoginWay;
    }

    public ChongZhi(){
       super();
    }
    public ChongZhi parseFromLogFile(String[] array) {
        String[] columnList = array;

        if (columnList.length == 24) {
            for (int i = 0; i < 24; i++) {
                if (StringUtil.isEmpty(columnList[i])) {
                    columnList[i] = "0";
                }
            }
            return new ChongZhi(columnList[1], columnList[2], columnList[3], columnList[4],
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

    public long getiArea() {
        return iArea;
    }

    public void setiArea(long iArea) {
        this.iArea = iArea;
    }

    public String getDstUin() {
        return DstUin;
    }

    public void setDstUin(String dstUin) {
        DstUin = dstUin;
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
        return vRemark;
    }

    public void setvRemark(String vRemark) {
        this.vRemark = vRemark;
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
        return iLoginWay;
    }

    public void setiLoginWay(String iLoginWay) {
        this.iLoginWay = iLoginWay;
    }
}
