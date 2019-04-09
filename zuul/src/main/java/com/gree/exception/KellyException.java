package com.gree.exception;


import com.gree.result.ResponseInfo;

public class KellyException extends RuntimeException {
    private ResponseInfo responseInfo;

    public KellyException(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }

    public ResponseInfo getResponseInfo() {
        return responseInfo;
    }

    public void setResponseInfo(ResponseInfo responseInfo) {
        this.responseInfo = responseInfo;
    }
}
