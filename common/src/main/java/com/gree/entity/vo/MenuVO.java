package com.gree.entity.vo;

import com.gree.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
 * @version 1.0
 * @description 菜单与界面交互的实体
 * @createTime 2019 -05-31 15:34:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MenuVO", description = "菜单对象")
public class MenuVO extends BaseEntity {
    private static final long serialVersionUID = 971530802849209444L;
    /**
     * @description 图片地址
     * @createTime 2019 -05-30 19:37:23
     * @version 1.0
     */
    @ApiModelProperty(value = "图片地址", name = "src", example = "icon/paint.png")
    private String src;
    /**
     * @description 菜单标题
     * @createTime 2019 -05-30 19:37:23
     * @version 1.0
     */
    @ApiModelProperty(value = "菜单标题", name = "title", example = "画册领取")
    private String title;
    /**
     * @description 菜单映射地址
     * @createTime 2019 -05-30 19:37:23
     * @version 1.0
     */
    @ApiModelProperty(value = "菜单映射地址", name = "url", example = "./paintAccessory")
    private String url;
    /**
     * @description icon样式
     * @createTime 2019 -05-30 19:37:23
     * @version 1.0
     */
    @ApiModelProperty(value = "icon样式", name = "icon", example = "icon-icon_wangye")
    private String icon;
}
