package com.gree.config;

import com.gree.util.DefaultHttpTokenExtractor;
import com.gree.util.HttpTokenExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class HttpAuthenticationManagerConfigurer {


    @Bean
    HttpTokenExtractor httpTokenExtractor(){
        return new DefaultHttpTokenExtractor();
    }
}
