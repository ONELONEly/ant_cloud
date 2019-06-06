package com.gree.controller;

import com.gree.entity.dto.VarietyDto;
import com.gree.entity.po.VarietyPO;
import com.gree.service.imp.VarietyServiceImpl;
import com.gree.util.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/")
@RestController
public class VarietyController {


    private Logger logger = LoggerFactory.getLogger(getClass());

    private final VarietyServiceImpl varietyService;
    private final BeanUtil beanUtil = BeanUtil.createBeanUtil();

    @Autowired
    public VarietyController(VarietyServiceImpl varietyService) {
        this.varietyService = varietyService;
    }

    @PostMapping("/getVarieties")
    public List<VarietyDto> getVarieties () {
        List<VarietyPO> varietyPOS = varietyService.getVarieties(-1);
        return beanUtil.convert(varietyPOS,VarietyDto.class);
    }
}
