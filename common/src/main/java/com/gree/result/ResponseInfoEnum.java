package com.gree.result;

public enum ResponseInfoEnum implements ResponseInfo{
    SUCCESS(200, "success"),
    ERROR(500, "error");

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
