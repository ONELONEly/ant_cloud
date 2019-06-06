package com.gree.service;

import com.gree.service.imp.MaterialServiceImp;
import com.gree.service.imp.MaterialStorageServiceImp;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class ServiceFactory {

    private final MaterialServiceImp materialService;
    private final MaterialStorageServiceImp materialStorageService;

    @Autowired
    public ServiceFactory(MaterialServiceImp materialService, MaterialStorageServiceImp materialStorageService) {
        this.materialService = materialService;
        this.materialStorageService = materialStorageService;
    }


}
