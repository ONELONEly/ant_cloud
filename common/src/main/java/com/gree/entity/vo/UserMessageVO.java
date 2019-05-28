package com.gree.entity.vo;

import com.gree.entity.dto.UserDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;

@Data
@ApiModel(value = "user",description = "用户对象.")
public class UserMessageVO implements Serializable {
    private static final long serialVersionUID = -6503641354969988281L;

    @ApiModelProperty(value = "用户ID，邮箱号",name = "userId",example = "180484",dataType = "String",required = true)/*hidden隐藏*/
    private String userId;

    @ApiModelProperty(value = "用户名",name = "userName",example = "锦遇")
    private String userName;

    @ApiModelProperty(value = "菜单权限",name = "access",example = "['admin','admin']")
    private String[] access;

    @ApiModelProperty(value = "用户token",name = "token",example = "gulugulu")
    private String token;

    @ApiModelProperty(value = "用户头像",name = "avatar",example = "https://")
    private String avatar;

    public UserMessageVO() {
    }

    public UserMessageVO (UserDto userDto, String[] access, String token) {
        this.userId = userDto.getUserId();
        this.userName = userDto.getUserName();
        this.access = access;
        this.token = token;
        this.avatar = userDto.getAvatar();
    }


}
