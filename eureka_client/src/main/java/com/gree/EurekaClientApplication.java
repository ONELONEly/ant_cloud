package com.gree;

import com.gree.bean.MybatisConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableEurekaClient
@EnableCaching
@EnableScheduling
@Import(value = MybatisConfig.class)

@EnableGlobalMethodSecurity(prePostEnabled = true)

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class EurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }

}

