package com.gree.service;

import com.gree.entity.vo.MaterialVO;
import com.gree.entity.vo.MaterialVarietyVO;

import java.util.List;

public interface MaterialService {

    List<MaterialVarietyVO> getMaterialsPaints();

    List<MaterialVarietyVO> getMaterialGifts();

    Boolean insertMaterial (MaterialVO materialVO);

    Boolean updateMaterial (MaterialVO materialVO);

    List<MaterialVO> getMaterials (Integer materialType);

    Boolean deleteMaterial (String[] materialGuids);
}
