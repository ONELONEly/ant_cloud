package com.gree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

//配置session存储后立刻刷新设置刷新模式为立刻刷新，否则可能获取不到session.
@EnableRedisHttpSession(redisFlushMode = RedisFlushMode.IMMEDIATE,maxInactiveIntervalInSeconds = 86400*30)
@EnableDiscoveryClient
@SpringBootApplication
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

}
