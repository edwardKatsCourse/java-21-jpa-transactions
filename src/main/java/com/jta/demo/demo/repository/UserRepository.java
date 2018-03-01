package com.jta.demo.demo.repository;

import com.jta.demo.demo.model.User;

import java.util.List;

public interface UserRepository {

    User save(User user);
    User update(User user);
    User findById(Integer userId);
    List<User> findAll();
    User findByUsername(String username);
}
