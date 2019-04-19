package com.gree.controller;

import com.gree.entity.po.MenuPO;
import com.gree.entity.po.UserPO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@RequestMapping("/authToken")
public interface AuthTokenApi {

    /**
     * 校验token方法
     * @param value
     * @return
     */
    @RequestMapping(value = "/oauth/check_token")
    Map<String, Object> checkToken(@RequestParam("token") String value, @RequestParam("path") String path);

    /**
     * 获取token信息
     * @param value
     * @return
     */
    @RequestMapping(value = "/oauth/getTokenInfo")
    UserPO getTokenInfo(@RequestParam("token") String value);

    /**
     * 获取菜单信息
     * @param userName
     * @param clientId
     * @return
     */
    @RequestMapping(value = "/oauth/getMenuInfo")
    List<MenuPO> getMenuInfo(@RequestParam("user_name") String userName, @RequestParam("client_id") String clientId);
}
