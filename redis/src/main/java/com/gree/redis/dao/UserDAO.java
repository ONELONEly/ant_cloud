package com.gree.redis.dao;

import com.gree.redis.entity.User;
import com.gree.redis.util.DatabaseType;
import com.gree.redis.util.TargetDataSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserDAO {

    @Select("select * from cbase000 where dsca = #{dsca} and pawd = #{pawd}")
    @TargetDataSource(DatabaseType.master)
    User fetchByDSPW(@Param("dsca")String dsca, @Param("pawd")String pawd);

    @Select("select * from cbase000 where usid = #{usid}")
    @TargetDataSource(DatabaseType.slave)
    User fetchByUSID(@Param("usid")String usid);

    @Update("update cbase000 set usid = #{usid}")
    @TargetDataSource(DatabaseType.master)
    void updateByUsid(@Param("usid")String usid);
}