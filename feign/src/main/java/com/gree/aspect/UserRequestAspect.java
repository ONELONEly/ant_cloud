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
import java.util.Objects;

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
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getRequest();

        logger.info("切面开始header的录入");

        // 直接调用方，上级服务
        String directInvoker = request.getHeader(UserContext.X_DIRECT_INVOKER);
        if(StringUtils.isNotEmpty(directInvoker)){
            UserContext.putDirectInvoker(directInvoker);
        }

        // 通用用户信息
        String userInfo = request.getHeader(UserContext.X_USER);
        try {
            if (userInfo != null && !"{}".equals(userInfo)) {
                userInfo = URLDecoder.decode(userInfo, "UTF-8");//解码
            } else {
                userInfo = "";
            }
            logger.info("x-user:{}",userInfo);
        } catch (UnsupportedEncodingException e) {
            logger.info("处理通用用户信息时，发生错误");
            userInfo = "";
        }
        if(StringUtils.isNotEmpty(userInfo)){
            UserContext.putXUser(userInfo);
        }

        // 客户端版本
        String token = request.getHeader(UserContext.ACCESS_TOKEN);
        if(StringUtils.isNotEmpty(token)){
            UserContext.putToken(token);
        }
        logger.info("token:{}",token);

        logger.info("切面结束header的录入");
    }

    /**
     * 方法之后调用
     */
    @AfterReturning(pointcut = "executionService()")
    public void  doAfterReturning(){
        UserContext.remove();
        logger.info("切面移除");
    }

}