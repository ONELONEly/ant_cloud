package com.gree.service;

import com.gree.entity.vo.User;

public interface UserService {

    User fetchByDSPW(String dsca, String pawd);
    User fetchByUSID(String usid);
    void updateByUsid(String usid);
    void deleteByusid(String usid);
}
