package com.gree.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gree.entity.po.VenderPO;
import com.gree.mapper.VenderMapper;
import com.gree.service.IVenderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 厂家表 服务实现类
 * </p>
 *
 * @author jinyu
 * @since 2019-04-22
 */
@Service
public class VenderServiceImpl extends ServiceImpl<VenderMapper, VenderPO> implements IVenderService {

    @Override
    public List<VenderPO> listVenderData() {
        QueryWrapper<VenderPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("vender_name");
        return getBaseMapper().selectList(queryWrapper);
    }

    @Override
    public VenderPO fetchVenderById(Integer venderId) {
        QueryWrapper<VenderPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("vender_id",venderId);
        return getBaseMapper().selectOne(queryWrapper);
    }
}
