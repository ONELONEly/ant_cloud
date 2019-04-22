package com.gree.service.impl;

import com.gree.entity.po.MaterialPO;
import com.gree.mapper.MaterialMapper;
import com.gree.service.IMaterialService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
