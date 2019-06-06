package com.gree.serviceApi;

import com.gree.entity.dto.VarietyDto;
import com.gree.entity.dto.VenderDto;
import com.gree.result.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "variety-service")
public interface VarietyServiceApi {

    @PostMapping(value = "/gift/getGifts")
    RestResponse<List<VarietyDto>> getGifts ();

    @PostMapping(value = "/gift/insertGift")
    RestResponse<Boolean> insertGift (@RequestBody VarietyDto varietyDto);

    @PostMapping(value = "/getVarieties")
    RestResponse<List<VarietyDto>> getVarieties ();

    @PostMapping(value = "/paint/getPaints")
    RestResponse<List<VarietyDto>> getPaints ();

    @PostMapping(value = "/paint/insertPaint")
    RestResponse<Boolean> insertPaint (@RequestBody VarietyDto varietyDto);

    @PostMapping(value = "/vender/insertVender")
    RestResponse<Boolean> insertVender (@RequestBody VenderDto venderDto);

    @PostMapping(value = "/vender/getVenders")
    RestResponse<List<VenderDto>> getVenders ();

    @PostMapping(value = "/vender/searchVender")
    RestResponse<VenderDto> searchVender (@RequestParam("venderId")Integer venderId);
}
