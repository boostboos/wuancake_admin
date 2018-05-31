package org.wuancake.entity;

import java.util.Date;

/**
 * 周报相关Bean
 *
 * @author
 * @date
 */
public class ReportBean {
    private Integer weekNum;
    private Integer userId;
    private Integer groupId;
    private String text;
    private Integer status;
    private Date replyTime;

    @Override
    public String toString() {
        return "ReportBean{" +
                "weekNum=" + weekNum +
                ", userId=" + userId +
                ", groupId=" + groupId +
                ", text='" + text + '\'' +
                ", status=" + status +
                ", replyTime=" + replyTime +
                '}';
    }

    public Integer getWeekNum() {
        return weekNum;
    }

    public void setWeekNum(Integer weekNum) {
        this.weekNum = weekNum;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }
}
