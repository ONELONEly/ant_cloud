package com.gree.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.gree.entity.dto.MaterialDto;
import com.gree.entity.po.MaterialPO;
import com.gree.entity.po.VarietyPO;
import com.gree.exception.KellyException;
import com.gree.mapper.MaterialMapper;
import com.gree.result.ResponseInfoEnum;
import com.gree.service.IMaterialService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gree.util.BeanUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 物料表 服务实现类
 * </p>
 *
 * @author jinyu
 * @since 2019-04-22
 */
@Service
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, MaterialPO> implements IMaterialService {

    private final BeanUtil beanUtil = BeanUtil.createBeanUtil();

    @Override
    public List<MaterialPO> listPaintsData() {
        QueryWrapper<VarietyPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("variety_type", 0);
        return getBaseMapper().listVarietyData(queryWrapper);
    }

    @Override
    public List<MaterialPO> listGiftsData() {
        QueryWrapper<VarietyPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("variety_type", 1);
        return getBaseMapper().listVarietyData(queryWrapper);
    }

    @Override
    public Boolean updateBatchStorageMaterial(List<MaterialDto> materialDtos, String modifyUser) {
        for (MaterialDto materialDto: materialDtos) {
            MaterialPO materialPO = getById(materialDto.getMaterialGuid());
            UpdateWrapper<MaterialPO> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("material_guid",materialPO.getMaterialGuid()).eq("version",materialPO.getVersion());
            materialPO.setMtVenderCount(materialPO.getMtVenderCount() + materialDto.getMtVenderCount());
            materialPO.setModifyUser(modifyUser);
            materialPO.setVersion(materialPO.getVersion() + 1);
            update(materialPO, updateWrapper);
        }
        return true;
    }

    @Override
    public Boolean saveMaterial(MaterialDto materialDto, String modifyUser) {
        MaterialPO materialPO = beanUtil.convert(materialDto, MaterialPO.class);
        materialPO.setModifyUser(modifyUser);
        materialPO.setVersion(1);
        return save(materialPO);
    }

    @Override
    public Boolean updateMaterial(MaterialDto materialDto, String modifyUser) {
        MaterialPO materialPO = getById(materialDto.getMaterialGuid());
        if (materialPO != null) {
            UpdateWrapper<MaterialPO> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("material_guid", materialPO.getMaterialGuid()).eq("version", materialPO.getVersion());
            materialPO.setVarietyId(materialDto.getVarietyId());
            materialPO.setMaterialName(materialDto.getMaterialName());
            materialPO.setMtVenderCount(materialDto.getMtVenderCount());
            materialPO.setVersion(materialPO.getVersion() + 1);
            materialPO.setModifyUser(modifyUser);
            return update(materialPO, updateWrapper);
        }
        return false;
    }

    @Override
    public Boolean deleteByGuids(String[] guids) {
        List<String> guidList = Arrays.asList(guids);
        return removeByIds(guidList);
    }
}
