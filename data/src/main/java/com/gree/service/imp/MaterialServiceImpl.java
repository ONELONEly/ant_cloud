package com.gree.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gree.entity.po.MaterialPO;
import com.gree.entity.po.VarietyPO;
import com.gree.mapper.MaterialMapper;
import com.gree.service.IMaterialService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 物料表 服务实现类
 * </p>
 *
 * @author jinyu
 * @since 2019-04-22
 */
@Service
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, MaterialPO> implements IMaterialService {


    @Override
    public List<MaterialPO> listPaintsData() {
        QueryWrapper<VarietyPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("variety_type", 0);
        return getBaseMapper().listVarietyData(queryWrapper);
    }

    @Override
    public List<MaterialPO> listGiftsData() {
        QueryWrapper<VarietyPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("variety_type", 1);
        return getBaseMapper().listVarietyData(queryWrapper);
    }
}
