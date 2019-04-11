package com.gree.util;

import org.springframework.security.crypto.password.PasswordEncoder;

/*密码验证，为了方便我使用了不加密的方式，重写了 PasswordEncoder， 实际开发还是建议使用 BCryptPasswordEncoder*/
public class NoEncryptPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return (String) charSequence;
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        //密码对比 密码对 true 反之 false
        // CharSequence 数据库中的密码
        // s 前台传入的密码
        return s.contentEquals(charSequence);
    }
}
