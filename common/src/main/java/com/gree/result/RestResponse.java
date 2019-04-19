package com.gree.result;

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
     * @Description 返回结果
     * @CreateTime 2019 -04-18 19:21:06
     */
    private T result;

    /**
     * @Description
     * @CreateTime 2019 -04-18 19:21:06
     */
    private RestErrorResponse errorResponse;

    public RestResponse() {
    }

    public RestResponse(T result, RestErrorResponse errorResponse) {
        this.result = result;
        this.errorResponse = errorResponse;
    }

    public T getResult() {
        return result;
    }

    public RestErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public String getRestId() {
        return restId;
    }
}
