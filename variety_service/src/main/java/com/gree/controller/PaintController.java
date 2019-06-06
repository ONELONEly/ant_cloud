package com.gree.controller;

import com.gree.entity.dto.VarietyDto;
import com.gree.entity.po.VarietyPO;
import com.gree.service.imp.VarietyServiceImpl;
import com.gree.util.BeanUtil;
import com.gree.util.UserAuthenticate;
import com.gree.util.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paint")
public class PaintController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private final VarietyServiceImpl varietyService;
    private final BeanUtil beanUtil = BeanUtil.createBeanUtil();

    @Autowired
    public PaintController(VarietyServiceImpl varietyService) {
        this.varietyService = varietyService;
    }

    @PostMapping("/getPaints")
    public List<VarietyDto> getPaints () {
        List<VarietyPO> varietyPOS = varietyService.getVarieties(1);
        return beanUtil.convert(varietyPOS,VarietyDto.class);
    }

    @PostMapping("/insertPaint")
    public Boolean insertPaint (@RequestBody VarietyDto varietyDto) {
        UserAuthenticate authenticate = UserContext.getUserMsg();
        VarietyPO  varietyPO = beanUtil.convert(varietyDto, VarietyPO.class);
        varietyPO.setVarietyType(1);
        varietyPO.setModifyUser(authenticate.getUser_name());
        varietyPO.setVersion(1);
        return varietyService.save(varietyPO);
    }
}
