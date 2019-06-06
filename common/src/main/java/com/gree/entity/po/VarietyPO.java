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
 * 种类表
 * </p>
 *
 * @author jinyu
 * @since 2019-04-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("VARIETY")
@KeySequence(value = "SEQ_variety",clazz = Integer.class)
public class VarietyPO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 种类
     */
    @TableId(value = "VARIETY_ID",type = IdType.INPUT)
    private Integer varietyId;

    /**
     * 种类名称
     */
    @TableField("VARIETY_NAME")
    private String varietyName;

    /**
     * 种类类型 0：画册，1：礼品
     */
    @TableField("VARIETY_TYPE")
    private Integer varietyType;

    /**
     * 最后修改日期
     */
    @TableField(value = "MODIFY_DATE",fill = FieldFill.INSERT_UPDATE)
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
