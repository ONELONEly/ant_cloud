package com.gree.redis.dao;

import com.gree.redis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserDAO {

    @Select("select * from cbase000 where dsca = #{dsca} and pawd = #{pawd}")
    @Cacheable(key = "#p0")
    User fetchByDSPW(@Param("dsca")String dsca, @Param("pawd")String pawd);

    @Select("select * from cbase000 where usid = #{usid}")
    @Cacheable(key = "#p0")
    User fetchByUSID(@Param("usid")String usid);

    @Update("update cbase000 set usid = #{usid}")
    @CachePut(key = "#p0")
    void updateByUsid(@Param("usid")String usid);
}