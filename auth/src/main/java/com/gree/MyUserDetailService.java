package com.gree;

import com.gree.dao.UserDAO;
import com.gree.entity.vo.Permission;
import com.gree.entity.vo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class MyUserDetailService implements UserDetailsService {

    private final UserDAO userDAO;

    @Autowired
    public MyUserDetailService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String memberName) throws UsernameNotFoundException {

        com.gree.entity.vo.User member = userDAO.fetchByUSID(memberName);
        if (member == null) {
            throw new UsernameNotFoundException(memberName);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        // 可用性 :true:可用 false:不可用
        boolean enabled = true;
        // 过期性 :true:没过期 false:过期
        boolean accountNonExpired = true;
        // 有效性 :true:凭证有效 false:凭证无效
        boolean credentialsNonExpired = true;
        // 锁定性 :true:未锁定 false:已锁定
        boolean accountNonLocked = true;
        for (Role role : member.getRoles()) {
            //角色必须是ROLE_开头，可以在数据库中设置
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleName());
            grantedAuthorities.add(grantedAuthority);
            //获取权限
            for (Permission permission : role.getPermissions()) {
                GrantedAuthority authority = new SimpleGrantedAuthority(permission.getUrl());
                grantedAuthorities.add(authority);
            }
        }
        return new User(member.getUserName(), member.getPassWord(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
    }
}

