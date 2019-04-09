package com.gree.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Token filter.
 *
 * @Description token的校验器
 * @Author 艺锦欧巴 【jinyuk@foxmail.com/180484@gree.cn.com】
 * @CreateTime 2019 -03-21 10:32:46
 * @Version V 1.0
 */
public class TokenFilter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(TokenFilter.class);

    /**
     * @Description
     *      pre：路由之前
     *      routing：路由之时
     *      post： 路由之后
     *      error：发送错误调用
     * @Return type
     * @Author 艺锦欧巴【jinyuk@foxmall.com/180484@gree.cn.com】
     * @Date 2019/1/10 16:08
     * @Version V 1.0
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * @Description 过滤的顺序
     * @Return
     * @Author 艺锦欧巴【jinyuk@foxmall.com/180484@gree.cn.com】
     * @Date 2019/1/10 16:09
     * @Version V 1.0
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * @Description 这里可以写逻辑判断，是否要过滤，本文true,永远过滤。
     * @Return
     * @Author 艺锦欧巴【jinyuk@foxmall.com/180484@gree.cn.com】
     * @Date 2019/1/10 16:10
     * @Version V 1.0
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * @Description 过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问
     * @Return
     * @Author 艺锦欧巴【jinyuk@foxmall.com/180484@gree.cn.com】
     * @Date 2019/1/10 16:10
     * @Version V 1.0
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        Object accessToken = request.getParameter("token");
        if(accessToken == null) {
            log.warn("token is empty");
            ctx.setSendZuulResponse(false); //不对其进行路由
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("token is empty");
            ctx.set("isSuccess",false);
//            try {
//                ctx.getResponse().getWriter().write("token is empty");
//            }catch (Exception e){}
            return null;
        } else {
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess",true);
        }
        return null;
    }
}
