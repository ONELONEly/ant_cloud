package com.gree.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gree.entity.po.UserPO;
import com.gree.model.MyPage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper extends BaseMapper<UserPO> {

    @Select("select * from cbase000 where usid = #{usid} and pawd = #{pawd}")
    @ResultMap("userMap")
    UserPO fetchByDSPW(@Param("usid")String usid, @Param("pawd")String pawd);

    @Select("select * from cbase000 where usid = #{usid}")
    @ResultMap("userMap")
    UserPO fetchByUSID(@Param("usid")String usid);

    @Update("update cbase000 set dsca = #{user.userName} where usid = #{user.usid}")
    void updateByUsid(@Param("user") UserPO userPO);

    MyPage<UserPO> queryAllByPage(@Param("pager")MyPage<UserPO> pager);
}
