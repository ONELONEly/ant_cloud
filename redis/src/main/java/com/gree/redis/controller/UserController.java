package com.gree.redis.controller;

import com.gree.redis.entity.User;
import com.gree.redis.service.serviceImp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    private final UserServiceImp userServiceImp;

    @Autowired
    public UserController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @RequestMapping("/welcome")
    public @ResponseBody
    User welcome(){
        return userServiceImp.fetchByUSID("180484");
    }
}
