package com.flyer.service.impl;

import com.flyer.domain.CustomerExample;
import com.flyer.domain.User;
import com.flyer.domain.UserExample;
import com.flyer.mapper.UserMapper;
import com.flyer.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserMapper userMapper;


    public boolean login(String userName, String password) {

        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();

        criteria.andUserNameEqualTo(userName).andPasswordEqualTo(password);

        List<User> list = userMapper.selectByExample(userExample);


        if(list.size() > 0){
            return  true;
        }

        return false;
    }
}
