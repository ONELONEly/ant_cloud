package com.gree.entity.vo;

import com.gree.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @NotBlank(message = "物料编号不能为空")
    private String materialGuid;
    /**
     * 物料名称
     */
    @NotBlank(message = "物料名称不能为空")
    private String materialName;

    /**
     * 物料数量
     */
    @NotNull(message = "物料数量不能为空")
    private Integer materialCount;

    /**
     * 物料类型
     */
    @NotNull(message = "物料类型不能为空")
    private Integer materialType;
}
