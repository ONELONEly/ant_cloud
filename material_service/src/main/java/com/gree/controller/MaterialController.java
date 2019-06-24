package com.gree.controller;


import com.gree.entity.dto.FileDto;
import com.gree.entity.dto.MaterialDto;
import com.gree.entity.po.MaterialPO;
import com.gree.entity.po.MtAccessoryPO;
import com.gree.model.MyPage;
import com.gree.service.imp.MaterialServiceImpl;
import com.gree.service.imp.MtAccessoryServiceImpl;
import com.gree.util.BeanUtil;
import com.gree.util.UserAuthenticate;
import com.gree.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/")
@RestController
public class MaterialController {

    private final MaterialServiceImpl materialService;
    private final MtAccessoryServiceImpl mtAccessoryService;

    @Autowired
    public MaterialController(MaterialServiceImpl materialService, MtAccessoryServiceImpl mtAccessoryService) {
        this.materialService = materialService;
        this.mtAccessoryService = mtAccessoryService;
    }

    @PostMapping("/insertMaterial")
    public Boolean insertMaterial (@RequestBody MaterialDto materialDto) {
        return materialService.saveMaterial(materialDto, UserContext.getUserMsg().getUser_name());
    }


    @PostMapping("/updateMaterials")
    public Boolean updateMaterial(@RequestBody List<MaterialDto> materialDtos) {
        return materialService.updateBatchStorageMaterial(materialDtos, UserContext.getUserMsg().getUser_name());
    }

    @PostMapping("/updateMaterial")
    public Boolean updateMaterial (@RequestBody MaterialDto materialDto) {
        return materialService.updateMaterial(materialDto, UserContext.getUserMsg().getUser_name());
    }

    @PostMapping("/deleteMaterials")
    public Boolean deleteMaterials (@RequestParam("materialGuids") String[] materialGuids) {
        return materialService.deleteByGuids(materialGuids);
    }

    @PostMapping("/searchMaterials")
    public List<MaterialDto> getMaterials () {
        return null;
    }

    @PostMapping("/insertFile")
    public Boolean insertFile (@RequestBody List<FileDto> fileDtos) {
        List<MtAccessoryPO> accessoryPOS = new ArrayList<>();
        for (FileDto fileDto: fileDtos){
            UserAuthenticate userAuthenticate = UserContext.getUserMsg();
            MtAccessoryPO accessoryPO = new MtAccessoryPO();
            accessoryPO.setMtGuid(fileDto.getMasterGuid());
            accessoryPO.setMtAccessoryResource(fileDto.getFileName());
            accessoryPO.setResourceSize(fileDto.getSize());
            accessoryPO.setModifyUser(userAuthenticate.getUser_name());
            accessoryPO.setVersion(1);
            accessoryPOS.add(accessoryPO);
        }
        return mtAccessoryService.saveBatch(accessoryPOS);
    }


}
