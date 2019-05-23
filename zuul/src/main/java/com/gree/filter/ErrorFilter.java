package com.gree.filter;

import com.gree.exception.KellyException;
import com.gree.result.RestErrorResponse;
import com.gree.result.RestResponse;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class ErrorFilter extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return -1;
    }

    @Override
    public boolean shouldFilter() {
        return RequestContext.getCurrentContext().containsKey("throwable");
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext rc = RequestContext.getCurrentContext();
        Throwable throwable = rc.getThrowable();
        rc.remove("throwable");
        RestErrorResponse errorResponse = getResponseInfo(throwable);
        String errorMsg = new RestResponse<>().errorJson(errorResponse.getErrorCode(),errorResponse.getErrorMsg(),errorResponse);
        rc.setResponseBody(errorMsg);
        rc.setResponseStatusCode(Integer.parseInt(errorResponse.getErrorCode()));
        rc.getResponse().setContentType("application/json;charset=utf-8");
        return null;
    }

    private RestErrorResponse getResponseInfo(Throwable exception){
        Throwable error = getKellyException(exception,0,5);
        RestErrorResponse errorResponse;
        if(error instanceof KellyException){
            KellyException kellyException = (KellyException)error;
            errorResponse = new RestErrorResponse(kellyException.getCode(),kellyException.getMessage(),kellyException.getStackTrace(),new Date(),kellyException.getName());
        }else{
            String msg = error.getCause() == null ? error.getMessage() : error.getCause().getMessage();
            errorResponse = new RestErrorResponse("500",msg,error.getStackTrace(),new Date(), error.getClass().getName());
        }
        return errorResponse;
    }

    private Throwable getKellyException(Throwable error,Integer now,Integer max){
        if(now < max) {
            if (!(error instanceof KellyException)) {
                return error.getCause() == null ? error : getKellyException(error.getCause(),now+1,max);
            } else {
                return error;
            }
        }else{
            return error;
        }
    }
}
