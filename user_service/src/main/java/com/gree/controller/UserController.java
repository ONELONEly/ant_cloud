package com.gree.controller;

import com.gree.entity.dto.UserDto;
import com.gree.entity.po.UserPO;
import com.gree.redisService.serviceImp.UserServiceImp;
import com.gree.util.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private final UserServiceImp userServiceImp;
    private final BeanUtil beanUtil = BeanUtil.createBeanUtil();

    @Autowired
    public UserController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @PostMapping("/fetchUserDsPw")
    public UserDto fetchUserUSPW(@RequestParam("username")String username, @RequestParam("password")String password){
        UserDto userDto;
        UserPO userPO = userServiceImp.fetchByDSPW(username,password);
        userDto = userPO == null ? new UserDto():new UserDto(userPO);
        return userDto;
    }

    @PostMapping("/fetchUserUsId")
    public UserDto fetchUserUsId(@RequestParam("userId")String userId){
        UserDto userDto;
        UserPO userPO = userServiceImp.fetchByUSID(userId);
        userDto = userPO == null ? new UserDto():new UserDto(userPO);
        return userDto;
    }
}
