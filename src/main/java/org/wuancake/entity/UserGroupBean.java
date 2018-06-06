package org.wuancake.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wuancake.dao.GroupMapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Ericheel
 * @Description: 用户_分组
 * @date 2018/6/523:50
 */
@Component
public class UserGroupBean implements Serializable {
    private Integer id;
    private Integer userId;
    private List<GroupBean> list;
    private Integer deleteFlg;
    private String headsman;
    private Date createTime;
    private Date modifyTime;
    @Autowired
    private GroupMapper groupMapper;

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

    public List<GroupBean> getList() {
        return list;
    }

    public void setList() {
        this.list = groupMapper.showGroup();
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

    @Override
    public String toString() {
        return "UserGroupBean{" +
                "id=" + id +
                ", userId=" + userId +
                ", list=" + list +
                ", deleteFlg=" + deleteFlg +
                ", headsman='" + headsman + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", groupMapper=" + groupMapper +
                '}';
    }
}
