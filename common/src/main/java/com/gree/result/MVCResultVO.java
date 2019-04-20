package com.gree.result;

public class MVCResultVO implements ResponseInfo{

    private String code;
    private String  msg;

    public MVCResultVO(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getResponseCode() {
        return this.code;
    }

    @Override
    public String getResponseMsg() {
        return this.msg;
    }
}
