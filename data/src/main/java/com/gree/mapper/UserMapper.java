package com.gree.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gree.entity.vo.User;
import com.gree.model.MyPage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper extends BaseMapper<User> {

    @Select("select * from cbase000 where usid = #{usid} and pawd = #{pawd}")
    @ResultMap("userMap")
    User fetchByDSPW(@Param("usid")String usid, @Param("pawd")String pawd);

    @Select("select * from cbase000 where usid = #{usid}")
    @ResultMap("userMap")
    User fetchByUSID(@Param("usid")String usid);

    @Update("update cbase000 set dsca = #{user.userName} where usid = #{user.usid}")
    void updateByUsid(@Param("user")User user);

    MyPage<User> queryAllByPage(@Param("pager")MyPage<User> pager);
}
