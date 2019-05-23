package com.gree.controller;

import com.alibaba.fastjson.JSON;
import com.gree.entity.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("/")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping("/hi")
    public String sayHi(@RequestBody UserVO userVO){
        logger.error("userMessage:{}", JSON.toJSONString(userVO));
        LinkedHashMap map = new LinkedHashMap();
        return "Hello girl,I'm handsome";
    }
}
