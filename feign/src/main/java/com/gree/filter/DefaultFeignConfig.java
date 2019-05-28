package com.gree.filter;

import com.gree.util.UserContext;
import feign.Feign;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(Feign.class)
public class DefaultFeignConfig implements RequestInterceptor {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${spring.application.name}")
    private String appName;

    @Override
    public void apply(RequestTemplate requestTemplate)
    {
        String traceId = MDC.get("traceID");
        if(StringUtils.isNotEmpty(traceId)){
            requestTemplate.header("x-trace-id", traceId);
        }

        String xUser = UserContext.getXUser();
        if(StringUtils.isNotEmpty(xUser) && !"{}".equals(xUser)){
            requestTemplate.header("x-user", xUser);
        }

        if(StringUtils.isNotEmpty(appName)){
            requestTemplate.header("x-direct-invoker", appName);
        }
    }

}