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

/**
 * Hello world!
 *
 */
@EnableEurekaClient

@EnableScheduling

@EnableCaching
@EnableAspectJAutoProxy(exposeProxy = true)

@EnableGlobalMethodSecurity(prePostEnabled = true)//开启security注解

@MapperScan("com.gree.mapper")

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MaterialServiceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(MaterialServiceApplication.class, args);
    }
}
