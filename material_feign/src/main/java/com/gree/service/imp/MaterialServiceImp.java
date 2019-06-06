package com.gree.service.imp;

import com.gree.entity.dto.MaterialDto;
import com.gree.entity.vo.MaterialVarietyVO;
import com.gree.service.BaseServiceImp;
import com.gree.service.MaterialService;
import com.gree.serviceApi.MaterialReceiveApi;
import com.gree.serviceApi.MaterialServiceApi;
import com.gree.serviceApi.MaterialStorageApi;
import com.gree.serviceApi.ResourceServiceApi;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialServiceImp extends BaseServiceImp implements MaterialService{

    public MaterialServiceImp(MaterialReceiveApi materialReceiveApi, MaterialServiceApi materialServiceApi, ResourceServiceApi resourceServiceApi, MaterialStorageApi materialStorageApi) {
        super(materialReceiveApi, materialServiceApi, resourceServiceApi, materialStorageApi);
    }

    @Override
    public List<MaterialVarietyVO> getMaterialsPaints() {
        List<MaterialDto> materialDtos = getMaterialHandle().handle(getMaterialReceiveApi().getMaterialsPaints());
        return getBeanUtil().convert(materialDtos, MaterialVarietyVO.class);
    }

    @Override
    public List<MaterialVarietyVO> getMaterialGifts() {
        List<MaterialDto> materialDtos = getMaterialHandle().handle(getMaterialReceiveApi().getMaterialGifts());
        return getBeanUtil().convert(materialDtos, MaterialVarietyVO.class);
    }
}
