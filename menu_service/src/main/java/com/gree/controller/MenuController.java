package com.gree.controller;

import com.gree.entity.dto.MenuDto;
import com.gree.entity.po.MenuPO;
import com.gree.service.impl.MenuServiceImpl;
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

    private final MenuServiceImpl menuService;
    private final BeanUtil beanUtil = BeanUtil.createBeanUtil();

    @Autowired
    public MenuController(MenuServiceImpl menuService) {
        this.menuService = menuService;
    }

    @PostMapping("getUserMenu")
    public List<MenuDto> getUserMenu (@RequestParam("userId")String userId) {
        List<MenuPO> menuPOS = menuService.getMenuByUserId(userId);
        return beanUtil.convert(menuPOS, MenuDto.class);
    }
}
