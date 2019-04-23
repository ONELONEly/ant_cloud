package com.gree.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.gree.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author jinyu
 * @since 2019-04-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("CBASE007")
public class PermissionPO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色编号
     */
    @TableId("ROID")
    private String roid;

    /**
     * 描述
     */
    @TableField("DSCA")
    private String dsca;

    /**
     * 公司
     */
    @TableField("COMP")
    private String comp;

    /**
     * 状态；1：菜单角色管理，0：科室角色管理
     */
    @TableField("STAT")
    private String stat;

    /**
     * 版本号
     */
    @TableField("VERSION")
    private Integer version;

    public PermissionPO(String dsca) {
        this.dsca = dsca;
    }
}
