package org.wuancake.entity;

import org.apache.ibatis.annotations.Param;

/**
 * @author Ericheel
 * @Description: 导师实体
 * @date 2018/5/2413:46
 */

public class TutorBean {


    private String username;
    private String email;
    private String password;
    private final Integer auth = 1;
    private Integer group_id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAuth() {
        return auth;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }
}
