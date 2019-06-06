package com.gree.entity.vo;

import com.gree.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class MaterialVarietyVO extends BaseEntity {
    private static final long serialVersionUID = -2308976978905162205L;
    /**
     * 物料ID
     */
    @ApiModelProperty(value = "物料Id",name = "materialGuid",example = "1996081485214865321")
    private String materialGuid;

    /**
     * 物料名称
     */
    @ApiModelProperty(value = "物料名称",name = "materialName",example = "飘飘飘")
    private String materialName;
}
