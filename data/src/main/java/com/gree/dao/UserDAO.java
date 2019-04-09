package com.gree.dao;

import com.gree.util.DatabaseType;
import com.gree.util.TargetDataSource;
import com.gree.entity.vo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserDAO {

    @Select("select * from cbase000 where dsca = #{dsca} and pawd = #{pawd}")
    @TargetDataSource(DatabaseType.master)
    @ResultMap("userMap")
    User fetchByDSPW(@Param("dsca")String dsca, @Param("pawd")String pawd);

    @Select("select * from cbase000 where usid = #{usid}")
    @TargetDataSource(DatabaseType.slave)
    @ResultMap("userMap")
    User fetchByUSID(@Param("usid")String usid);

    @Update("update cbase000 set usid = #{usid}")
    @TargetDataSource(DatabaseType.master)
    void updateByUsid(@Param("usid")String usid);
}