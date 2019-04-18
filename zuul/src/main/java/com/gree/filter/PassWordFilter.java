package com.gree.filter;

import com.gree.config.HttpTokenExtractor;
import com.gree.dao.UserDAO;
import com.gree.entity.vo.User;
import com.gree.exception.KellyException;
import com.gree.feign.AuthTokenApi;
import com.gree.result.ResponseInfoEnum;
import com.gree.result.ResultBody;
import com.gree.service.RedisService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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

    private RedisService redisService;

    private UserDAO userDAO;

    private AuthTokenApi authTokenApi;



    public PassWordFilter(HttpTokenExtractor httpTokenExtractor, RedisService redisService, UserDAO userDAO, AuthTokenApi authTokenApi) {
        this.httpTokenExtractor = httpTokenExtractor;
        this.redisService = redisService;
        this.userDAO = userDAO;
        this.authTokenApi = authTokenApi;
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
        String token = httpTokenExtractor.extract(request);
        if(StringUtils.isBlank(token)) {
            Map<String, String> loginMsg = httpTokenExtractor.extractLoginMessage(request);
            Map<String, Object> tokenMap;
            String username;
            if (loginMsg != null) {
                username = loginMsg.get("username");
                String password = loginMsg.get("password");
                String client_id = loginMsg.get("client_id");
                String client_secret = loginMsg.get("client_secret");
                User user = userDAO.fetchByDSPW(username, password);
                if (user != null) {
                    logger.info("username:{},password:{},client:{},secret:{}",username,password,client_id,client_secret);
                    tokenMap = authTokenApi.getToken("password", username, password, client_id, client_secret);
                    redisService.set(username, tokenMap, 30 * 24 * 60);
                    ctx.addZuulRequestHeader("access_token",tokenMap.get("access_token").toString());
                    ctx.setSendZuulResponse(false); //不对其进行路由
                    ctx.setResponseStatusCode(200);
                    ctx.setResponseBody(ResultBody.success(tokenMap.get("access_token")));
                } else {
                    throw new KellyException(ResponseInfoEnum.NONE_USER);
                }
            } else {
                ctx.setSendZuulResponse(false); //不对其进行路由
                ctx.setResponseStatusCode(401);
                ctx.setResponseBody(ResultBody.error("请检查登录信息"));
            }
            ctx.set("isSuccess", false);
            ctx.getResponse().setContentType("application/json;charset=utf-8");
        }else {
            ctx.set("isSuccess", true);
        }
        return null;
    }
}
