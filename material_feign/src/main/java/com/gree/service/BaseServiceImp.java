package com.gree.service;

import com.gree.entity.dto.FileDto;
import com.gree.entity.dto.MaterialDto;
import com.gree.entity.dto.MtStorageDto;
import com.gree.entity.vo.FileVO;
import com.gree.result.HandleRestResponse;
import com.gree.serviceApi.ApiFactory;
import com.gree.util.BeanUtil;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BaseServiceImp {

    private final HandleRestResponse<List<MaterialDto>> materialHandle = new HandleRestResponse<>(ArrayList.class);
    private final BeanUtil beanUtil = BeanUtil.createBeanUtil();
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final HandleRestResponse<MtStorageDto> mtStorageHandle = new HandleRestResponse<>(MtStorageDto.class);
    private final HandleRestResponse<Boolean> booleanHandle = new HandleRestResponse<>(Boolean.class);

    private final ApiFactory apiFactory;

    public BaseServiceImp(ApiFactory apiFactory) {
        this.apiFactory = apiFactory;
    }

    protected List<FileDto> getFileDtos (List<FileVO> fileVOS, String masterId) {
        List<FileDto> fileDtos = new ArrayList<>();
        for (FileVO fileVO :fileVOS) {
            FileDto fileDto = getBeanUtil().convert(fileVO, FileDto.class);
            fileDto.setMasterGuid(masterId);
            fileDtos.add(fileDto);
        }
        return fileDtos;
    }
}
