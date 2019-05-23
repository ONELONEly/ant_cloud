package com.gree.controller;

import com.gree.entity.po.UserPO;
import com.gree.exception.KellyException;
import com.gree.mapper.UserMapper;
import com.gree.redisService.AuthTokenApi;
import com.gree.redisService.RedisService;
import com.gree.result.HandleRestResponse;
import com.gree.result.ResponseInfoEnum;
import com.gree.util.HttpTokenExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private UserMapper userMapper;

    private final HttpTokenExtractor httpTokenExtractor;

    private final AuthTokenApi authTokenApi;

    private final RedisService redisService;

    @Autowired
    public LoginController(HttpTokenExtractor httpTokenExtractor, AuthTokenApi authTokenApi, RedisService redisService) {
        this.httpTokenExtractor = httpTokenExtractor;
        this.authTokenApi = authTokenApi;
        this.redisService = redisService;
    }

    @PostMapping("/login")
    public Map<String,Object> login (HttpServletRequest request) {
        Map<String, String> loginMsg = httpTokenExtractor.extractLoginMessage(request);
        Map tokenMap;
        Map<String,Object> resultMap = new HashMap<>();
        if (loginMsg != null) {
            String token;
            String username = loginMsg.get("username");
            String password = loginMsg.get("password");
            String client_id = loginMsg.get("client_id");
            String client_secret = "{noop}" + loginMsg.get("client_secret");
            UserPO userPO = userMapper.fetchByDSPW(username, password);
            if (userPO != null) {
                tokenMap = new HandleRestResponse<LinkedHashMap>().handle(LinkedHashMap.class, authTokenApi.getToken("password", username, password, client_id, client_secret));
                redisService.set(username, tokenMap, 30 * 24 * 60);
                token = tokenMap.get("access_token").toString();
            } else {
                throw new KellyException(ResponseInfoEnum.NONE_USER, new Date(), "KellyException");
            }
            resultMap.put("token",token);
            resultMap.put("userId",userPO.getUsid());
            return resultMap;
        } else {
            throw new KellyException(ResponseInfoEnum.EMPTY_USER_MSG, new Date(), "KellyException");
        }
    }

    @PostMapping("/getInfo")
    public Map<String,Object> getUserInfo (@RequestParam("userId") String userId) {
        return null;
    }
}
