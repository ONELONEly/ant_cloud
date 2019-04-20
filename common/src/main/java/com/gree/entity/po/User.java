package com.gree.entity.po;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.List;

@TableName("CBASE000")
public class User implements Serializable {
    private static final long serialVersionUID = -6503641354969988281L;

    @TableId(value = "usid",type = IdType.INPUT)
    private String userId;

    @TableField(value = "dsca")
    private String userName;

    @TableField(value = "pawd")
    private String passWord;

    @TableField(exist = false)
    private List<Role> roles;

    @Version
    private Integer version;

    public User() {

    }

    public User(String userId, String userName, String passWord) {
        this.userId = userId;
        this.userName = userName;
        this.passWord = passWord;
    }

    public User(String userId, String userName, String passWord, Integer version) {
        this.userId = userId;
        this.userName = userName;
        this.passWord = passWord;
        this.version = version;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
