package com.gree.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel(value = "user",description = "用户对象.")
public class User implements Serializable {

    @ApiModelProperty(value = "用户ID，邮箱号",name = "usid",example = "180484",dataType = "String",required = true)/*hidden隐藏*/
    private String usid;
    @ApiModelProperty(value = "用户名",name = "userName",example = "锦遇")
    private String userName;
    @ApiModelProperty(value = "密码",name = "passWord",example = "qwe!23")
    private String passWord;

    public User() {
    }

    public User(String usid, String userName, String passWord) {
        this.usid = usid;
        this.userName = userName;
        this.passWord = passWord;
    }

    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getUsid() {
        return usid;
    }

    public void setUsid(String usid) {
        this.usid = usid;
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
}
