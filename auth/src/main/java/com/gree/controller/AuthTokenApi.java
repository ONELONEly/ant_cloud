package com.gree.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@RequestMapping("/authToken")
public interface AuthTokenApi {

    /**
     * 校验token方法
     * @param value
     * @return
     */
    @RequestMapping(value = "/oauth/check_token")
    Map<String, Object> checkToken(@RequestParam("token") String value, @RequestParam("path") String path);
}
