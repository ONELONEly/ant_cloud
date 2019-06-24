package com.gree;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * The type Service feign application.
 *
 * @Description
 *               /v2/api-docs 获得所有接口的接口信息
 *               /swagger-ui.html#/ 获得swagger-ui的接口界面
 * @Author 艺锦欧巴 【jinyuk@foxmail.com/180484@gree.cn.com】
 * @CreateTime 2019 -04-09 09:52:11
 * @Version V 1.0
 */
@EnableFeignClients
@EnableEurekaClient
@EnableDiscoveryClient
@EnableOAuth2Sso
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ServiceFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceFeignApplication.class, args);
    }

}

