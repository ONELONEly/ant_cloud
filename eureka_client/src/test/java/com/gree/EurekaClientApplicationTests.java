package com.gree;

import com.gree.dao.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EurekaClientApplicationTests {


    @Resource
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
        System.out.println(userMapper.selectById("180284"));
    }

}

