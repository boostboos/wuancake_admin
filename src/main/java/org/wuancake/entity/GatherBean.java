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
    private String group_name;
    private String user_name;
    private String QQ;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public void setIsUnderProtected(Integer isUnderProtected) {
        this.isUnderProtected = isUnderProtected;
    }

    public Map<Integer, Integer> getReport4StatusMap() {
        return report4StatusMap;
    }

    public void setReport4StatusMap(Map<Integer, Integer> report4StatusMap) {
        this.report4StatusMap = report4StatusMap;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

}
