package com.gree.serviceApi;

import com.gree.entity.dto.UserDto;
import com.gree.result.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "user-service")
public interface UserServiceApi {

    @PostMapping("/fetchUserDsPw")
    RestResponse<UserDto> fetchUserUsPw(@RequestParam("username")String username, @RequestParam("password")String password);

    @PostMapping("/fetchUserUsId")
    RestResponse<UserDto> fetchUserUsId(@RequestParam("userId")String userId);

}
