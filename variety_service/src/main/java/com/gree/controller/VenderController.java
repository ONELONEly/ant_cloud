package com.gree.controller;

import com.gree.entity.dto.VenderDto;
import com.gree.entity.po.VenderPO;
import com.gree.service.imp.VenderServiceImpl;
import com.gree.util.BeanUtil;
import com.gree.util.UserAuthenticate;
import com.gree.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/vender")
@RestController
public class VenderController {



    private final VenderServiceImpl venderService;
    private final BeanUtil beanUtil = BeanUtil.createBeanUtil();

    @Autowired
    public VenderController(VenderServiceImpl venderService) {
        this.venderService = venderService;
    }

    @PostMapping("/insertVender")
    public Boolean insertVender (@RequestBody VenderDto venderDto) {
        UserAuthenticate authenticate = UserContext.getUserMsg();
        VenderPO venderPO = beanUtil.convert(venderDto, VenderPO.class);
        venderPO.setModifyUser(authenticate.getUser_name());
        venderPO.setVersion(1);
        return venderService.save(venderPO);
    }

    @PostMapping("/getVenders")
    public List<VenderDto> getVenders () {
        List<VenderPO> venderPOS = venderService.listVenderData();
        return beanUtil.convert(venderPOS, VenderDto.class);
    }

    @PostMapping("/searchVender")
    public VenderDto searchVender (@RequestParam("venderId")Integer venderId) {
        VenderPO venderPO = venderService.fetchVenderById(venderId);
        return beanUtil.convert(venderPO, VenderDto.class);
    }
}
