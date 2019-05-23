package com.gree.filter;

import com.gree.config.HttpAuthenticationManager;
import com.gree.result.*;
import com.gree.util.HttpTokenExtractor;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

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

    private HttpTokenExtractor httpTokenExtractor;

    private HttpAuthenticationManager httpAuthenticationManager;



    public PassWordFilter(HttpAuthenticationManager httpAuthenticationManager,HttpTokenExtractor httpTokenExtractor) {
        this.httpTokenExtractor = httpTokenExtractor;
        this.httpAuthenticationManager = httpAuthenticationManager;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return -1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String url = request.getRequestURI();
        String token = httpTokenExtractor.extract(request);
        logger.info("url:{}",url);
        if(StringUtils.isBlank(token)) {
            if(httpAuthenticationManager.isIgnoreUrl(url)){
                ctx.set("isSuccess", true);
            } else {
                ctx.set("isSuccess", false);
                ctx.setSendZuulResponse(false); //不对其进行路由
                ctx.setResponseStatusCode(401);
                ctx.setResponseBody(new RestResponse<>().errorJson(ResponseInfoEnum.EMPTY_USER_MSG,new RestErrorResponse()));
                ctx.getResponse().setContentType("application/json;charset=utf-8");
            }
        }else {
            ctx.set("isSuccess", true);
        }
        return null;
    }
}
