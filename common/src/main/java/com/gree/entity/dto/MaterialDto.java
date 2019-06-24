package com.gree.entity.dto;

import com.gree.entity.BaseEntity;
import com.gree.entity.vo.MaterialVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class MaterialDto extends BaseEntity {
    private static final long serialVersionUID = 4179489741121762886L;

    /**
     * 物料ID
     */
    private String materialGuid;

    /**
     * 物料名称
     */
    private String materialName;

    /**
     * 库存数量
     */
    private Integer mtVenderCount;

    /**
     * 种类ID
     */
    private Integer varietyId;

    public MaterialDto(MaterialVO materialVO) {
        this.materialGuid = materialVO.getMaterialGuid();
        this.materialName = materialVO.getMaterialName();
        this.mtVenderCount = materialVO.getMaterialCount();
        this.varietyId = materialVO.getMaterialType();
    }
}
