package com.gree.redis.service.serviceImp;

import com.gree.redis.dao.UserDAO;
import com.gree.redis.entity.User;
import com.gree.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImp(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Cacheable(cacheNames = {"user"},key = "#p0")
    public User fetchByDSPW(String dsca, String pawd) {
        return userDAO.fetchByDSPW(dsca,pawd);
    }

    @Override
    @Cacheable(cacheNames = {"user"},key = "#p0") //用户数组查询缓存
    public User fetchByUSID(String usid) {
        return userDAO.fetchByUSID(usid);
    }

    @Override
    @CachePut(cacheNames = {"user"},key = "#p0") //单个对象添加缓存
    public void updateByUsid(String usid) {
        userDAO.updateByUsid(usid);
    }

    @Override
    @CacheEvict(cacheNames = {"user"},key = "#p0") //删除缓存 allEntries=true 删除所有的缓存
//    @Caching   //@Cacheable,@CacheaPut,@CacheaEvict都可以使用
//    @CacheaPut 存放数据
    public void deleteByusid(String usid) {

    }


}
