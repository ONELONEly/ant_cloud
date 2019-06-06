package com.gree.service;

import com.gree.entity.dto.MaterialDto;
import com.gree.result.HandleRestResponse;
import com.gree.serviceApi.MaterialReceiveApi;
import com.gree.serviceApi.MaterialServiceApi;
import com.gree.serviceApi.MaterialStorageApi;
import com.gree.serviceApi.ResourceServiceApi;
import com.gree.util.BeanUtil;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BaseServiceImp {

    private final HandleRestResponse<List<MaterialDto>> materialHandle = new HandleRestResponse<>(ArrayList.class);
    private final BeanUtil beanUtil = BeanUtil.createBeanUtil();
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final MaterialReceiveApi materialReceiveApi;
    private final MaterialServiceApi materialServiceApi;
    private final ResourceServiceApi resourceServiceApi;
    private final MaterialStorageApi materialStorageApi;

    @Autowired
    public BaseServiceImp(MaterialReceiveApi materialReceiveApi, MaterialServiceApi materialServiceApi, ResourceServiceApi resourceServiceApi, MaterialStorageApi materialStorageApi) {
        this.materialReceiveApi = materialReceiveApi;
        this.materialServiceApi = materialServiceApi;
        this.resourceServiceApi = resourceServiceApi;
        this.materialStorageApi = materialStorageApi;
    }
}
