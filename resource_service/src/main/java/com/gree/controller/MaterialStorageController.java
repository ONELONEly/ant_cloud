package com.gree.controller;

import com.gree.constant.FileConstant;
import com.gree.entity.dto.FileDto;
import com.gree.util.Base64Util;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequestMapping("/storage")
@RestController
public class MaterialStorageController {

    @PostMapping("/saveFile")
    public Boolean saveStorageFile (@RequestBody List<FileDto> fileDtos) {
        boolean upload = true;
        for (FileDto fileDto: fileDtos){
            upload = Base64Util.generateFile(fileDto.getFileInput(), FileConstant.UP_MT_STORAGE_PATH ,fileDto.getFileName());
        }
        return upload;
    }
}
