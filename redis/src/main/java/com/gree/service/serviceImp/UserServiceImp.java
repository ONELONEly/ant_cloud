package com.gree.service.serviceImp;

import com.gree.entity.vo.User;
import com.gree.dao.UserDAO;
import com.gree.service.UserService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

//    @Resource
    private UserDAO userDAO = new UserDAO() {
        @Override
        public User fetchByDSPW(String dsca, String pawd) {
            return null;
        }

        @Override
        public User fetchByUSID(String usid) {
            return null;
        }

        @Override
        public void updateByUsid(String usid) {

        }
    };

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
