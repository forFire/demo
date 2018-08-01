package com.flyer.service.impl;

import com.flyer.domain.User;
import com.flyer.mapper.UserMapper;
import com.flyer.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    UserMapper userMapper;

    public int register(User user) {
     return   userMapper.insert(user);
    }
}
