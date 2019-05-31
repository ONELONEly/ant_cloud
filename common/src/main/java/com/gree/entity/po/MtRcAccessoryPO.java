package com.gree.entity.po;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.sql.Blob;
import java.util.Date;

import com.gree.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 物料领用附件表
 * </p>
 *
 * @author jinyu
 * @since 2019-04-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("MT_RC_ACCESSORY")
public class MtRcAccessoryPO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 物料领用附件编号
     */
    @TableId(value = "ACCESSORY_GUID",type = IdType.ID_WORKER_STR)
    private String AccessoryGuid;

    /**
     * 物料领用单号
     */
    @TableField("RECEIVE_GUID")
    private String receiveGuid;

    /**
     * 物料领用图片资源
     */
    @TableField("MC_RC_ACCESSORY_RESOURCE")
    private Blob mcRcAccessoryResource;

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
    private Integer version;


}
