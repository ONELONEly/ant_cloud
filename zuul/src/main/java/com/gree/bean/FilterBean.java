package com.gree.bean;

import com.gree.config.HttpAuthenticationManager;
import com.gree.config.HttpAuthenticationManagerConfigurer;
import com.gree.config.HttpTokenExtractor;
import com.gree.dao.UserDAO;
import com.gree.feign.AuthTokenApi;
import com.gree.filter.ErrorFilter;
import com.gree.filter.PassWordFilter;
import com.gree.filter.TokenFilter;
import com.gree.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * The type Filter bean.
 *
 * @Description zuul Filter注册中心
 * @Author 艺锦欧巴 【jinyuk@foxmail.com/180484@gree.cn.com】
 * @CreateTime 2019 -03-21 10:46:52
 * @Version V 1.0
 */
@Component
@Configuration
@Import({HttpAuthenticationManagerConfigurer.class})
public class FilterBean {

    private final
    HttpAuthenticationManager httpAuthenticationManager;

    private final
    HttpTokenExtractor httpTokenExtractor;

    private final RedisService redisService;

    private final UserDAO userDAO;

    private final AuthTokenApi authTokenApi;

    @Autowired
    public FilterBean(HttpAuthenticationManager httpAuthenticationManager, HttpTokenExtractor httpTokenExtractor, RedisService redisService, UserDAO userDAO, AuthTokenApi authTokenApi) {
        this.httpAuthenticationManager = httpAuthenticationManager;
        this.httpTokenExtractor = httpTokenExtractor;
        this.redisService = redisService;
        this.userDAO = userDAO;
        this.authTokenApi = authTokenApi;
    }

    /**
     * Token filter token filter.
     *
     * @return the token filter
     * @Description token校验器
     * @Author 艺锦欧巴 【jinyuk@foxmail.com/180484@gree.cn.com】
     * @CreateTime 2019 -03-21 10:46:52
     * @Version V 1.0
     */
    @Bean
    public TokenFilter tokenFilter(){
        return new TokenFilter(httpAuthenticationManager,authTokenApi,redisService);
    }

    /**
     * Pass word filter pass word filter.
     *
     * @return the pass word filter
     * @Description password校验器
     * @Author 艺锦欧巴 【jinyuk@foxmail.com/180484@gree.cn.com】
     * @CreateTime 2019 -03-21 10:46:52
     * @Version V 1.0
     */
    @Bean
    public PassWordFilter passWordFilter(){
        return new PassWordFilter(httpTokenExtractor,redisService,userDAO,authTokenApi);
    }

    @Bean
    public ErrorFilter errorFilter(){
        return new ErrorFilter();
    }
}
