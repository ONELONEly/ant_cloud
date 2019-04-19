package com.gree.controller;

import com.gree.result.RestResponse;
import com.gree.result.ResultBody;
import com.gree.redisService.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 〈会员Controller〉
 *
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api")
public class MemberController {

    private final MyUserDetailService userDetailService;

    private final ConsumerTokenServices consumerTokenServices;

    @Autowired
    public MemberController(MyUserDetailService userDetailService, ConsumerTokenServices consumerTokenServices) {
        this.userDetailService = userDetailService;
        this.consumerTokenServices = consumerTokenServices;
    }

    @GetMapping("/member")
    public RestResponse<Principal> user(Principal member) {
        //获取当前用户信息
        return new RestResponse<>(member,null);
    }

    @DeleteMapping(value = "/exit")
    public RestResponse<String> revokeToken(String access_token) {
        //注销当前用户
        if (consumerTokenServices.revokeToken(access_token)) {
            return new RestResponse<>(ResultBody.success("注销成功"),null);
        } else {
            return new RestResponse<>(ResultBody.error("注销失败"),null);
        }
    }
}
