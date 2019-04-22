package com.gree.service.impl;

import com.gree.entity.po.StoragePO;
import com.gree.mapper.StorageMapper;
import com.gree.service.IStorageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 入库主表 服务实现类
 * </p>
 *
 * @author jinyu
 * @since 2019-04-22
 */
@Service
public class StorageServiceImpl extends ServiceImpl<StorageMapper, StoragePO> implements IStorageService {

}
