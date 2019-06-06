package com.gree.serviceApi;

import com.gree.entity.dto.MaterialDto;
import com.gree.result.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "materialReceive-service")
public interface MaterialReceiveApi {


    @PostMapping("/getMaterialsPaints")
    RestResponse<List<MaterialDto>> getMaterialsPaints ();

    @PostMapping("/getMaterialsGifts")
    RestResponse<List<MaterialDto>> getMaterialGifts ();
}
