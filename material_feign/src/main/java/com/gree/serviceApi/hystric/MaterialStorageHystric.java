package com.gree.serviceApi.hystric;

import com.gree.entity.dto.MtStorageDto;
import com.gree.result.RestResponse;
import com.gree.serviceApi.MaterialStorageApi;
import org.springframework.stereotype.Component;

@Component
public class MaterialStorageHystric implements MaterialStorageApi {

    @Override
    public RestResponse<MtStorageDto> storageMaterial(MtStorageDto mtStorageDto) {
        return null;
    }
}
