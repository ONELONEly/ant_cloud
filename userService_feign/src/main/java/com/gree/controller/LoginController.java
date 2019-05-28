package com.gree.controller;

import com.alibaba.fastjson.JSON;
import com.gree.entity.dto.UserDto;
import com.gree.entity.po.UserPO;
import com.gree.entity.vo.UserMessageVO;
import com.gree.exception.KellyException;
import com.gree.serviceApi.AuthTokenApi;
import com.gree.redisService.RedisService;
import com.gree.result.HandleRestResponse;
import com.gree.result.ResponseInfoEnum;
import com.gree.serviceApi.UserServiceApi;
import com.gree.util.HttpTokenExtractor;
import com.gree.util.UserAuthenticate;
import com.gree.util.UserContext;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@Api(value = "/", description = "用户登陆及获取基本信息的接口")
@RequestMapping("/")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private HandleRestResponse<UserDto> userDtoHandle = new HandleRestResponse<>(UserDto.class);

    private final HttpTokenExtractor httpTokenExtractor;

    private final AuthTokenApi authTokenApi;

    private final UserServiceApi userServiceApi;

    private final RedisService redisService;

    @Autowired
    public LoginController(HttpTokenExtractor httpTokenExtractor, AuthTokenApi authTokenApi, RedisService redisService, UserServiceApi userServiceApi) {
        this.httpTokenExtractor = httpTokenExtractor;
        this.authTokenApi = authTokenApi;
        this.redisService = redisService;
        this.userServiceApi = userServiceApi;
    }

    @ApiOperation(value = "登陆接口",notes = "返回用户的ID和Token")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "client_id", value = "客户ID", dataType = "String", required = true),
            @ApiImplicitParam(paramType = "header", name = "client_secret", value = "客户密码", dataType = "String", required = true),
            @ApiImplicitParam(paramType = "query", name = "username", value = "用户名", dataType = "String", required = true),
            @ApiImplicitParam(paramType = "query", name = "password", value = "用户密码", dataType = "String", required = true)
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "${obj}",response = Map.class)
    })
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
            UserDto userDto = userDtoHandle.handle(userServiceApi.fetchUserUsPw(username, password));
            if (userDto != null) {
                tokenMap = authTokenApi.getToken("password", username, password, client_id, client_secret).getData();
                redisService.set(username, tokenMap, 30 * 24 * 60);
                token = tokenMap.get("access_token").toString();
            } else {
                throw new KellyException(ResponseInfoEnum.NONE_USER, new Date(), "KellyException");
            }
            resultMap.put("token",token);
            resultMap.put("userId",userDto.getUserId());
            return resultMap;
        } else {
            throw new KellyException(ResponseInfoEnum.EMPTY_USER_MSG, new Date(), "KellyException");
        }
    }


    @ApiOperation(value = "获得用户信息",notes = "返回用户的基础信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户Id", dataType = "String", required = true)
    })
    @PostMapping("/getInfo")
    public UserMessageVO getUserInfo (@RequestParam("userId") String userId){
        UserDto userDto = userDtoHandle.handle(userServiceApi.fetchUserUsId(userId));
        UserMessageVO userMessageVO = new UserMessageVO(userDto, new String[]{"admin","admin_master"},UserContext.getToken());
        UserAuthenticate userAuthenticate = UserContext.parseJson(UserContext.getXUser());
        return userMessageVO;
    }
}
