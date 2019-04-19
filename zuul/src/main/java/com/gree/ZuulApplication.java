package com.gree;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

//配置session存储后立刻刷新设置刷新模式为立刻刷新，否则可能获取不到session.
@EnableRedisHttpSession(redisFlushMode = RedisFlushMode.IMMEDIATE,maxInactiveIntervalInSeconds = 86400*30)

@EnableZuulProxy
//
// 简化配置，整合了@SpringBootApplication、@EnableDiscoveryClient、@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableCircuitBreaker

@EnableFeignClients

@EnableOAuth2Sso

@EnableCaching //内部调用，非Public方法上使用注解，会导致缓存无效。&& SpringCache是基于Spring AOP动态代理实现，同一个类中调用类中另一个方法，会导致另一个方法的缓存不能使用！
@EnableAspectJAutoProxy(exposeProxy = true) //通过再次代理调用（AopContext.currentProxy().getXXX），前提要开启暴露

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.gree.mapper")
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

}

