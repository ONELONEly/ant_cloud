package com.gree.entity.vo;

import com.gree.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "VarietyVO", description = "种类对象")
public class VarietyVO extends BaseEntity {
    private static final long serialVersionUID = 7585448798912108720L;


    /**
     * 种类
     */
    @ApiModelProperty(value = "种类ID",name = "varietyId",example = "1")
    private Integer varietyId;

    /**
     * 种类类型 0：画册，1：礼品
     */
    @ApiModelProperty(value = "种类类型",name = "varietyType",example = "1")
    private Integer varietyType;

    /**
     * 种类名称
     */
    @ApiModelProperty(value = "种类名称",name = "varietyName",example = "图片")
    private String varietyName;
}
