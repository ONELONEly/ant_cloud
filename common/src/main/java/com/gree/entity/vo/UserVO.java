package com.gree.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.HashMap;

@ApiModel(value = "user",description = "用户对象.")
public class UserVO implements Serializable {
    private static final long serialVersionUID = -6503641354969988281L;

    @ApiModelProperty(value = "用户ID，邮箱号",name = "userId",example = "180484",dataType = "String",required = true)/*hidden隐藏*/
    private String userId;

    @ApiModelProperty(value = "用户名",name = "userName",example = "锦遇")
    private String userName;

    @ApiModelProperty(value = "密码",name = "passWord",example = "qwe!23")
    private String passWord;

    private HashMap<String,String> requestJson;

    public UserVO() {
    }

    public UserVO(String userId, String userName, String passWord) {
        this.userId = userId;
        this.userName = userName;
        this.passWord = passWord;
    }

    public HashMap<String, String> getRequestJson() {
        return requestJson;
    }

    public void setRequestJson(HashMap<String, String> requestJson) {
        this.requestJson = requestJson;
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
}
