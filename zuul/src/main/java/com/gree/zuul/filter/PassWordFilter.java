package com.gree.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Pass word filter.
 *
 * @Description 密码路由
 * @Author 艺锦欧巴 【jinyuk@foxmail.com/180484@gree.cn.com】
 * @CreateTime 2019 -03-21 10:33:57
 * @Version V 1.0
 */
public class PassWordFilter extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(PassWordFilter.class);

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return (boolean)ctx.get("isSuccess"); // 判断上一个过滤器结果为true，否则就不走下面过滤器，直接跳过后面的所有过滤器并返回 上一个过滤器不通过的结果。
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        logger.info(" --- > PassWordFilter {},{}",request.getMethod(),request.getRequestURL().toString());

        String username = request.getParameter("password");
        if(!"fate".equals(username)){
            ctx.setSendZuulResponse(false); //不对其进行路由
            ctx.setResponseStatusCode(401);
            ctx.set("isSuccess",false);
            return null;
        }else {
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess",true);
        }
        return null;
    }
}
