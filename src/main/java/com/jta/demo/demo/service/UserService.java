package com.jta.demo.demo.service;

import com.jta.demo.demo.model.UserRequest;
import com.jta.demo.demo.model.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse create(UserRequest userRequest) throws Exception;
    UserResponse update(UserRequest userRequest, Integer userId);
    List<UserResponse> getAll();
    UserResponse getByUsername(String username);
}
