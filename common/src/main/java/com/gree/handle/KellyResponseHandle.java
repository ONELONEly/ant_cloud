package com.gree.handle;

import com.alibaba.fastjson.JSON;
import com.gree.result.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;

@ControllerAdvice
public class KellyResponseHandle implements ResponseBodyAdvice<Object> {
    private Logger logger = LoggerFactory.getLogger(getClass());

    // 这个方法表示对于哪些请求要执行beforeBodyWrite，返回true执行，返回false不执行
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    //// 对于返回的对象如果不是最终对象ResponseResult，则选包装一下
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        String url = serverHttpRequest.getURI().getPath();
        logger.info("返回结果，urI:{},url:{},body:{}",serverHttpRequest.getURI(),url,JSON.toJSONString(o));
        if(url.contains("swagger-resources") || "/v2/api-docs".equals(url)){
            return o;
        }
        serverHttpResponse.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        if(!(o instanceof RestResponse)){
            if(o instanceof String){ //如果返回的o为空，或许也需要做同样的方式处理
                return new RestResponse<>().successJson(o);
            }
            return new RestResponse<>().success(o);
        }
        return o;
    }
}
