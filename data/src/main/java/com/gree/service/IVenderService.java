package com.gree.service;

import com.gree.entity.po.VenderPO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gree.util.DatabaseType;
import com.gree.util.TargetDataSource;

import java.util.List;

/**
 * <p>
 * 厂家表 服务类
 * </p>
 *
 * @author jinyu
 * @since 2019-04-22
 */
public interface IVenderService extends IService<VenderPO> {

    @TargetDataSource(DatabaseType.master)
    List<VenderPO> listVenderData ();
    @TargetDataSource(DatabaseType.master)
    VenderPO fetchVenderById (Integer venderId);
}
