package org.wuancake.entity;

import java.io.Serializable;

/**
 * Admin实体类
 *
 * @author
 * @date
 */
public class AdminBean implements Serializable {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private Integer auth = 2;
    private final Integer group_id = 0;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public void setAuth(Integer auth) {
        this.auth = auth;
    }

    public Integer getGroup_id() {
        return group_id;
    }


}
