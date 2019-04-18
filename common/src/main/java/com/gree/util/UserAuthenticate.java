package com.gree.util;

import java.util.List;

/**
 * The type User authenticate.
 *
 * @Description 用户权限实体
 * @Author 艺锦欧巴 【jinyuk@foxmail.com/180484@gree.cn.com】
 * @CreateTime 2019 -04-16 08:32:38
 * @Version V 1.0
 */
public class UserAuthenticate {

    /**
     * @Description 是否激活
     * @Version V 1.0
     */
    private String active;

    /**
     * @Description 时效性
     * @Version V 1.0
     */
    private String exp;

    /**
     * @Description 用户名
     * @Version V 1.0
     */
    private String user_name;

    /**
     * @Description 权限
     * @Version V 1.0
     */
    private List<String> authorities;

    /**
     * @Description 用户ID
     * @Version V 1.0
     */
    private String client_id;

    /**
     * @Description 权限
     * @Version V 1.0
     */
    private List<String> scope;

    public UserAuthenticate() {
    }

    public UserAuthenticate(String active, String exp, String user_name, List<String> authorities, String client_id, List<String> scope) {
        this.active = active;
        this.exp = exp;
        this.user_name = user_name;
        this.authorities = authorities;
        this.client_id = client_id;
        this.scope = scope;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public List<String> getScope() {
        return scope;
    }

    public void setScope(List<String> scope) {
        this.scope = scope;
    }
}
