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
@KeySequence(value = "SEQ_vender",clazz = Integer.class)
public class VenderPO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 厂家编号
     */
    @TableId(value = "VENDER_ID",type = IdType.INPUT)
    private Integer venderId;

    /**
     * 厂家名称
     */
    @TableField("VENDER_NAME")
    private String venderName;

    /**
     * 厂家类型 0：画册，1：礼品
     */
    @TableField("VENDER_TYPE")
    private Integer venderType;

    /**
     * 联系方式
     */
    @TableField("TELEPHONE")
    private String telephone;

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
