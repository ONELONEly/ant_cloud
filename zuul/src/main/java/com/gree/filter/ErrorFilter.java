package com.gree.filter;

import com.alibaba.fastjson.JSON;
import com.gree.exception.KellyException;
import com.gree.result.MVCResultVO;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        MVCResultVO resultVO = getResponseInfo(throwable);
        rc.remove("throwable");
        rc.setResponseBody(JSON.toJSONString(resultVO));
        rc.getResponse().setContentType("application/json;charset=utf-8");
        return null;
    }

    private MVCResultVO getResponseInfo(Throwable error){
        MVCResultVO resultVO;
        if(error instanceof KellyException){
            KellyException kellyException = (KellyException)error;
            resultVO = new MVCResultVO(kellyException.getCode(),kellyException.getMessage());
        }else{
            String msg = error.getCause().getMessage();
            resultVO = new MVCResultVO(500,msg);
        }
        return resultVO;
    }
}
