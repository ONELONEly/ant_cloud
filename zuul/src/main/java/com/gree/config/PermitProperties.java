package com.gree.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermitProperties {

    /**
     * 监控中心和swagger需要访问的url
     */
    private static final String[] ENDPOINTS = {
            "/MICROSERVICEAUTH/oauth/**",
            "/MICROSERVICEAUTH/authToken/**",
            "/actuator/**",
            "/*/v2/api-docs",
            "/swagger/api-docs",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/webjars/**",
            "/druid/**",
            "/user/login"
    };

    /**
     * 设置不用认证的url
     */
    private String[] httpUrls = {};

    /**
     * 设置认证后不需要判断具体权限的url，所有登录的人都能访问
     */
    private String[] menusPaths = {};

    public String[] getUrls() {
        if (httpUrls == null || httpUrls.length == 0) {
            return ENDPOINTS;
        }
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(ENDPOINTS));
        list.addAll(Arrays.asList(httpUrls));
        return list.toArray(new String[0]);
    }
}
