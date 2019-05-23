package com.gree.bean;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Create rest api docket.
     *
     * @return the docket
     * @Description 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义指定要建立API的目录。
     * @Author 艺锦欧巴 【jinyuk@foxmail.com/180484@gree.cn.com】
     * @CreateTime 2019 -04-09 09:18:41
     * @Version V 1.0
     */
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gree.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * Api info api info.
     *
     * @return the api info
     * @Description 创建接口的信息
     * @Author 艺锦欧巴 【jinyuk@foxmail.com/180484@gree.cn.com】
     * @CreateTime 2019 -04-09 09:17:14
     * @Version V 1.0
     */
    private ApiInfo apiInfo(){
        return new ApiInfo("Ant项目接口文档","Ant团队开发","1.0","http://10.1.18.83:8887/ant/",contact(),"","",new ArrayList<>());
    }

    /**
     * Contact contact.
     *
     * @return the contact
     * @Description 创建一个联系方式
     * @Author 艺锦欧巴 【jinyuk@foxmail.com/180484@gree.cn.com】
     * @CreateTime 2019 -04-09 09:17:36
     * @Version V 1.0
     */
    private Contact contact(){
        return new Contact("JinYu","http://10.1.18.83:8887/ant/","180484@gree.cn.com/jinyuk@foxmail.com");
    }
}
