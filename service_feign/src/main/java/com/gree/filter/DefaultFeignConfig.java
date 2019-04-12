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

        logger.info("开始对一些预备的数据进行传输");

        String traceId = MDC.get("traceID");
        if(StringUtils.isNotEmpty(traceId)){
            requestTemplate.header("x-trace-id", traceId);
        }
        String xUser = UserContext.getXUser();
        if(StringUtils.isNotEmpty(xUser)){
            requestTemplate.header("x-user", xUser);
        }
        String platform = UserContext.getPlatform();
        if(StringUtils.isNotEmpty(platform)){
            requestTemplate.header("x-platform", platform);
        }
        String version = UserContext.getVersion();
        if(StringUtils.isNotEmpty(version)){
            logger.info("传输过程{}",version);
            requestTemplate.header("x-version", "1.2");
        }
        if(StringUtils.isNotEmpty(appName)){
            logger.info("传输过程{}",appName);
            requestTemplate.header("x-direct-invoker", appName);
        }

        logger.info("传输结束");
    }

}