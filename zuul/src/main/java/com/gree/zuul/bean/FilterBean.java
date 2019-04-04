package com.gree.zuul.bean;

import com.gree.zuul.filter.PassWordFilter;
import com.gree.zuul.filter.TokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
public class FilterBean {

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
        return new TokenFilter();
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
        return new PassWordFilter();
    }
}
