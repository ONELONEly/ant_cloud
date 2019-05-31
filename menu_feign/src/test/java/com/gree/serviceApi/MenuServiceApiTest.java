package com.gree.serviceApi;

import com.gree.entity.dto.MenuDto;
import com.gree.result.HandleRestResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuServiceApiTest {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private HandleRestResponse<List<MenuDto>> menuDtoHandle = new HandleRestResponse<>(ArrayList.class);

    @Autowired
    private MenuServiceApi menuServiceApi;

    @Test
    public void getMenuByUserId() {
    }
}