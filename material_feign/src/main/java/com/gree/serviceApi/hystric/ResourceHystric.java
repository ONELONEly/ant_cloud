package com.gree.serviceApi.hystric;

import com.gree.entity.dto.FileDto;
import com.gree.result.RestResponse;
import com.gree.serviceApi.ResourceServiceApi;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResourceHystric implements ResourceServiceApi {

    @Override
    public RestResponse<Boolean> saveStorageFile(List<FileDto> fileDtos) {
        return null;
    }

    @Override
    public RestResponse<Boolean> saveMaterialFile(List<FileDto> fileDtos) {
        return null;
    }
}
