package com.gree.result;

import java.io.Serializable;
import java.util.Date;

/**
 * The type Rest error response.
 *
 * @Description 异常返回类
 * @Author 艺锦欧巴 【jinyuk@foxmail.com/180484@gree.cn.com】
 * @CreateTime 2019 -04-18 19:21:06
 * @Version V 1.0
 */
public class RestErrorResponse implements Serializable {

    private static final long serialVersionUID = 6823739132566541733L;

    /**
     * @Description 异常码
     * @CreateTime 2019 -04-18 19:21:06
     */
    private Integer errorCode;
    /**
     * @Description 异常信息
     * @CreateTime 2019 -04-18 19:21:06
     */
    private String errorMsg;
    /**
     * @Description 异常堆栈
     * @CreateTime 2019 -04-18 19:21:06
     */
    private StackTraceElement[] errorStackTrace;
    /**
     * @Description 异常时间
     * @CreateTime 2019 -04-18 19:21:06
     */
    private Date errorDate;
    /**
     * @Description 异常类型
     * @CreateTime 2019 -04-18 19:21:06
     */
    private String errorType;

    public RestErrorResponse() {
    }

    public RestErrorResponse(Integer errorCode, String errorMsg, StackTraceElement[] errorStackTrace, Date errorDate, String errorType) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.errorStackTrace = errorStackTrace;
        this.errorDate = errorDate;
        this.errorType = errorType;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public StackTraceElement[] getErrorStackTrace() {
        return errorStackTrace;
    }

    public void setErrorStackTrace(StackTraceElement[] errorStackTrace) {
        this.errorStackTrace = errorStackTrace;
    }

    public Date getErrorDate() {
        return errorDate;
    }

    public void setErrorDate(Date errorDate) {
        this.errorDate = errorDate;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }
}
