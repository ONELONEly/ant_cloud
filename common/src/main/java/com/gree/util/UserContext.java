package com.gree.util;

import com.alibaba.fastjson.JSON;
import com.gree.exception.KellyException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UserContext {

    public static final ThreadLocal<Map<String,Object>> contextData = new ThreadLocal<>();

    public static final String X_USER = "x-user";

    public static final String X_DIRECT_INVOKER = "xDirectInvoker";

    public static final String ACCESS_TOKEN = "access_token";

    public static String getXUser() {
        return getStringValue(X_USER);
    }
    public static void putXUser(String value) {
        contextData.get().put(X_USER, value);
    }

    public static String getDirectInvoker() {
        return getStringValue(X_DIRECT_INVOKER);
    }
    public static void putDirectInvoker(Object value) {
        contextData.get().put(X_DIRECT_INVOKER, value);
    }

    public static String getToken() {
        return getStringValue(ACCESS_TOKEN);
    }
    public static void putToken(Object value) {
        contextData.get().put(ACCESS_TOKEN, value);
    }

    public static void init() {
        contextData.set(new HashMap<>());
    }

    public static void remove() {
        contextData.remove();
    }

    private static String getStringValue(String key) {
        if(contextData.get() == null) return null;
        Object value = contextData.get().get(key);
        if(value == null) {
            return null;
        }

        return (String)value;
    }

    public static UserAuthenticate parseJson(String jsonStr) {
        try {
            jsonStr = jsonStr.replace("\\", "");
            return JSON.parseObject(jsonStr,UserAuthenticate.class);
        } catch (Exception e) {
            throw new KellyException("header里x-user解析错误：x-user=" + jsonStr + e.getMessage(),"",new Date(),"");
        }
    }


}