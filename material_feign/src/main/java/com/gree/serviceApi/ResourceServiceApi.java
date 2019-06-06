package com.gree.serviceApi;

import com.gree.entity.dto.FileDto;
import com.gree.result.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "resource-service")
public interface ResourceServiceApi {


    @PostMapping("/storage/saveFile")
    RestResponse<Boolean> saveStorageFile (@RequestBody List<FileDto> fileDtos);

    @PostMapping("/material/saveFile")
    RestResponse<Boolean> saveMaterialFile (@RequestBody List<FileDto> fileDtos);
}
