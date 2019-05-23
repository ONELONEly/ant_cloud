package com.gree.feign;

import com.gree.result.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
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
    <T> RestResponse<T> checkToken(@RequestParam("token") String value, @RequestParam("path") String path);
}