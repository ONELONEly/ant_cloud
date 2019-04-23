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
 * 厂家表
 * </p>
 *
 * @author jinyu
 * @since 2019-04-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("VENDER")
@KeySequence(value = "SEQ_material",clazz = Integer.class)
public class VenderPO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 厂家编号
     */
    @TableId(value = "VENDER_GUID",type = IdType.INPUT)
    private String venderGuid;

    /**
     * 厂家名称
     */
    @TableField("VENDER_NAME")
    private String venderName;

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
