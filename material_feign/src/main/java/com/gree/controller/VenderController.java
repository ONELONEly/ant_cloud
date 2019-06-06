package com.gree.controller;

import com.gree.entity.dto.VenderDto;
import com.gree.entity.vo.VenderVO;
import com.gree.result.HandleRestResponse;
import com.gree.serviceApi.VarietyServiceApi;
import com.gree.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/vender")
@RestController
public class VenderController {

    private final VarietyServiceApi varietyServiceApi;
    private final BeanUtil beanUtil = BeanUtil.createBeanUtil();
    private final HandleRestResponse<VenderDto> venderHandle = new HandleRestResponse<>(VenderDto.class);
    private final HandleRestResponse<List<VenderDto>> venderListHandle = new HandleRestResponse<>(ArrayList.class);
    private final HandleRestResponse<Boolean> boolHandle = new HandleRestResponse<>(Boolean.class);

    @Autowired
    public VenderController(VarietyServiceApi varietyServiceApi) {
        this.varietyServiceApi = varietyServiceApi;
    }

    @PostMapping("/insertVender")
    public Boolean insertVender (@RequestBody VenderVO venderVO) {
        VenderDto venderDto = beanUtil.convert(venderVO, VenderDto.class);
        return boolHandle.handle(varietyServiceApi.insertVender(venderDto));
    }

    @PostMapping("/getVenders")
    public List<VenderVO> getVenders () {
        List<VenderDto> venderDtos = venderListHandle.handle(varietyServiceApi.getVenders());
        return beanUtil.convert(venderDtos, VenderVO.class);
    }

    @PostMapping("/searchVender")
    public VenderVO searchVender (@RequestParam("venderId")Integer venderId) {
        VenderDto venderDto = venderHandle.handle(varietyServiceApi.searchVender(venderId));
        return beanUtil.convert(venderDto, VenderVO.class);
    }
}
