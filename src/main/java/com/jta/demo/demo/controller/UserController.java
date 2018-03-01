package com.jta.demo.demo.controller;

import com.jta.demo.demo.model.UserRequest;
import com.jta.demo.demo.model.UserResponse;
import com.jta.demo.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public UserResponse create(@RequestBody UserRequest userRequest) throws Exception {
        return userService.create(userRequest);
    }

    @PutMapping("/update/{userId}")
    public UserResponse update(@RequestBody UserRequest userRequest,
                               @PathVariable("userId") Integer userId) {
        return userService.update(userRequest, userId);
    }

    @GetMapping("/all")
    public List<UserResponse> getAll() {
        return userService.getAll();
    }

    @GetMapping("/search")
    public UserResponse getByUsername(@RequestParam("username") String username) {
        return userService.getByUsername(username);
    }
}
