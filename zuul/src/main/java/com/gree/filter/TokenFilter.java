package com.gree.filter;

import com.alibaba.fastjson.JSON;
import com.gree.config.HttpAuthenticationManager;
import com.gree.exception.TokenExpiredException;
import com.gree.feign.AuthTokenApi;
import com.gree.result.RestErrorResponse;
import com.gree.result.RestResponse;
import com.gree.redisService.RedisService;
import com.gree.util.UserAuthenticate;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The type Token filter.
 *
 * @Description token的校验器
 * @Author 艺锦欧巴 【jinyuk@foxmail.com/180484@gree.cn.com】
 * @CreateTime 2019 -03-21 10:32:46
 * @Version V 1.0
 */

public class TokenFilter extends ZuulFilter {

    private Logger log = LoggerFactory.getLogger(TokenFilter.class);

    private HttpAuthenticationManager httpAuthenticationManager;

    public TokenFilter(HttpAuthenticationManager httpAuthenticationManager) {
        this.httpAuthenticationManager = httpAuthenticationManager;
    }

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
     * @Description 这里可以写逻辑判断，是否要过滤。
     * @Return
     * @Author 艺锦欧巴【jinyuk@foxmall.com/180484@gree.cn.com】
     * @Date 2019/1/10 16:10
     * @Version V 1.0
     */
    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return (boolean)ctx.get("isSuccess"); // 判断上一个过滤器结果为true，否则就不走下面过滤器，直接跳过后面的所有过滤器并返回 上一个过滤器不通过的结果。
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
        Map<String, Object> authenticate;
        UserAuthenticate userAuthenticate;
        String errorMessage = "";
        try {
            authenticate = httpAuthenticationManager.authenticate(request);
            userAuthenticate = getUserAuthenticate(authenticate);
            ctx.addZuulRequestHeader("x-user",JSON.toJSONString(userAuthenticate));
        } catch (TokenExpiredException e) {
            errorMessage = "请重新完成登录";
        }
        if (!StringUtils.isBlank(errorMessage)) {
            ctx.setSendZuulResponse(false); //不对其进行路由
            ctx.setResponseStatusCode(401);
            ctx.set("isSuccess", false);
            ctx.setResponseBody(new RestResponse<>().errorJson("500", errorMessage, new RestErrorResponse()));
            ctx.getResponse().setContentType("application/json;charset=utf-8");
        } else {
            ctx.set("isSuccess", true);
        }
        return null;
    }

    private UserAuthenticate getUserAuthenticate(Map<String,Object> authenticate){
        if (authenticate == null){
            return new UserAuthenticate();
        }else {
            return
                    new UserAuthenticate(authenticate.get("active").toString(), authenticate.get("exp").toString(),
                            authenticate.get("user_name").toString(), (List<String>) authenticate.get("authorities"),
                            authenticate.get("client_id").toString(), (List<String>) authenticate.get("scope"));
        }
    }
}
