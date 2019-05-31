package com.gree.entity.po;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;

import com.gree.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

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
@NoArgsConstructor
@Accessors(chain = true)
@TableName("MENU")
@KeySequence(value = "SEQ_menu",clazz = Integer.class)
public class MenuPO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @TableId(value = "MENU_ID", type = IdType.INPUT)
    private Integer menuId;

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
    @TableField(value = "MODIFY_DATE",fill = FieldFill.INSERT_UPDATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
    private Integer version;

    public MenuPO(String src, String title, String url, String icon, String modifyUser, Integer version) {
        this.src = src;
        this.title = title;
        this.url = url;
        this.icon = icon;
        this.modifyUser = modifyUser;
        this.version = version;
    }
}
