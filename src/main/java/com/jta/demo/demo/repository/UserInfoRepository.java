package com.jta.demo.demo.repository;

import com.jta.demo.demo.model.User;
import com.jta.demo.demo.model.UserInfo;

import java.util.List;

public interface UserInfoRepository {

    UserInfo save(UserInfo userInfo);
    UserInfo update(UserInfo userInfo);
    UserInfo findById(Integer userInfoId);
    UserInfo findByUser(User user);
    List<UserInfo> findAll();


}
