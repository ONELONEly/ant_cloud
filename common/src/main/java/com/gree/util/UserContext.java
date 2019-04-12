package com.gree.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserContext {

    public static final ThreadLocal<Map<String,Object>> contextData = new ThreadLocal<>();

    public static final String X_USER = "xUser";

    public static final String MEMBER_ID = "memberId";

    public static final String MOBILE = "mobile";

    public static final String SHOP_ID = "shopId";

    public static final String DEVICE_ID = "deviceId";

    public static final String X_PLATFORM = "xPlatform";

    public static final String X_VERSION = "xVersion";

    public static final String X_DIRECT_INVOKER = "xDirectInvoker";

    public static String getXUser() {
        return getStringValue(X_USER);
    }
    public static void putXUser(String value, String platfrom) {
        contextData.get().put(X_USER, value);
        parseJson(value, platfrom);
    }

    public static Long getMemberId() {
        return getLongValue(MEMBER_ID);
    }
    public static void putMemberId(Object value) {
        contextData.get().put(MEMBER_ID, value);
    }

    public static String getMobile() {
        return getStringValue(MOBILE);
    }
    public static void putMobile(Object value) {
        contextData.get().put(MOBILE, value);
    }

    public static Long getShopId() {
        return getLongValue(SHOP_ID);
    }
    public static void putShopId(Object value) {
        contextData.get().put(SHOP_ID, value);
    }

    public static Long getDeviceId() {
        return getLongValue(DEVICE_ID);
    }
    public static void putDeviceId(Object value) {
        contextData.get().put(DEVICE_ID, value);
    }

    public static String getPlatform() {
        return getStringValue(X_PLATFORM);
    }
    public static void putPlatform(Object value) {
        contextData.get().put(X_PLATFORM, value);
    }

    public static String getDirectInvoker() {
        return getStringValue(X_DIRECT_INVOKER);
    }
    public static void putDirectInvoker(Object value) {
        contextData.get().put(X_DIRECT_INVOKER, value);
    }

    public static String getVersion() {
        return getStringValue(X_VERSION);
    }
    public static void putVersion(Object value) {
        contextData.get().put(X_VERSION, value);
    }

    public static void put(String key, Object value) {
        contextData.get().put(key, value);
    }

    public static void init() {
        contextData.set(new HashMap<>());
    }

    public static void remove() {
        contextData.remove();
    }


    private static Long getLongValue(String key) {
        if(contextData.get() == null) return null;
        Object value = contextData.get().get(key);
        if(value == null) {
            return null;
        }

        return (Long)value;
    }

    private static String getStringValue(String key) {
        if(contextData.get() == null) return null;
        Object value = contextData.get().get(key);
        if(value == null) {
            return null;
        }

        return (String)value;
    }

    public static void parseJson(String jsonStr, String platform) {
        try {
            jsonStr = jsonStr.replace("\\", "");
            jsonStr= jsonStr.substring(1, jsonStr.length()-1);
            JSONObject userJson = JSON.parseObject(jsonStr);
            try{
                putMemberId(Long.valueOf(userJson.get("memberId").toString()));
                putMobile(userJson.get("mobile"));
            } catch (Exception ex){
                putMemberId(0L);
                putMobile("13888886666");
            }
        } catch (Exception e) {
            System.out.println("header里x-user解析错误：x-user=" + jsonStr + e.getMessage());
        }
    }


}