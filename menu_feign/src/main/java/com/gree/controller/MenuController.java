package com.gree.controller;

import com.gree.entity.dto.MenuDto;
import com.gree.entity.vo.MenuVO;
import com.gree.result.HandleRestResponse;
import com.gree.serviceApi.MenuServiceApi;
import com.gree.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/basic")
public class MenuController {

    private final MenuServiceApi menuServiceApi;
    private final HandleRestResponse<List<MenuDto>>  menuDtoHandle = new HandleRestResponse<>(ArrayList.class);
    private final BeanUtil beanUtil = BeanUtil.createBeanUtil();

    @Autowired
    public MenuController(MenuServiceApi menuServiceApi) {
        this.menuServiceApi = menuServiceApi;
    }

    @PostMapping("/getMenus")
    public List<MenuVO> getMenus (@RequestParam("userId")String userId) {
        List<MenuDto> menuDtoList = menuDtoHandle.handle(menuServiceApi.getMenuByUserId(userId));
        return beanUtil.convert(menuDtoList, MenuVO.class);
    }
}
