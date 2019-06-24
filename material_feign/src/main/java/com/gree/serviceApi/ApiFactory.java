package com.gree.serviceApi;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ApiFactory {

    private final MaterialReceiveApi materialReceiveApi;
    private final MaterialServiceApi materialServiceApi;
    private final ResourceServiceApi resourceServiceApi;
    private final MaterialStorageApi materialStorageApi;

    @Autowired
    public ApiFactory(MaterialReceiveApi materialReceiveApi, MaterialServiceApi materialServiceApi, ResourceServiceApi resourceServiceApi, MaterialStorageApi materialStorageApi) {
        this.materialReceiveApi = materialReceiveApi;
        this.materialServiceApi = materialServiceApi;
        this.resourceServiceApi = resourceServiceApi;
        this.materialStorageApi = materialStorageApi;
    }
}
