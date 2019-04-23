package com.gree.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.gree.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 进程表
 * </p>
 *
 * @author jinyu
 * @since 2019-04-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("CBASE002")
public class RolePO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 进程号
     */
    @TableId("PONO")
    private String pono;

    /**
     * 描述
     */
    @TableField("DSCA")
    private String dsca;

    /**
     * 节点类型
     */
    @TableField("STYP")
    private Integer styp;

    /**
     * 路径
     */
    @TableField("PURL")
    private String purl;

    /**
     * 版本
     */
    @TableField("VERSION")
    private Integer version;

    private List<PermissionPO> permissionPOS;

    public RolePO(String dsca) {
        this.dsca = dsca;
    }
}
