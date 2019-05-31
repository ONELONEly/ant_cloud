package com.gree.serviceApi;

import com.gree.entity.dto.MenuDto;
import com.gree.result.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(value = "menu-service",path = "/basic")
public interface MenuServiceApi {

    @PostMapping("/getUserMenu")
    RestResponse<List<MenuDto>> getMenuByUserId (@RequestParam("userId")String userId);
}
