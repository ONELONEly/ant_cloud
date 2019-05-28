package com.gree.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.gree.entity.BaseEntity;
import com.gree.entity.dto.UserDto;
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
@TableName("CBASE000")
public class UserPO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 员工编号
     */
    @TableId("USID")
    private String usid;

    /**
     * 姓名
     */
    @TableField("NAMA")
    private String nama;

    /**
     * 入职时间
     */
    @TableField("IDAT")
    private LocalDateTime idat;

    /**
     * 邮箱
     */
    @TableField("MAIL")
    private String mail;

    /**
     * 座机号
     */
    @TableField("PHO1")
    private String pho1;

    /**
     * 手机号
     */
    @TableField("MOD1")
    private String mod1;

    /**
     * 性别
     */
    @TableField("SEX1")
    private String sex1;

    /**
     * 是否在职
     */
    @TableField("STA1")
    private String sta1;

    /**
     * 部门名称
     */
    @TableField("DEPT")
    private String dept;

    /**
     * 所属系统
     */
    @TableField("SYSS")
    private String syss;

    /**
     * 所属区域
     */
    @TableField("AARC")
    private String aarc;

    /**
     * 科室
     */
    @TableField("OFFI")
    private String offi;

    /**
     * 团队
     */
    @TableField("TEAM")
    private String team;

    /**
     * 小组
     */
    @TableField("GROU")
    private String grou;

    /**
     * 职务
     */
    @TableField("POST")
    private String post;

    /**
     * 管理区域
     */
    @TableField("AREA")
    private LocalDateTime area;

    /**
     * 学历
     */
    @TableField("EDUA")
    private String edua;

    /**
     * 部别
     */
    @TableField("DEAC")
    private String deac;

    /**
     * 群体类别 : 0:员工；1：主管；2:中层干部
     */
    @TableField("PTYP")
    private String ptyp;

    /**
     * 备注
     */
    @TableField("REAM")
    private String ream;

    /**
     * 权限:0个人;1：科室; 2:部门; 3 :公司
     */
    @TableField("STA2")
    private String sta2;

    /**
     * 密码
     */
    @TableField("PAWD")
    private String pawd;

    /**
     * 版本
     */
    @TableField("VERSION")
    private Integer version;

    private List<RolePO> rolePOS;


}
