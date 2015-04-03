package com.shadowinlife.app.LogAnalyse.SQLModelFactory;

import java.sql.Date;

/**
 * @author shadowinlife
 * 
+-------------+------------------+------+-----+---------+-------+
| Field       | Type             | Null | Key | Default | Extra |
+-------------+------------------+------+-----+---------+-------+
| dtEventTime | datetime         | YES  |     | NULL    |       |
| iUin        | varchar(64)      | NO   |     |         |       |
| iRoleLevel  | int(11) unsigned | NO   |     | 0       |       |
| iMoney      | int(11) unsigned | NO   |     | 0       |       |
+-------------+------------------+------+-----+---------+-------+
 */
public class Pay {
    private Date dtEventTime;
    private String iUin;
    private int iRoleLevel;
    private int iMoney;

    private Pay(String dtEventTime, String iUin, String iRoleLevel, String iMoney) {
        try {
            this.dtEventTime = Date.valueOf(dtEventTime);
            this.iUin = iUin;
            this.iRoleLevel = Integer.valueOf(iRoleLevel);
            this.iMoney = Integer.valueOf(iMoney);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public Date getDtEventTime() {
        return dtEventTime;
    }

    public void setDtEventTime(Date dtEventTime) {
        this.dtEventTime = dtEventTime;
    }

    public String getiUin() {
        return iUin;
    }

    public void setiUin(String iUin) {
        this.iUin = iUin;
    }

    public int getiRoleLevel() {
        return iRoleLevel;
    }

    public void setiRoleLevel(int iRoleLevel) {
        this.iRoleLevel = iRoleLevel;
    }

    public int getiMoney() {
        return iMoney;
    }

    public void setiMoney(int iMoney) {
        this.iMoney = iMoney;
    }

    public static Pay parseFromLogFile(String logline) {
        String[] columnList = logline.split("\\|", 10);
        if (columnList.length == 4) {
            for (int i = 0; i < 4; i++) {
                if (columnList[i] == null) {
                    columnList[i] = "-1";
                }
            }
            return new Pay(columnList[0], columnList[1], columnList[2], columnList[3]);
        } else {
            return null;
        }
    }
}
