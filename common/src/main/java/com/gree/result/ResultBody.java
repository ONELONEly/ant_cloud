package com.gree.result;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class ResultBody {

    /**
     * 响应代码
     */
    private static final String CODE = "code";

    /**
     * 响应消息
     */
    private static final String MESSAGE = "message";

    /**
     * 响应结果
     */
    private static final String DATA = "data";

    public static String success() {
        return result(ResponseInfoEnum.SUCCESS, null);
    }

    public static String success(Object data) {
        return result(ResponseInfoEnum.SUCCESS, data);
    }

    public static String success(ResponseInfo response) {
        return result(response, null);
    }

    public static String error() {
        return result(ResponseInfoEnum.ERROR, null);
    }

    public static String error(String message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(CODE, ResponseInfoEnum.ERROR.getResponseMsg());
        jsonObject.put(MESSAGE, message);
        return JSON.toJSONString(jsonObject, SerializerFeature.DisableCircularReferenceDetect);
    }

    public static String error(ResponseInfo response) {
        return result(response, null);
    }

    public static String result(ResponseInfo response, Object data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(CODE, response.getResponseCode());
        jsonObject.put(MESSAGE, response.getResponseMsg());
        jsonObject.put(DATA, data);
        return JSON.toJSONString(jsonObject, SerializerFeature.DisableCircularReferenceDetect);
    }
}