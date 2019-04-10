package com.gree;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

//允许zuul代理，配置session存储后立刻刷新设置刷新模式为立刻刷新，否则可能获取不到session.
@EnableRedisHttpSession(redisFlushMode = RedisFlushMode.IMMEDIATE)
@EnableZuulProxy
//
// 简化配置，整合了@SpringBootApplication、@EnableDiscoveryClient、@EnableCircuitBreaker
@SpringCloudApplication
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

}

