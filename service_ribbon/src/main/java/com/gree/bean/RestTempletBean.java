package com.gree.bean;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Configuration
public class RestTempletBean {

    @Bean
    @LoadBalanced // 开启客户端负载均衡的能力
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
