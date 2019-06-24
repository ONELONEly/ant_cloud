package com.gree.service.imp;

import com.gree.entity.dto.FileDto;
import com.gree.entity.dto.MaterialDto;
import com.gree.entity.dto.MtStorageDto;
import com.gree.entity.vo.FileVO;
import com.gree.entity.vo.MaterialVO;
import com.gree.entity.vo.request.StorageMaterialVO;
import com.gree.service.BaseServiceImp;
import com.gree.service.MaterialStorageService;
import com.gree.serviceApi.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MaterialStorageServiceImp extends BaseServiceImp implements MaterialStorageService {

    @Autowired
    public MaterialStorageServiceImp(ApiFactory apiFactory) {
        super(apiFactory);
    }

    @Override
    public Boolean insertStorageMaterial(StorageMaterialVO storageMaterialVO) {
        /*插入入库记录 start*/
        MtStorageDto mtStorageDto = getBeanUtil().convert(storageMaterialVO, MtStorageDto.class);
        mtStorageDto.setContact(storageMaterialVO.getTelephone());
        mtStorageDto.setStatus(0);
        mtStorageDto = getMtStorageHandle().handle(getApiFactory().getMaterialStorageApi().storageMaterial(mtStorageDto));
        /*获得用户资源存储的数组 start*/
        List<FileVO> fileVOS = storageMaterialVO.getPictureList();
        List<FileDto> fileDtos = getFileDtos(fileVOS, mtStorageDto.getStorageGuid());
        /*插入入库记录附件*/
        getBooleanHandle().handle(getApiFactory().getResourceServiceApi().saveStorageFile(fileDtos));
        /*end*/
        /*end*/
        /*end*/
        /*入库物料操作*/
        List<MaterialDto> materialDtos = materialVOConvert(storageMaterialVO.getMaterialList(), fileVOS);
        getBooleanHandle().handle(getApiFactory().getMaterialServiceApi().updateMaterial(materialDtos));

        return true;
    }

    private List<MaterialDto> materialVOConvert (List<MaterialVO> materialVOS, List<FileVO> fileVOS) {
        List<MaterialDto> materialDtos = new ArrayList<>();
        for(MaterialVO materialVO:materialVOS) {
            MaterialDto materialDto = new MaterialDto(materialVO.getMaterialGuid(),materialVO.getMaterialName(),
                    materialVO.getMaterialCount(),materialVO.getMaterialType());
            /*进行物料的附件添加*/
            List<FileDto> fileDtos = getFileDtos(fileVOS, materialVO.getMaterialGuid());
            getBooleanHandle().handle(getApiFactory().getResourceServiceApi().saveMaterialFile(fileDtos));
            getBooleanHandle().handle(getApiFactory().getMaterialServiceApi().insertFile(fileDtos));
            /*end*/
            materialDtos.add(materialDto);
        }
        return materialDtos;
    }
}
