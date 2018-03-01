package com.jta.demo.demo.service;

import com.jta.demo.demo.exceptions.GeneralAPIException;
import com.jta.demo.demo.model.User;
import com.jta.demo.demo.model.UserInfo;
import com.jta.demo.demo.model.UserRequest;
import com.jta.demo.demo.model.UserResponse;
import com.jta.demo.demo.repository.UserInfoRepository;
import com.jta.demo.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
//    @Transactional(rollbackFor = Exception.class)
//    @Transactional
//    @Transactional(noRollbackFor = RuntimeException.class)
    @Transactional(noRollbackFor = RuntimeException.class, propagation = Propagation.REQUIRED)
    public UserResponse create(UserRequest userRequest) throws Exception {
        User user = new User();
        user.setUsername(userRequest.getUsername());

        userRepository.save(user);

//        if (0 == 0) {
//            throw new Exception("Some exception");
//        }

//        if (0 == 0) {
//            throw new RuntimeException("Unchecked exception");
//        }

//        if (0 == 0) {
//            throw new RuntimeException("No rollback exception");
//        }

        if (2 * 2 == 4) {
            throw new GeneralAPIException("Custom exception with RuntimeException");
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setUser(user);
        userInfo.setFirstName(userRequest.getFirstName());
        userInfo.setLastName(userRequest.getLastName());
        userInfoRepository.save(userInfo);

        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(userInfo.getFirstName())
                .lastName(userInfo.getLastName())
                .build();
    }


    @Override
    public UserResponse update(UserRequest userRequest, Integer userId) {
        return null;
    }

    @Override
    @Transactional
    public List<UserResponse> getAll() {
        List<UserInfo> userInfos = userInfoRepository.findAll();
        return userInfos
                .stream()
                .map(userInfo -> UserResponse
                        .builder()
                        .id(userInfo.getUser().getId())
                        .username(userInfo.getUser().getUsername())
                        .firstName(userInfo.getFirstName())
                        .lastName(userInfo.getLastName())
                        .build())
                .collect(Collectors.toList());

    }

    @Override
    public UserResponse getByUsername(String username) {
        return null;
    }
}
