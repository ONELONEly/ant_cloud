package com.gree.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Feign请求拦截器（设置请求头，传递登录信息）
 *
 * 解决Feign无法同步session的异常
 *
 **/
@Configuration
public class FeignClientsConfigurationCustom implements RequestInterceptor {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Bean
    public FeignHystrixConcurrencyStrategy feignHystrixConcurrencyStrategy() {
        return new FeignHystrixConcurrencyStrategy();
    }

    @Override
    public void apply(RequestTemplate template) {

        logger.info("开始header的录入");

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return;
        }

        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                Enumeration<String> values = request.getHeaders(name);
                while (values.hasMoreElements()) {
                    String value = values.nextElement();
                    if(!"{}".equals(value)){
                        template.header(name, value);
                    }
                    logger.info("headerName:{},headerValue:{}",name,value);
                }
            }
        }

        logger.info("结束header的录入");
    }

}