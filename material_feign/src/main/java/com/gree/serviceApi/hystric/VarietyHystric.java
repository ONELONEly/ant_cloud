package com.gree.serviceApi.hystric;

import com.gree.entity.dto.VarietyDto;
import com.gree.entity.dto.VenderDto;
import com.gree.result.RestResponse;
import com.gree.serviceApi.VarietyServiceApi;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VarietyHystric implements VarietyServiceApi {

    @Override
    public RestResponse<List<VarietyDto>> getGifts() {
        return null;
    }

    @Override
    public RestResponse<Boolean> insertGift(VarietyDto varietyDto) {
        return null;
    }

    @Override
    public RestResponse<List<VarietyDto>> getVarieties() {
        return null;
    }

    @Override
    public RestResponse<List<VarietyDto>> getPaints() {
        return null;
    }

    @Override
    public RestResponse<Boolean> insertPaint(VarietyDto varietyDto) {
        return null;
    }

    @Override
    public RestResponse<Boolean> insertVender(VenderDto venderDto) {
        return null;
    }

    @Override
    public RestResponse<List<VenderDto>> getVenders() {
        return null;
    }

    @Override
    public RestResponse<VenderDto> searchVender(Integer venderId) {
        return null;
    }
}
