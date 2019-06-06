package com.gree.serviceApi;

import com.gree.entity.dto.MaterialDto;
import com.gree.result.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "material-service")
public interface MaterialServiceApi {

    @PostMapping("/insertMaterial")
    RestResponse<Boolean> insertMaterial (@RequestBody List<MaterialDto> materialDtos);

}
