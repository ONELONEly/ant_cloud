package com.gree.redis.service;

import com.gree.redis.entity.User;

public interface UserService {

    User fetchByDSPW(String dsca, String pawd);
    User fetchByUSID(String usid);
    void updateByUsid(String usid);
    void deleteByusid(String usid);
}
