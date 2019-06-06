package com.gree.serviceApi;

import com.gree.entity.dto.MtStorageDto;
import com.gree.result.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "materialStorage-service")
public interface MaterialStorageApi {

    @PostMapping("/storageMaterial")
    RestResponse<MtStorageDto> storageMaterial (@RequestBody MtStorageDto mtStorageDto);
}
