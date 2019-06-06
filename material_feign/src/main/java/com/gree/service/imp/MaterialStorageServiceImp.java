package com.gree.service.imp;

import com.alibaba.fastjson.JSON;
import com.gree.entity.dto.MaterialDto;
import com.gree.entity.dto.MtStorageDto;
import com.gree.entity.vo.FileVO;
import com.gree.entity.vo.MaterialVO;
import com.gree.entity.vo.request.StorageMaterialVO;
import com.gree.service.BaseServiceImp;
import com.gree.service.MaterialStorageService;
import com.gree.serviceApi.MaterialReceiveApi;
import com.gree.serviceApi.MaterialServiceApi;
import com.gree.serviceApi.MaterialStorageApi;
import com.gree.serviceApi.ResourceServiceApi;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MaterialStorageServiceImp extends BaseServiceImp implements MaterialStorageService {

    public MaterialStorageServiceImp(MaterialReceiveApi materialReceiveApi, MaterialServiceApi materialServiceApi, ResourceServiceApi resourceServiceApi, MaterialStorageApi materialStorageApi) {
        super(materialReceiveApi, materialServiceApi, resourceServiceApi, materialStorageApi);
    }

    @Override
    public Boolean insertStorageMaterial(StorageMaterialVO storageMaterialVO) {
        MtStorageDto mtStorageDto = getBeanUtil().convert(storageMaterialVO, MtStorageDto.class);
        mtStorageDto.setContact(storageMaterialVO.getTelephone());
        mtStorageDto.setStatus(0);
        List<MaterialDto> materialDtos = materialVOConvert(storageMaterialVO.getMaterialList());
        List<FileVO> fileVOS = storageMaterialVO.getPictureList();
        getMaterialStorageApi().storageMaterial(mtStorageDto);
        getLogger().info("picture:{},", JSON.toJSONString(storageMaterialVO));
        return true;
    }

    private List<MaterialDto> materialVOConvert (List<MaterialVO> materialVOS) {
        List<MaterialDto> materialDtos = new ArrayList<>();
        for(MaterialVO materialVO:materialVOS) {
            MaterialDto materialDto = new MaterialDto(materialVO.getMaterialGuid(),materialVO.getMaterialName(),
                    materialVO.getMaterialCount(),materialVO.getMaterialType());
            materialDtos.add(materialDto);
        }
        return materialDtos;
    }
}
