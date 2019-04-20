package com.gree.result;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.UUID;

/**
 * The type Rest response.
 *
 * @Description 返回信息统一类
 * @Author 艺锦欧巴 【jinyuk@foxmail.com/180484@gree.cn.com】
 * @CreateTime 2019 -04-18 19:25:30
 * @Version V 1.0
 */
public class RestResponse<T> implements Serializable {
    private static final long serialVersionUID = 7678251761269418716L;

    /**
     * @Description  结果序列码
     * @CreateTime 2019 -04-18 19:21:06
     */
    private String restId = UUID.randomUUID().toString();

    /**
     * @Description 返回码
     * @CreateTime 2019 -04-18 19:21:06
     */
    private String code;

    /**
     * @Description 返回信息
     * @CreateTime 2019 -04-18 19:21:06
     */
    private String msg;

    /**
     * @Description 返回结果
     * @CreateTime 2019 -04-18 19:21:06
     */
    private T data;

    /**
     * @Description
     * @CreateTime 2019 -04-18 19:21:06
     */
    private RestErrorResponse errorResponse;

    public RestResponse() {
    }

    private RestResponse(String code, String msg, T result, RestErrorResponse errorResponse) {
        this.code = code;
        this.msg = msg;
        this.data = result;
        this.errorResponse = errorResponse;
    }

    public T getData() {
        return data;
    }

    public RestErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public String getRestId() {
        return restId;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public RestResponse<T> error(ResponseInfoEnum responseInfoEnum, RestErrorResponse errorResponse){
        return error(responseInfoEnum.getResponseCode(),responseInfoEnum.getResponseMsg(),errorResponse);
    }

    public RestResponse<T> error(String errorCode,String errorMsg,RestErrorResponse errorResponse){
        return new RestResponse<>(errorCode,errorMsg,null,errorResponse);
    }

    public RestResponse<T> success(T result){
        return new RestResponse<>("200","请求成功",result,null);
    }

    public String successJson(T result){
        return JSON.toJSONString(success(result));
    }

    public String errorJson(ResponseInfoEnum responseInfoEnum, RestErrorResponse errorResponse){
        return JSON.toJSONString(error(responseInfoEnum,errorResponse));
    }

    public String errorJson(String errorCode,String errorMsg,RestErrorResponse errorResponse){
        return JSON.toJSONString(error(errorCode,errorMsg,errorResponse));
    }

}
