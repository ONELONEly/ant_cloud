package com.gree.result;

public enum ResponseInfoEnum implements ResponseInfo{
    SUCCESS("200", "success"),
    ERROR("500", "error"),
    NONE_USER("401","请确认用户名和密码是否正确"),
    EMPTY_USER_MSG("500","请确认用户登录信息"),
    JSON_CONVERT_ERROR("500","请求数据转换异常"),
    EMPTY_RESULT("500","空返回异常");

    private String code;
    private String message;

    ResponseInfoEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getResponseCode() {
        return this.code;
    }

    @Override
    public String getResponseMsg() {
        return this.message;
    }
}
