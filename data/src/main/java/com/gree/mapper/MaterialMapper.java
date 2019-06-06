package com.gree.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.gree.entity.po.MaterialPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * <p>
 * 物料表 Mapper 接口
 * </p>
 *
 * @author jinyu
 * @since 2019-04-22
 */
public interface MaterialMapper extends BaseMapper<MaterialPO> {

    List<MaterialPO> listVarietyData (@Param("ew") Wrapper wrapper);
}
