package com.gree.exception;


import com.gree.result.ResponseInfo;

public class KellyException extends RuntimeException{

    private Integer code;

    public KellyException(ResponseInfo responseInfo) {
        super(responseInfo.getResponseMsg());
        this.code = responseInfo.getResponseCode();
    }

    public KellyException(String msg,Integer code){
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
