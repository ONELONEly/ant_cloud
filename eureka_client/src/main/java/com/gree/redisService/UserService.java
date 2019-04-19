package com.gree.redisService;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gree.entity.vo.User;
import com.gree.model.MyPage;
import com.gree.util.DatabaseType;
import com.gree.util.TargetDataSource;

public interface UserService extends IService<User> {

    @TargetDataSource(DatabaseType.master)
    User fetchByDSPW(String dsca, String pawd);

    @TargetDataSource(DatabaseType.master)
    User fetchByUSID(String usid);

    @TargetDataSource(DatabaseType.slave)
    void updateByUsid(User user);

    @TargetDataSource(DatabaseType.slave)
    Integer deleteByusid(String usid);

    @TargetDataSource(DatabaseType.master)
    MyPage<User> queryAllByPage(MyPage<User> pager);
}
