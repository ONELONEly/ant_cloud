package com.gree.serviceApi.hystric;

import com.gree.entity.dto.MenuDto;
import com.gree.result.RestResponse;
import com.gree.serviceApi.MenuServiceApi;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MenuServiceHystric implements MenuServiceApi {

    @Override
    public RestResponse<List<MenuDto>> getMenuByUserId(String userId) {
        return null;
    }
}
