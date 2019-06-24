package com.gree.serviceApi;

import com.gree.entity.dto.FileDto;
import com.gree.entity.dto.MaterialDto;
import com.gree.result.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "material-service")
public interface MaterialServiceApi {

    @PostMapping("/insertFile")
    RestResponse<Boolean> insertFile (@RequestBody List<FileDto> fileDtos);

    @PostMapping("/insertMaterial")
    RestResponse<Boolean> insertMaterial (@RequestBody MaterialDto materialDto);

    @PostMapping("/deleteMaterials")
    RestResponse<Boolean> deleteMaterials (@RequestParam("materialGuids") String[] materialGuids);

    @PostMapping("/updateMaterial")
    RestResponse<Boolean> updateMaterial (@RequestBody MaterialDto materialDto);

    @PostMapping("/updateMaterials")
    RestResponse<Boolean> updateMaterial (@RequestBody List<MaterialDto> materialDtos);

    @PostMapping("/getMaterials")
    RestResponse<List<MaterialDto>> getMaterials (@RequestParam(value = "materialType",required = false)Integer materialType);
}
