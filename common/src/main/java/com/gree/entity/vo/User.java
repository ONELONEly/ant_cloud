package com.gree.entity.vo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel(value = "user",description = "用户对象.")
@TableName("CBASE000")
public class User implements Serializable {
    private static final long serialVersionUID = -6503641354969988281L;

    @ApiModelProperty(value = "用户ID，邮箱号",name = "usid",example = "180484",dataType = "String",required = true)/*hidden隐藏*/
    @TableId(value = "usid",type = IdType.INPUT)
    private String usid;
    @ApiModelProperty(value = "用户名",name = "userName",example = "锦遇")
    @TableField(value = "dsca")
    private String userName;
    @ApiModelProperty(value = "密码",name = "passWord",example = "qwe!23")
    @TableField(value = "pawd")
    private String passWord;
    @ApiModelProperty(hidden = true)
    @Version
    private Integer version;

    public User() {
    }

    public User(String usid, String userName, String passWord) {
        this.usid = usid;
        this.userName = userName;
        this.passWord = passWord;
    }

    public User(String usid, String userName, String passWord, Integer version) {
        this.usid = usid;
        this.userName = userName;
        this.passWord = passWord;
        this.version = version;
    }

    @TableField(exist = false)
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
