package com.gree.entity.vo;

import com.gree.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
 * @version 1.0
 * @description 物料实体
 * @createTime 2019 -06-04 17:06:57
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class MaterialVO extends BaseEntity {
    private static final long serialVersionUID = 3198308930828249967L;

    /**
     * 物料编号
     */
    private String materialGuid;
    /**
     * 物料名称
     */
    private String materialName;

    /**
     * 物料数量
     */
    private Integer materialCount;

    /**
     * 物料类型
     */
    private Integer materialType;
}
