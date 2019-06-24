package com.gree.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gree.entity.vo.MaterialVO;
import com.gree.entity.vo.MaterialVarietyVO;
import com.gree.entity.vo.request.StorageMaterialVO;
import com.gree.service.ServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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

    @PostMapping("/insertMaterial")
    public Boolean insertMaterial (@RequestBody @Validated MaterialVO materialVO) {
        return serviceFactory.getMaterialService().insertMaterial(materialVO);
    }

    @PostMapping("/deleteMaterials")
    public Boolean deleteMaterial (@RequestParam("materialGuids")String[] materialGuids) {
        return serviceFactory.getMaterialService().deleteMaterial(materialGuids);
    }

    @PostMapping("/updateMaterial")
    public Boolean updateMaterial (@RequestBody @Validated MaterialVO materialVO) {
        return serviceFactory.getMaterialService().updateMaterial(materialVO);
    }

    @PostMapping("/getMaterials")
    public List<MaterialVO> getMaterials (@RequestParam(value = "materialType", required = false)Integer materialType) {
        return serviceFactory.getMaterialService().getMaterials(materialType);
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
    public Boolean insertStorageMaterial (@RequestBody @Validated StorageMaterialVO storageMaterialVO) {
        return serviceFactory.getMaterialStorageService().insertStorageMaterial(storageMaterialVO);
    }
}
