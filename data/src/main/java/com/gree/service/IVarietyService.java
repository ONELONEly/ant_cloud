package com.gree.service;

import com.gree.entity.po.VarietyPO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 种类表 服务类
 * </p>
 *
 * @author jinyu
 * @since 2019-04-22
 */
public interface IVarietyService extends IService<VarietyPO> {

    List<VarietyPO> getVarieties(Integer varietyType);
}
