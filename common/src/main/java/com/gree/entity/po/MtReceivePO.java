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
 * 物料领用表
 * </p>
 *
 * @author jinyu
 * @since 2019-04-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("MT_RECEIVE")
public class MtReceivePO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 物料单号
     */
    @TableId(value = "RECEIVE_GUID",type = IdType.ID_WORKER_STR)
    private String receiveGuid;

    /**
     * 申请人->CBASE000
     */
    @TableField("PROPOSER")
    private String proposer;

    /**
     * 区域ID -> CBASE008
     */
    @TableField("REGION")
    private String region;

    /**
     * 状态->0:提交,1:已提交
     */
    @TableField("STATUS")
    private Integer status;

    /**
     * 审批状态->0:同意,1:不同意
     */
    @TableField("APPROVE_STATUS")
    private Integer approveStatus;

    /**
     * 审批人->CBASE000
     */
    @TableField("APPROVER")
    private String approver;

    /**
     * 填写日期
     */
    @TableField("SET_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime setDate;

    /**
     * 提交日期
     */
    @TableField("PUSH_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime pushDate;

    /**
     * 领取种类->Variety
     */
    @TableField("RECEIVE_VARIETY")
    private Integer receiveVariety;

    /**
     * 领用日期
     */
    @TableField("RECEIVE_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime receiveDate;

    /**
     * 领用用途
     */
    @TableField("RECEIVE_BUSSINESS")
    private String receiveBussiness;

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
