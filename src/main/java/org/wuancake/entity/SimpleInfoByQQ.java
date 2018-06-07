package org.wuancake.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Ericheel
 * @Description: 简单查询
 * @date 2018/6/616:51
 */
public class SimpleInfoByQQ {
    @JsonProperty(value = "QQ")
    private String QQ;
    private String userName;
    private String groupName;

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
