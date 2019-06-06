package com.gree.controller;

import com.gree.entity.dto.MaterialDto;
import com.gree.entity.po.MaterialPO;
import com.gree.service.imp.MaterialServiceImpl;
import com.gree.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class MaterialController {

    private final MaterialServiceImpl materialService;
    private final BeanUtil beanUtil = BeanUtil.createBeanUtil();

    @Autowired
    public MaterialController(MaterialServiceImpl materialService) {
        this.materialService = materialService;
    }

    @PostMapping("/getMaterialsPaints")
    public List<MaterialDto> getMaterialsPaints () {
        List<MaterialPO> materialPOS = materialService.listPaintsData();
        return beanUtil.convert(materialPOS, MaterialDto.class);
    }

    @PostMapping("/getMaterialsGifts")
    public List<MaterialDto> getMaterialGifts () {
        List<MaterialPO> materialPOS = materialService.listGiftsData();
        return beanUtil.convert(materialPOS, MaterialDto.class);
    }
}
