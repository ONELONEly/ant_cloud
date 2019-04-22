package com.gree;

import com.alibaba.fastjson.JSON;
import com.gree.entity.po.UserPO;
import com.gree.model.MyPage;
import com.gree.redisService.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EurekaClientApplicationTests {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Resource
    private UserService userService;

    @Test
    public void contextLoads() {
        MyPage<UserPO> pager = new MyPage<>(1,10);
        pager.setSelectInt(180484);
        pager.setSelectString("180284");
        UserPO userPO = userService.queryAllByPage(pager).getRecords().get(0);
        logger.debug("{}", JSON.toJSONString(userPO));
        userService.updateById(userPO);
        UserPO update = userService.queryAllByPage(pager).getRecords().get(0);
        System.out.println(update.getVersion().intValue());
    }

}

