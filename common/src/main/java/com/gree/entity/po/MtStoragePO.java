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
 * 入库主表
 * </p>
 *
 * @author jinyu
 * @since 2019-04-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("MT_STORAGE")
@KeySequence("SEQ_storage")
public class MtStoragePO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 入库单号
     */
    @TableId(value = "STORAGE_GUID",type = IdType.ID_WORKER_STR)
    private String storageGuid;

    /**
     * 收货人编号->默认当前用户
     */
    @TableField("RECEIVER")
    private String receiver;

    /**
     * 厂家编号
     */
    @TableField("VENDER_ID")
    private Integer venderId;

    /**
     * 联系方式
     */
    @TableField("CONTACT")
    private String contact;

    /**
     * 入库时间
     */
    @TableField("STORAGE_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime storageDate;

    /**
     * 用途
     */
    @TableField("BUSSINESS")
    private String bussiness;

    /**
     * 状态
     */
    @TableField("STATUS")
    private Integer status;

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
