package com.gree.entity.po;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;

import com.gree.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 附件日志表
 * </p>
 *
 * @author jinyu
 * @since 2019-04-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ACCESSORY_LOG")
public class AccessoryLogPO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 附件日志ID
     */
    @TableId(value = "AL_GUID",type = IdType.ID_WORKER_STR)
    private String alGuid;

    /**
     * 附件名称
     */
    @TableField("ACCESSORY_NAME")
    private String accessoryName;

    /**
     * 附件目录
     */
    @TableField("ACCESSORY_CATALOGUE")
    private String accessoryCatalogue;

    /**
     * 附件大小
     */
    @TableField("ACCESSORY_SIZE")
    private Integer accessorySize;

    /**
     * 附件类型
     */
    @TableField("ACCESSORY_TYPE")
    private Integer accessoryType;

    /**
     * 上传日期
     */
    @TableField("PUSH_DATE")
    private LocalDateTime pushDate;

    /**
     * 上传用户
     */
    @TableField("PROVIDER")
    private String provider;

    /**
     * 记录版本
     */
    @TableField("VERSION")
    private Integer version;


}
