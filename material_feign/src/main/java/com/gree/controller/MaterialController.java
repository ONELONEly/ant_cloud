package com.gree.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gree.entity.vo.MaterialVarietyVO;
import com.gree.entity.vo.request.StorageMaterialVO;
import com.gree.service.ServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/")
public class MaterialController {

    private final ServiceFactory serviceFactory;

    @Autowired
    public MaterialController(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    @PostMapping("/getMaterialsPaints")
    public List<MaterialVarietyVO> getMaterialsPaints () {
        return serviceFactory.getMaterialService().getMaterialsPaints();
    }

    @PostMapping("/getMaterialsGifts")
    public List<MaterialVarietyVO> getMaterialGifts () {
        return serviceFactory.getMaterialService().getMaterialGifts();
    }

    @PostMapping("/insertStorageMaterial")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Boolean insertStorageMaterial (@RequestBody StorageMaterialVO storageMaterialVO) {
        return serviceFactory.getMaterialStorageService().insertStorageMaterial(storageMaterialVO);
    }
}
