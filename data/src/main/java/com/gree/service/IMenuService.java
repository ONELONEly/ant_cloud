package com.gree.service;

import com.gree.entity.po.MenuPO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gree.util.DatabaseType;
import com.gree.util.TargetDataSource;

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

    @TargetDataSource(DatabaseType.master)
    List<MenuPO> getMenuByUserId (String userId);
}
