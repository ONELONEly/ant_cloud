package com.gree.aspect;

import com.gree.util.DatabaseContextHolder;
import com.gree.util.DatabaseType;
import com.gree.util.TargetDataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class DatasourceAspect {

    private final static Logger log = LoggerFactory.getLogger(DatasourceAspect.class);

    //切换放在mapper接口的方法上，所以这里要配置AOP切面的切入点
//    @Pointcut("execution( * com.gree.redis.dao..*(..)) || execution( * com.gree.redis.controller.*.*(..))")
    @Pointcut("execution( * com.gree.dao..*.*(..))")
    public void dataSourcePointCut() {
    }

    @Before("dataSourcePointCut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("I'm in");
        log.debug("I'm in");
        Object target = joinPoint.getTarget();
        String method = joinPoint.getSignature().getName();
        Class<?>[] clazz = target.getClass().getInterfaces();
        Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
        try {
            Method m = clazz[0].getMethod(method, parameterTypes);
            //如果方法上存在切换数据源的注解，则根据注解内容进行数据源切换
            if (m != null && m.isAnnotationPresent(TargetDataSource.class)) {
                TargetDataSource data = m.getAnnotation(TargetDataSource.class);
                DatabaseType dataSourceName = data.value();
                DatabaseContextHolder.setDatabaseType(dataSourceName);
                log.debug("current thread {} add {} to ThreadLocal",Thread.currentThread().getName(),dataSourceName);
            } else {
                log.debug("switch datasource fail,use default");
            }
        } catch (Exception e) {
            log.error("current thread {} add data to ThreadLocal error {}", Thread.currentThread().getName(),e);
        }
    }

    //执行完切面后，将线程共享中的数据源名称清空
    @After("dataSourcePointCut()")
    public void after(JoinPoint joinPoint) {
        DatabaseContextHolder.removeDatabase();
    }
}
