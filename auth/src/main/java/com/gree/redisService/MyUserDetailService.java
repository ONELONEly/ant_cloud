package com.gree.redisService;

import com.gree.entity.po.PermissionPO;
import com.gree.entity.po.RolePO;
import com.gree.entity.po.UserPO;
import com.gree.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MyUserDetailService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String memberName) throws UsernameNotFoundException {
        logger.info("艺锦欧巴已经进入local{}",memberName);
        UserPO member = userMapper.fetchByUSID(memberName);
        if (member == null) {
            throw new UsernameNotFoundException(memberName);
        }
        member.setDeac(new BCryptPasswordEncoder().encode(member.getDeac()));
        RolePO role_PO_ = new RolePO("管理员");
        List<RolePO> rolePOS = new ArrayList<>();
        rolePOS.add(role_PO_);
        PermissionPO permission_PO_ = new PermissionPO("http://localhost:8764/sayHello?name=feign");
        PermissionPO permission_PO_0 = new PermissionPO("hello");
        PermissionPO permission_PO_1 = new PermissionPO("query");
        List<PermissionPO> permissionPOS = new ArrayList<>();
        permissionPOS.add(permission_PO_);
        permissionPOS.add(permission_PO_0);
        permissionPOS.add(permission_PO_1);
        role_PO_.setPermissionPOS(permissionPOS);
        member.setRolePOS(rolePOS);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        // 可用性 :true:可用 false:不可用
        boolean enabled = true;
        // 过期性 :true:没过期 false:过期
        boolean accountNonExpired = true;
        // 有效性 :true:凭证有效 false:凭证无效
        boolean credentialsNonExpired = true;
        // 锁定性 :true:未锁定 false:已锁定
        boolean accountNonLocked = true;
        for (RolePO rolePO : member.getRolePOS()) {
            //角色必须是ROLE_开头，可以在数据库中设置
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(rolePO.getDsca());
            grantedAuthorities.add(grantedAuthority);
            //获取权限
            for (PermissionPO permissionPO : rolePO.getPermissionPOS()) {
                GrantedAuthority authority = new SimpleGrantedAuthority(permissionPO.getDsca());
                grantedAuthorities.add(authority);
            }
        }
        logger.info("艺锦欧巴已经结束local");
        return new User(member.getDeac(), member.getPawd(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
    }
}

