package com.gree.entity.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gree.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("MT_STORAGE")
public class MtStorageDto extends BaseEntity {

    private static final long serialVersionUID = -7865325966612369818L;
    /**
     * 入库编号
     */
    private String storageGuid;

    /**
     * 收货人编号->默认当前用户
     */
    private String receiver;

    /**
     * 厂家编号
     */
    private Integer venderId;

    /**
     * 联系方式
     */
    private String contact;

    /**
     * 入库时间
     */
    private LocalDateTime storageDate;

    /**
     * 用途
     */
    private String business;

    /**
     * 状态 暂且不明了作用
     */
    private Integer status;
}
