package com.gree.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gree.entity.po.VarietyPO;
import com.gree.mapper.VarietyMapper;
import com.gree.service.IVarietyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 种类表 服务实现类
 * </p>
 *
 * @author jinyu
 * @since 2019-04-22
 */
@Service
public class VarietyServiceImpl extends ServiceImpl<VarietyMapper, VarietyPO> implements IVarietyService {

    @Override
    public List<VarietyPO> getVarieties(Integer varietyType) {
        QueryWrapper<VarietyPO> wrapper = new QueryWrapper<>();
        if(varietyType >  0) {
            wrapper.eq("VARIETY_TYPE", varietyType);
        }
        return getBaseMapper().selectList(wrapper);
    }
}
