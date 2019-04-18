package com.gree.result;

public enum ResponseInfoEnum implements ResponseInfo{
    SUCCESS(200, "success"),
    ERROR(500, "error"),
    NONE_USER(401,"请确认用户名和密码是否正确"),
    JSON_CONVERT_ERROR(500,"请求数据转换异常");

    private Integer code;
    private String message;

    ResponseInfoEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getResponseCode() {
        return this.code;
    }

    @Override
    public String getResponseMsg() {
        return this.message;
    }
}
