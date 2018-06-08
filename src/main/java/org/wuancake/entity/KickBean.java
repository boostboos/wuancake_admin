package org.wuancake.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * @author Ericheel
 * @Description: 被踢
 * @date 2018/6/713:21
 */
public class KickBean {
    private String groupName;
    private String userName;
    @JsonProperty(value = "QQ")
    private String QQ;
    private String headsman;
    private Date modifyTime;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getHeadsman() {
        return headsman;
    }

    public void setHeadsman(String headsman) {
        this.headsman = headsman;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
