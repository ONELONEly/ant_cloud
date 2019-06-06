package com.gree.controller;

import com.gree.entity.dto.VarietyDto;
import com.gree.entity.vo.VarietyVO;
import com.gree.result.HandleRestResponse;
import com.gree.serviceApi.VarietyServiceApi;
import com.gree.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/variety")
@RestController
public class VarietyController {

    private final BeanUtil beanUtil = BeanUtil.createBeanUtil();
    private final VarietyServiceApi varietyServiceApi;
    private final HandleRestResponse<List<VarietyDto>> varietyHandle = new HandleRestResponse<>(ArrayList.class);
    private final HandleRestResponse<Boolean> booleanHandle = new HandleRestResponse<>(Boolean.class);

    @Autowired
    public VarietyController(VarietyServiceApi varietyServiceApi) {
        this.varietyServiceApi = varietyServiceApi;
    }

    @PostMapping("/getVarieties")
    public List<VarietyVO> getVarieties () {
        List<VarietyDto> varietyDtos = varietyHandle.handle(varietyServiceApi.getVarieties());
        return beanUtil.convert(varietyDtos, VarietyVO.class);
    }

    @PostMapping("/paint/getPaints")
    public List<VarietyVO> getPaints () {
        List<VarietyDto> varietyDtos = varietyHandle.handle(varietyServiceApi.getPaints());
        return beanUtil.convert(varietyDtos, VarietyVO.class);
    }

    @PostMapping("/paint/insertPaint")
    public Boolean insertPaint (@RequestBody VarietyDto varietyDto) {
        return booleanHandle.handle(varietyServiceApi.insertPaint(varietyDto));
    }

    @PostMapping("/gift/getGifts")
    public List<VarietyVO> getGifts () {
        List<VarietyDto> varietyDtos = varietyHandle.handle(varietyServiceApi.getGifts());
        return beanUtil.convert(varietyDtos, VarietyVO.class);
    }

    @PostMapping("/gift/insertGift")
    public Boolean insertGift (@RequestBody VarietyDto varietyDto) {
        return booleanHandle.handle(varietyServiceApi.insertGift(varietyDto));
    }
}
