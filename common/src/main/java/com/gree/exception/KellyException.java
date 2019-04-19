package com.gree.exception;


import com.gree.result.ResponseInfo;

import java.util.Date;

public class KellyException extends RuntimeException{

    private static final long serialVersionUID = 9184940296110274987L;
    private Integer code;
    private Date date;
    private String name;


    public KellyException(ResponseInfo responseInfo,Date date,String name) {
        super(responseInfo.getResponseMsg());
        this.code = responseInfo.getResponseCode();
        this.date = date;
        this.name = name;
    }

    public KellyException(String msg,Integer code,Date date,String name){
        super(msg);
        this.code = code;
        this.date = date;
        this.name = name;
    }

    public KellyException(String message, Throwable cause, Integer code,Date date,String name) {
        super(message, cause);
        this.code = code;
        this.date = date;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
