package org.wuancake.entity;

import java.util.Date;

public class ReportBean {
    private Integer week_num;
    private Integer user_id;
    private Integer group_id;
    private String text;
    private Integer status;
    private Date reply_time;

    public Integer getWeek_num() {
        return week_num;
    }

    public void setWeek_num(Integer week_num) {
        this.week_num = week_num;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
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

    public Date getReply_time() {
        return reply_time;
    }

    public void setReply_time(Date reply_time) {
        this.reply_time = reply_time;
    }
}
