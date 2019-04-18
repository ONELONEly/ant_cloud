package com.gree.config;

import com.gree.service.MyUserDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.annotation.Resource;

/**
 * The type Security config.
 *
 * @Description 用于保护oauth相关的endpoints，同时主要作用于用户的登录(form login,Basic auth)
 * @Author 艺锦欧巴 【jinyuk@foxmail.com/180484@gree.cn.com】
 * @CreateTime 2019 -04-11 14:36:46
 * @Version V 1.0
 */
@Configuration
@EnableWebSecurity
@Order(2)
@Import({TokenStoreConfiguration.class,SecurityHandlerConfiguration.class})
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired(required = false)
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Resource
    private LogoutHandler oauthLogoutHandler;

    private final MyUserDetailService userDetailService;

    @Autowired
    public SecurityConfig(MyUserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //配置不需要拦截的资源
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .requestMatchers()
                .antMatchers("/oauth/**")
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**")
                .authenticated()
                .and()
                .formLogin()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
                .logout()
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                .addLogoutHandler(oauthLogoutHandler)
                .clearAuthentication(true)
                .and()
                .csrf().disable()
                // 解决不允许显示在iframe的问题
                .headers().frameOptions().disable().cacheControl();

        http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(false);

        // 基于密码 等模式可以无session,不支持授权码模式
        if (authenticationEntryPoint != null) {
            http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        } else {
            // 授权码模式单独处理，需要session的支持，此模式可以支持所有oauth2的认证
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
        }
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }

    /**
     * 不定义没有password grant_type,密码模式需要AuthenticationManager支持 * * @return * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
