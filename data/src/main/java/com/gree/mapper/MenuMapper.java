package com.gree.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gree.entity.po.MenuPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author jinyu
 * @since 2019-05-29
 */
public interface MenuMapper extends BaseMapper<MenuPO> {

    List<MenuPO> getMenuByUserId (@Param("ew")Wrapper<MenuPO> queryWrapper);
}
