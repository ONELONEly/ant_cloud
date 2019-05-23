package com.gree;

import com.gree.service.impl.MaterialServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EurekaClientApplicationTests {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Resource
    private MaterialServiceImpl materialService;

    @Test
    public void contextLoads() {
        logger.info(new BCryptPasswordEncoder().encode("1ssss"));
//        MyPage<UserPO> pager = new MyPage<>(1,10);
//        pager.setSelectInt(180484);
//        pager.setSelectString("180284");
//        UserPO userPO = userService.queryAllByPage(pager).getRecords().get(0);
//        logger.debug("{}", JSON.toJSONString(userPO));
//        userService.updateById(userPO);
//        UserPO update = userService.queryAllByPage(pager).getRecords().get(0);
//        System.out.println(update.getVersion().intValue());
//        MaterialPO material = new MaterialPO("锦遇",100,1,1,"180484");
//        material.setMaterialGuid("12345666756767");
//        materialService.save(material);
    }

}

