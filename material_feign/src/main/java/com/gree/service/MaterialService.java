package com.gree.service;

import com.gree.entity.vo.MaterialVarietyVO;

import java.util.List;

public interface MaterialService {

    List<MaterialVarietyVO> getMaterialsPaints();

    List<MaterialVarietyVO> getMaterialGifts();
}
