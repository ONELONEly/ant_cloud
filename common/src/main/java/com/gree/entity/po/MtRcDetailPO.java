package com.gree.entity.po;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;

import com.gree.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 物料领用明细表
 * </p>
 *
 * @author jinyu
 * @since 2019-04-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("MT_RC_DETAIL")
@KeySequence("SEQ_mtRcDetail")
public class MtRcDetailPO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 物料明细单号
     */
    @TableId(value = "MT_RC_DETAIL_GUID",type = IdType.INPUT)
    private String mtRcDetailGuid;

    /**
     * 物料领用单号
     */
    @TableField("MT_RECEIVE_GUID")
    private String mtReceiveGuid;

    /**
     * 物料编码
     */
    @TableField("MATERIAL_GUID")
    private String materialGuid;

    /**
     * 物料名称
     */
    @TableField("MATERIAL_NAME")
    private String materialName;

    /**
     * 物料领用数量
     */
    @TableField("MT_RECEIVE_COUNT")
    private Double mtReceiveCount;

    /**
     * 计数单位->eg:个,张,台
     */
    @TableField("MT_RECEIVE_UNIT")
    private String mtReceiveUnit;

    /**
     * 最后修改日期
     */
    @TableField("MODIFY_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifyDate;

    /**
     * 最后修改用户
     */
    @TableField("MODIFY_USER")
    private String modifyUser;

    /**
     * 版本号
     */
    @TableField("VERSION")
    private Double version;


}
