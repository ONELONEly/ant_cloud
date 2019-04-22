package com.gree.redisService;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gree.entity.po.UserPO;
import com.gree.model.MyPage;
import com.gree.util.DatabaseType;
import com.gree.util.TargetDataSource;

public interface UserService extends IService<UserPO> {

    @TargetDataSource(DatabaseType.master)
    UserPO fetchByDSPW(String dsca, String pawd);

    @TargetDataSource(DatabaseType.master)
    UserPO fetchByUSID(String usid);

    @TargetDataSource(DatabaseType.slave)
    void updateByUsid(UserPO userPO);

    @TargetDataSource(DatabaseType.slave)
    Integer deleteByusid(String usid);

    @TargetDataSource(DatabaseType.master)
    MyPage<UserPO> queryAllByPage(MyPage<UserPO> pager);
}
