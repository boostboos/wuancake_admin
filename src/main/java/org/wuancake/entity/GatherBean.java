package org.wuancake.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 考勤汇总
 *
 * @author
 * @date
 */
public class GatherBean {
    private String groupName;
    private String userName;
    private String QQ;
    private Integer id;
    /**
     * 截至四周的周报状态集合
     */
    private Map<Integer, Integer> report4StatusMap;
    /**
     * 是否是保护期 1 是 2 否
     */
    private Integer isUnderProtected;

    public Integer getIsUnderProtected() {
        return isUnderProtected;
    }

    public Map<Integer, Integer> getReport4StatusMap() {
        return report4StatusMap;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getuserName() {
        return userName;
    }

    public void setuserName(String userName) {
        this.userName = userName;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setReport4StatusMap(Map<Integer, Integer> report4StatusMap) {
        this.report4StatusMap = report4StatusMap;
    }

    public void setIsUnderProtected(Integer isUnderProtected) {
        this.isUnderProtected = isUnderProtected;
    }

    @Override
    public String toString() {
        return "GatherBean{" +
                "groupName='" + groupName + '\'' +
                ", userName='" + userName + '\'' +
                ", QQ='" + QQ + '\'' +
                ", id=" + id +
                ", report4StatusMap=" + report4StatusMap +
                ", isUnderProtected=" + isUnderProtected +
                '}';
    }
}
