package com.gree.impl;
import com.gree.service.impl.MenuServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuServiceImplTest {

    @Resource
    private MenuServiceImpl menuService;

    @Test
    public void getMenuByUserId() {
    }
}