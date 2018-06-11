package org.wuancake.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Ericheel
 * @Description: 用户_分组
 * @date 2018/6/523:50
 */
public class UserGroupBean implements Serializable {
    private Integer id;
    private Integer userId;
    private Integer groupId;
    private Integer deleteFlg;
    private String headsman;
    private Date createTime;
    private Date modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getDeleteFlg() {
        return deleteFlg;
    }

    public void setDeleteFlg(Integer deleteFlg) {
        this.deleteFlg = deleteFlg;
    }

    public String getHeadsman() {
        return headsman;
    }

    public void setHeadsman(String headsman) {
        this.headsman = headsman;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
