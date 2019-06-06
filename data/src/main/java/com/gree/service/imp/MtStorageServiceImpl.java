package com.gree.service.imp;

import com.gree.entity.po.MtStoragePO;
import com.gree.mapper.MtStorageMapper;
import com.gree.service.IMtStorageService;
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
public class MtStorageServiceImpl extends ServiceImpl<MtStorageMapper, MtStoragePO> implements IMtStorageService {
}
