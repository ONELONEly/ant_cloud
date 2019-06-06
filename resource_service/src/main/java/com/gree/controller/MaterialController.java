package com.gree.controller;

import com.gree.entity.dto.FileDto;
import com.gree.entity.po.MtAccessoryPO;
import com.gree.service.imp.MtAccessoryServiceImpl;
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

@RequestMapping("/material")
@RestController
public class MaterialController {

    private final MtAccessoryServiceImpl accessoryService;

    @Autowired
    public MaterialController(MtAccessoryServiceImpl accessoryService) {
        this.accessoryService = accessoryService;
    }

    @PostMapping("/saveFile")
    public Boolean saveMaterialFile (@RequestBody List<FileDto> fileDtos) {
        List<MtAccessoryPO> accessoryPOS = new ArrayList<>();
        for (FileDto fileDto: fileDtos){
            boolean upload = Base64Util.generateFile(fileDto.getFileInput(), ConstUtil.UP_MATERIAL_PATH + fileDto.getFileName());
            if(upload) {
                UserAuthenticate userAuthenticate = UserContext.getUserMsg();
                MtAccessoryPO accessoryPO = new MtAccessoryPO();
                accessoryPO.setMtGuid(fileDto.getMasterGuid());
                accessoryPO.setMtAccessoryResource(fileDto.getFileName());
                accessoryPO.setResourceSize(fileDto.getSize());
                accessoryPO.setModifyUser(userAuthenticate.getUser_name());
                accessoryPO.setVersion(1);
                accessoryPOS.add(accessoryPO);
            }
        }
        return accessoryService.saveBatch(accessoryPOS);
    }
}
