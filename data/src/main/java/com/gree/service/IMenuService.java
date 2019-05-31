package com.gree.service;

import com.gree.entity.po.MenuPO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author jinyu
 * @since 2019-05-29
 */
public interface IMenuService extends IService<MenuPO> {

    List<MenuPO> getMenuByUserId (String userId);
}
