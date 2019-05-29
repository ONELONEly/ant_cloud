package com.gree.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.gree.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author jinyu
 * @since 2019-05-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("MENU")
public class MenuPO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @TableId("MENU_ID")
    private Double menuId;

    /**
     * 图片地址
     */
    @TableField("SRC")
    private String src;

    /**
     * 标题
     */
    @TableField("TITLE")
    private String title;

    /**
     * 映射目录
     */
    @TableField("URL")
    private String url;

    /**
     * icon样式
     */
    @TableField("ICON")
    private String icon;

    /**
     * 最后修改日期
     */
    @TableField("MODIFY_DATE")
    private LocalDateTime modifyDate;

    /**
     * 最后修改人
     */
    @TableField("MODIFY_USER")
    private String modifyUser;

    /**
     * 版本
     */
    @TableField("VERSION")
    private Double version;


}
