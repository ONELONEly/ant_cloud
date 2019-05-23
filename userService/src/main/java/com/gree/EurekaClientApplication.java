package com.gree;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableEurekaClient
@EnableScheduling

@EnableCaching //内部调用，非Public方法上使用注解，会导致缓存无效。&& SpringCache是基于Spring AOP动态代理实现，同一个类中调用类中另一个方法，会导致另一个方法的缓存不能使用！
@EnableAspectJAutoProxy(exposeProxy = true) //通过再次代理调用（AopContext.currentProxy().getXXX），前提要开启暴露

@EnableGlobalMethodSecurity(prePostEnabled = true)//开启security注解
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.gree.mapper") // 扫描mapper地址
public class EurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }

}

