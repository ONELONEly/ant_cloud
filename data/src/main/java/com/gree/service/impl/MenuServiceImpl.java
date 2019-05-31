package com.gree.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gree.entity.po.MenuPO;
import com.gree.mapper.MenuMapper;
import com.gree.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author jinyu
 * @since 2019-05-29
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuPO> implements IMenuService {

    @Override
    public List<MenuPO> getMenuByUserId(String userId) {

        QueryWrapper<MenuPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_Id",userId);
        return getBaseMapper().getMenuByUserId(queryWrapper);
    }
}
