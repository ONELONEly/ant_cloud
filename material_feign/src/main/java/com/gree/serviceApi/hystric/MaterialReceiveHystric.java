package com.gree.serviceApi.hystric;

import com.gree.entity.dto.MaterialDto;
import com.gree.result.RestResponse;
import com.gree.serviceApi.MaterialReceiveApi;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MaterialReceiveHystric implements MaterialReceiveApi {

    @Override
    public RestResponse<List<MaterialDto>> getMaterialsPaints() {
        return null;
    }

    @Override
    public RestResponse<List<MaterialDto>> getMaterialGifts() {
        return null;
    }
}
