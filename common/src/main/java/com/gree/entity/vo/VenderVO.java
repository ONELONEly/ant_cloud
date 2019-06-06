package com.gree.entity.vo;

import com.gree.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class VenderVO extends BaseEntity {


    private static final long serialVersionUID = -1249906436813371617L;
    /**
     * 厂家编号
     */
    private Integer venderId;

    /**
     * 厂家名称
     */
    private String venderName;

    /**
     * 厂家类型 0：画册，1：礼品
     */
    private Integer venderType;

    /**
     * 联系方式
     */
    private String telephone;
}
