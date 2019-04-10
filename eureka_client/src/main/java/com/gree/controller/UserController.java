package com.gree.controller;

import com.gree.entity.vo.User;
import com.gree.service.serviceImp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    private final UserServiceImp userServiceImp;
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public UserController(UserServiceImp userServiceImp,
                          @Qualifier("localObjectRedisTemplate") RedisTemplate<String, Object> defaultRedisTemplate) {
        this.userServiceImp = userServiceImp;
        this.redisTemplate = defaultRedisTemplate;
    }

    @RequestMapping("/welcome")
    public @ResponseBody
    User welcome(@RequestParam("usid")String usid){
//        ObjectMapper mapper = new ObjectMapper();
//        User user = userServiceImp.fetchByUSID(usid);
//        redisTemplate.opsForValue().set("root",user);
//        user = mapper.readValue(mapper.writeValueAsString(redisTemplate.opsForValue().get("root")),User.class);
        return userServiceImp.fetchByUSID(usid);
    }
}
