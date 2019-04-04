package com.gree.redis.service.serviceImp;

import com.gree.redis.dao.UserDAO;
import com.gree.redis.entity.User;
import com.gree.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImp(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User fetchByDSPW(String dsca, String pawd) {
        return userDAO.fetchByDSPW(dsca,pawd);
    }

    @Override
    public User fetchByUSID(String usid) {
        return userDAO.fetchByUSID(usid);
    }

    @Override
    public void updateByUsid(String usid) {
        userDAO.updateByUsid(usid);
    }
}
