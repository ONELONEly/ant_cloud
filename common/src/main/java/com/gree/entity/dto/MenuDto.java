package com.gree.entity.dto;

import com.gree.entity.BaseEntity;
import com.gree.entity.po.MenuPO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
 * @version 1.0
 * @description 菜单的传输实体
 * @createTime 2019 -05-30 19:37:23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto extends BaseEntity {

    /**
     * @description 序列号，保证对象在序列化前后保证是同一对象
     * @createTime 2019 -05-30 19:37:23
     * @version 1.0
     */
    private static final long serialVersionUID = 803488616954460079L;
    /**
     * @description 菜单ID
     * @createTime 2019 -05-30 19:37:23
     * @version 1.0
     */
    private Integer menuId;
    /**
     * @description 图片地址
     * @createTime 2019 -05-30 19:37:23
     * @version 1.0
     */
    private String src;
    /**
     * @description 菜单标题
     * @createTime 2019 -05-30 19:37:23
     * @version 1.0
     */
    private String title;
    /**
     * @description 菜单映射地址
     * @createTime 2019 -05-30 19:37:23
     * @version 1.0
     */
    private String url;
    /**
     * @description icon样式
     * @createTime 2019 -05-30 19:37:23
     * @version 1.0
     */
    private String icon;

    /**
     * Instantiates a new Menu dto.
     *
     * @param menuPO 菜单数据库映射实体
     * @description 通过数据库映射实体实例化一个菜单传输实体
     * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
     * @createTime 2019 -05-30 19:37:23
     */
    public MenuDto(MenuPO menuPO) {
        this.menuId = menuPO.getMenuId();
        this.src = menuPO.getSrc();
        this.title = menuPO.getTitle();
        this.url = menuPO.getUrl();
        this.icon = menuPO.getIcon();
    }
}
