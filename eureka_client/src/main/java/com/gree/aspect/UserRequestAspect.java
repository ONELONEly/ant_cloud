package com.gree.aspect;

import com.gree.util.UserContext;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author fei
 */
@Aspect
@Order(85)
@Component
public class UserRequestAspect {
    private static Logger logger = LoggerFactory.getLogger(UserRequestAspect.class);

    @Pointcut("(@target(org.springframework.web.bind.annotation.RestController)) && (execution(public * com.gree..*.*(..)))")
    public void executionService() {
    }

    /**
     * 方法调用之前调用
     */
    @Before("executionService()")
    public void doBefore(){
        UserContext.init();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();

        // 系统码
        String platform = request.getHeader("x-platform");
        if(StringUtils.isNotEmpty(platform)){
            UserContext.putPlatform(platform);
        }

        // 直接调用方，上级服务
        String directInvoker = request.getHeader("x-direct-invoker");
        if(StringUtils.isNotEmpty(directInvoker)){
            UserContext.putDirectInvoker(directInvoker);
        }

        // 通用用户信息
        String userInfo = request.getHeader("x-user");
        try {
            if (userInfo != null) {
                userInfo = URLDecoder.decode(userInfo, "UTF-8");//解码
            }
        } catch (UnsupportedEncodingException e) {
            logger.info("处理通用用户信息时，发生错误");
            userInfo = "{}";
        }
        if(StringUtils.isNotEmpty(userInfo)){
            UserContext.putXUser(userInfo, platform);
        }

        // 客户端版本
        String version = request.getHeader("x-version");
        if(StringUtils.isNotEmpty(version)){
            UserContext.putVersion(version);
        }
    }

    /**
     * 方法之后调用
     */
    @AfterReturning(pointcut = "executionService()")
    public void  doAfterReturning(){
        UserContext.remove();
    }

}