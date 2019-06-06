package com.gree.controller;

import com.gree.entity.dto.MtStorageDto;
import com.gree.entity.po.MtStoragePO;
import com.gree.service.imp.MtStorageServiceImpl;
import com.gree.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class StorageController {

    private final MtStorageServiceImpl storageService;
    private final BeanUtil beanUtil = BeanUtil.createBeanUtil();

    @Autowired
    public StorageController(MtStorageServiceImpl storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/storageMaterial")
    public MtStorageDto storageMaterial (@RequestBody MtStorageDto mtStorageDto) {
//        UserAuthenticate authenticate = UserContext.getUserMsg();
        MtStoragePO storagePO = beanUtil.convert(mtStorageDto, MtStoragePO.class);
        storagePO.setModifyUser("180340");
        storagePO.setVersion(1);
        storageService.save(storagePO);
        return beanUtil.convert(storagePO, MtStorageDto.class);
    }
}
