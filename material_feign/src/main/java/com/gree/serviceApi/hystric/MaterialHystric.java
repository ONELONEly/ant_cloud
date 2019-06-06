package com.gree.serviceApi.hystric;

import com.gree.entity.dto.MaterialDto;
import com.gree.result.RestResponse;
import com.gree.serviceApi.MaterialServiceApi;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MaterialHystric implements MaterialServiceApi {

    @Override
    public RestResponse<Boolean> insertMaterial(List<MaterialDto> materialDtos) {
        return null;
    }
}
