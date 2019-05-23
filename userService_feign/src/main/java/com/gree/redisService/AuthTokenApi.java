package com.gree.redisService;

import com.gree.result.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "gree-auth")
public interface AuthTokenApi {

    /**
     * 校验token方法
     * @param value
     * @return
     */
    @RequestMapping(value = "/authToken/oauth/check_token")
    <T>RestResponse<T> checkToken(@RequestParam("token") String value, @RequestParam("path") String path);

    @PostMapping(value = "/oauth/token")
    <T>RestResponse<T> getToken(@RequestParam("grant_type")String grant_type,@RequestParam("username")String username,
                                @RequestParam("password")String password,@RequestParam("client_id")String clientId,
                                @RequestParam("client_secret")String client_secret);


    @PostMapping(value = "/oauth/token")
    <T>RestResponse<T> refreshToken(@RequestParam("grant_type")String grant_type,@RequestParam("refresh_token")String refreshToken,
                                    @RequestParam("client_id")String clientId,@RequestParam("client_secret")String client_secret);

}