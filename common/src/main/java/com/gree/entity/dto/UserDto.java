package com.gree.entity.dto;

import com.gree.entity.po.UserPO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author create by jinyuk@foxmail.com(180365@gree.com.cn).
 * @version 1.0
 * @description 用户数据传输实体
 * @createTime 2019 -05-27 11:27:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    /**
     * @description 用户ID
     * @createTime 2019 -05-27 11:27:03
     * @version 1.0
     */
    private String userId;

    /**
     * @description 用户姓名
     * @createTime 2019 -05-27 11:27:03
     * @version 1.0
     */
    private String userName;

    /**
     * @description 用户头像
     * @createTime 2019 -05-27 11:27:03
     * @version 1.0
     */
    private String avatar;

    public UserDto(UserPO userPO) {
        this.userId = userPO.getUsid();
        this.userName = userPO.getNama();
        this.avatar = "default";
    }
}
