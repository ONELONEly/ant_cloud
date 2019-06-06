package com.gree.serviceApi;
import com.alibaba.fastjson.JSON;
import com.gree.entity.dto.MtStorageDto;
import com.gree.util.BeanUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
@RunWith(SpringRunner.class)
@SpringBootTest
public class PaintServiceApiTest {
    private final BeanUtil beanUtil = BeanUtil.createBeanUtil();
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ResourceServiceApi resourceServiceApi;

    @Autowired
    private MaterialStorageApi materialStorageApi;

    @Test
    public void getPaints()
    {
        MtStorageDto storageDto = new MtStorageDto();
        storageDto.setStatus(1);
        storageDto.setContact("181804623054");
        storageDto.setBusiness("business");
        storageDto.setReceiver("欧巴");
        storageDto.setStorageDate(LocalDateTime.now());
        storageDto.setVenderId(1);
        logger.info("debug:{}", JSON.toJSONString(materialStorageApi.storageMaterial(storageDto)));
    }

    @Test
    public void insertPaint() {
    }
}