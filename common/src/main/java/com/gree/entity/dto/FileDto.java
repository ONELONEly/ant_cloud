package com.gree.entity.dto;

import com.gree.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
 * @version 1.0
 * @description 文件传输层实体
 * @createTime 2019 -06-05 17:15:32
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FileDto extends BaseEntity {

    private static final long serialVersionUID = 2322465916935953856L;

    /**
     * @description 主支Id
     * @createTime 2019 -06-05 17:16:26
     * @version 1.0
     */
    private String masterGuid;
    /**
     * @description 文件base64码
     * @createTime 2019 -06-05 17:13:42
     * @version 1.0
     */
    private String fileInput;
    /**
     * @description 文件名称
     * @createTime 2019 -06-05 17:13:43
     * @version 1.0
     */
    private String fileName;
    /**
     * @description 文件大小
     * @createTime 2019 -06-05 17:13:43
     * @version 1.0
     */
    private Long size;
    /**
     * @description 文件属性
     * @createTime 2019 -06-05 17:13:43
     * @version 1.0
     */
    private String suffix;
}
