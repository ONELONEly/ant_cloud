package com.gree.serviceApi.serviceApiImp;

import com.gree.entity.dto.UserDto;
import com.gree.result.RestResponse;
import com.gree.serviceApi.UserServiceApi;
import org.springframework.stereotype.Component;

@Component
public class UserServiceApiHystric implements UserServiceApi {

    @Override
    public RestResponse<UserDto> fetchUserUsPw(String username, String password) {
        return null;
    }

    @Override
    public RestResponse<UserDto> fetchUserUsId(String userId) {
        return null;
    }
}
