package com.gree.result;

public enum ResponseInfoEnum implements ResponseInfo{
    SUCCESS("200", "success"),
    ERROR("500", "error"),
    NONE_USER("401","请确认用户名和密码是否正确"),
    EMPTY_USER_MSG("500","请确认用户登录信息"),
    JSON_CONVERT_ERROR("500","请求数据转换异常"),
    EMPTY_RESULT("500","空返回异常"),
    BLOB_CONVERT_ERROR("500","blob文件转换异常"),
    CLOB_CONVERT_ERROR("500","clob文件转换异常"),
    FOS_CLOSE_ERROR("500","fos流关闭异常"),
    OS_CLOSE_ERROR("500","os流关闭异常"),
    IO_CLOSE_ERROR("500","io流关闭异常"),
    IOS_CLOSE_ERROR("500","ios流关闭异常"),
    IO_ERROR("500","流转换异常"),
    CHARSET_CONVERT_ERROR("500","编码转换异常"),

    DB_SAVE_UPDATE_EMPTY("501","插入修改数据库空操作");

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
