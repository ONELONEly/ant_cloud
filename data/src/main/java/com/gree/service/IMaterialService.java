package com.gree.service;

import com.gree.entity.po.MaterialPO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gree.util.DatabaseType;
import com.gree.util.TargetDataSource;

import java.util.List;

/**
 * <p>
 * 物料表 服务类
 * </p>
 *
 * @author jinyu
 * @since 2019-04-22
 */
public interface IMaterialService extends IService<MaterialPO> {

    @TargetDataSource(DatabaseType.master)
    List<MaterialPO> listPaintsData ();

    @TargetDataSource(DatabaseType.master)
    List<MaterialPO> listGiftsData ();
}
