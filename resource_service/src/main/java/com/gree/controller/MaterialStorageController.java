package com.gree.controller;

import com.gree.entity.dto.FileDto;
import com.gree.entity.po.MtStAccessoryPO;
import com.gree.service.imp.MtStAccessoryServiceImpl;
import com.gree.util.Base64Util;
import com.gree.util.ConstUtil;
import com.gree.util.UserAuthenticate;
import com.gree.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/storage")
@RestController
public class MaterialStorageController {

    private final MtStAccessoryServiceImpl accessoryService;

    @Autowired
    public MaterialStorageController(MtStAccessoryServiceImpl accessoryService) {
        this.accessoryService = accessoryService;
    }

    @PostMapping("/saveFile")
    public Boolean saveStorageFile (@RequestBody List<FileDto> fileDtos) {
        List<MtStAccessoryPO> accessoryPOS = new ArrayList<>();
        for (FileDto fileDto: fileDtos){
            boolean upload = Base64Util.generateFile(fileDto.getFileInput(), ConstUtil.UP_MT_STORAGE_PATH + fileDto.getFileName());
            if(upload) {
                UserAuthenticate userAuthenticate = UserContext.getUserMsg();
                MtStAccessoryPO accessoryPO = new MtStAccessoryPO();
                accessoryPO.setStorageGuid(fileDto.getMasterGuid());
                accessoryPO.setStAccessoryResource(fileDto.getFileName());
                accessoryPO.setResourceSize(fileDto.getSize());
                accessoryPO.setModifyUser(userAuthenticate.getUser_name());
                accessoryPO.setVersion(1);
                accessoryPOS.add(accessoryPO);
            }
        }
        return accessoryService.saveBatch(accessoryPOS);
    }
}
