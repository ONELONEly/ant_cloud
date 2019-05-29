package com.gree.service.impl;

import com.gree.entity.po.MenuPO;
import com.gree.mapper.MenuMapper;
import com.gree.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
