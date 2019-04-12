package com.gree.service;

import com.gree.dao.UserDAO;
import com.gree.entity.vo.Permission;
import com.gree.entity.vo.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MyUserDetailService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final UserDAO userDAO;

    @Autowired
    public MyUserDetailService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String memberName) throws UsernameNotFoundException {
        logger.info("艺锦欧巴已经进入local");
        com.gree.entity.vo.User member = userDAO.fetchByUSID(memberName);
        member = new com.gree.entity.vo.User("180484","admin",new BCryptPasswordEncoder().encode("admin"));
        Role role_ = new Role("管理员");
        List<Role> roles = new ArrayList<>();
        roles.add(role_);
        Permission permission_ = new Permission("http://localhost:8764/sayHello?name=feign");
        Permission permission_0 = new Permission("hello");
        Permission permission_1 = new Permission("query");
        List<Permission> permissions = new ArrayList<>();
        permissions.add(permission_);
        permissions.add(permission_0);
        permissions.add(permission_1);
        role_.setPermissions(permissions);
        member.setRoles(roles);
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

