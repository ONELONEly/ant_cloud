package com.gree.serviceApi.hystric;

import com.gree.entity.dto.FileDto;
import com.gree.entity.dto.MaterialDto;
import com.gree.result.RestResponse;
import com.gree.serviceApi.MaterialServiceApi;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MaterialHystric implements MaterialServiceApi {

    @Override
    public RestResponse<Boolean> updateMaterial(List<MaterialDto> materialDtos) {
        return null;
    }

    @Override
    public RestResponse<Boolean> deleteMaterials(String[] materialGuids) {
        return null;
    }

    @Override
    public RestResponse<Boolean> insertFile(List<FileDto> fileDtos) {
        return null;
    }

    @Override
    public RestResponse<Boolean> insertMaterial(MaterialDto materialDto) {
        return null;
    }

    @Override
    public RestResponse<Boolean> updateMaterial(MaterialDto materialDto) {
        return null;
    }

    @Override
    public RestResponse<List<MaterialDto>> getMaterials(Integer materialType) {
        return null;
    }
}
