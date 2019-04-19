package com.gree.controller;


import com.gree.feign.AuthTokenApi;
import com.gree.result.HandleRestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private AuthTokenApi authTokenApi;

    @RequestMapping("/getToken")
    @ResponseBody
    public Map getToken(@RequestParam("username")String username,
                                    @RequestParam("password")String password,@RequestParam("client_id")String clientId,
                                    @RequestParam("client_secret")String client_secret){
        return new HandleRestResponse<LinkedHashMap>().handle(LinkedHashMap.class,authTokenApi.getToken("password",username,password,clientId,client_secret));
    }

    @RequestMapping("refreshToken")
    @ResponseBody
    public Map refreshToken(@RequestParam("refresh_token")String refreshToken,@RequestParam("client_id")String clientId,
                                    @RequestParam("client_secret")String client_secret){
        return new HandleRestResponse<Map>().handle(Map.class,authTokenApi.refreshToken("refresh_token",refreshToken,clientId,client_secret));
    }
}
