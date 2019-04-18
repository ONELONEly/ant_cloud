package com.gree.result;

public class MVCResultVO implements ResponseInfo{

    private Integer code;
    private String  msg;

    public MVCResultVO(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getResponseCode() {
        return this.code;
    }

    @Override
    public String getResponseMsg() {
        return this.msg;
    }
}
