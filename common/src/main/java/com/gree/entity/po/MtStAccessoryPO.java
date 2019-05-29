package com.gree.entity.po;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.sql.Blob;

import com.gree.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 入库附件表
 * </p>
 *
 * @author jinyu
 * @since 2019-04-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("MT_ST_ACCESSORY")
@KeySequence("SEQ_stAccessory")
public class MtStAccessoryPO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 入库资源编号
     */
    @TableId(value = "ACCESSORY_GUID",type = IdType.ID_WORKER_STR)
    private String AccessoryGuid;

    /**
     * 入库单号
     */
    @TableField("STORAGE_GUID")
    private String storageGuid;

    /**
     * 入库图片
     */
    @TableField("ST_ACCESSORY_RESOURCE")
    private Blob stAccessoryResource;

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
