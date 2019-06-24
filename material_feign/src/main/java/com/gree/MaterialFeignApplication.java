package com.gree;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hello world!
 *
 */
@EnableFeignClients
@EnableEurekaClient
@EnableDiscoveryClient

@EnableOAuth2Sso
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MaterialFeignApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(MaterialFeignApplication.class, args);
    }
}
