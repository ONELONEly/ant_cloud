package com.gree.controller;

import com.gree.constant.FileConstant;
import com.gree.entity.dto.FileDto;
import com.gree.util.Base64Util;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequestMapping("/material")
@RestController
public class MaterialController {

    @PostMapping("/saveFile")
    public Boolean saveMaterialFile (@RequestBody List<FileDto> fileDtos) {
        boolean upload = false;
        for (FileDto fileDto: fileDtos){
            upload = Base64Util.generateFile(fileDto.getFileInput(), FileConstant.UP_MATERIAL_PATH ,fileDto.getFileName());
        }
        return upload;
    }
}
