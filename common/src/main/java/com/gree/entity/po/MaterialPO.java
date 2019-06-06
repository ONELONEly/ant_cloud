package com.gree.entity.po;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;

import com.gree.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 物料表
 * </p>
 *
 * @author jinyu
 * @since 2019-04-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("MATERIAL")
@NoArgsConstructor
public class MaterialPO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 物料ID
     */
    @TableId(value = "MATERIAL_GUID",type = IdType.INPUT)
    private String materialGuid;

    /**
     * 物料名称
     */
    @TableField("MATERIAL_NAME")
    private String materialName;

    /**
     * 库存数量
     */
    @TableField("MT_VENDER_COUNT")
    private Integer mtVenderCount;

    /**
     * 种类ID
     */
    @TableField("VARIETY_ID")
    private Integer varietyId;

    /**
     * 最后修改日期
     */
    @TableField(value = "MODIFY_DATE",fill = FieldFill.INSERT_UPDATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifyDate;

    /**
     * 版本号
     */
    @TableField("VERSION")
    private Integer version;

    /**
     * 最后修改用户
     */
    @TableField("MODIFY_USER")
    private String modifyUser;

    public MaterialPO(String materialName, Integer mtVenderCount, Integer varietyId, Integer version, String modifyUser) {
        this.materialName = materialName;
        this.mtVenderCount = mtVenderCount;
        this.varietyId = varietyId;
        this.version = version;
        this.modifyUser = modifyUser;
    }
}
