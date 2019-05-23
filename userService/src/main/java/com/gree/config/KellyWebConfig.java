package com.gree.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class KellyWebConfig implements WebMvcConfigurer {

//    @Bean TODO 研究一下如何创建这个bean，目前使用这个之后，feign调用会获得不到值
    public HttpMessageConverters kellyConverters(){
        return new HttpMessageConverters(new FastJsonHttpMessageConverter());
    }
}
