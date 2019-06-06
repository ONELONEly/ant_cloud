package com.gree.controller;


import com.gree.entity.dto.MaterialDto;
import com.gree.entity.po.MaterialPO;
import com.gree.service.imp.MaterialServiceImpl;
import com.gree.util.BeanUtil;
import com.gree.util.UserAuthenticate;
import com.gree.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/")
@RestController
public class MaterialController {

    private final MaterialServiceImpl materialService;
    private final BeanUtil beanUtil = BeanUtil.createBeanUtil();

    @Autowired
    public MaterialController(MaterialServiceImpl materialService) {
        this.materialService = materialService;
    }

    @PostMapping("/insertMaterial")
    public Boolean insertMaterial (@RequestBody List<MaterialDto> materialDtos) {
        UserAuthenticate authenticate = UserContext.getUserMsg();
        List<MaterialPO> materialPOList = new ArrayList<>();
        for (MaterialDto materialDto: materialDtos) {
            MaterialPO materialPO = beanUtil.convert(materialDto, MaterialPO.class);
            materialPO.setModifyUser(authenticate.getUser_name());
            materialPO.setVersion(1);
            materialPOList.add(materialPO);
        }
        return materialService.saveBatch(materialPOList);
    }
}
