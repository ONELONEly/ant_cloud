package com.gree.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.endpoint.CheckTokenEndpoint;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class AuthTokenController implements AuthTokenApi{

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CheckTokenEndpoint checkTokenEndpoint;



    @Override
    public Map<String, Object> checkToken(String value, String path) {
        Map<String, Object> authentication = (Map<String, Object>) checkTokenEndpoint.checkToken(value);
        List<String> role_id_list = (List<String>) authentication.get("authorities");
        for (String role:role_id_list){
            logger.info("role:{}",role);
        }
        if(false){
            throw new InvalidTokenException("no permission access!");
        }
        return authentication;
    }
}
