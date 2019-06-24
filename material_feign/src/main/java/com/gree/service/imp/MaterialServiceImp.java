package com.gree.service.imp;

import com.gree.entity.dto.MaterialDto;
import com.gree.entity.vo.MaterialVO;
import com.gree.entity.vo.MaterialVarietyVO;
import com.gree.service.BaseServiceImp;
import com.gree.service.MaterialService;
import com.gree.serviceApi.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialServiceImp extends BaseServiceImp implements MaterialService{

    @Autowired
    public MaterialServiceImp(ApiFactory apiFactory) {
        super(apiFactory);
    }

    @Override
    public List<MaterialVarietyVO> getMaterialsPaints() {
        List<MaterialDto> materialDtos = getMaterialHandle().handle(getApiFactory().getMaterialReceiveApi().getMaterialsPaints());
        return getBeanUtil().convert(materialDtos, MaterialVarietyVO.class);
    }

    @Override
    public List<MaterialVarietyVO> getMaterialGifts() {
        List<MaterialDto> materialDtos = getMaterialHandle().handle(getApiFactory().getMaterialReceiveApi().getMaterialGifts());
        return getBeanUtil().convert(materialDtos, MaterialVarietyVO.class);
    }

    @Override
    public Boolean insertMaterial(MaterialVO materialVO) {
        MaterialDto materialDto = new MaterialDto(materialVO);
        return getBooleanHandle().handle(getApiFactory().getMaterialServiceApi().insertMaterial(materialDto));
    }

    @Override
    public Boolean updateMaterial(MaterialVO materialVO) {
        MaterialDto materialDto = new MaterialDto(materialVO);
        return getBooleanHandle().handle(getApiFactory().getMaterialServiceApi().updateMaterial(materialDto));
    }

    @Override
    public List<MaterialVO> getMaterials(Integer materialType) {
        return getBeanUtil().convert(getMaterialHandle().handle(getApiFactory().getMaterialServiceApi().getMaterials(materialType)), MaterialVO.class);
    }

    @Override
    public Boolean deleteMaterial(String[] materialGuids) {
        return getBooleanHandle().handle(getApiFactory().getMaterialServiceApi().deleteMaterials(materialGuids));
    }
}
