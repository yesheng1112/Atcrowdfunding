package com.ys.atcrowdfunding.manager.service.impl;

import com.ys.atcrowdfunding.bean.User;
import com.ys.atcrowdfunding.exception.LoginFailException;
import com.ys.atcrowdfunding.manager.dao.UserMapper;
import com.ys.atcrowdfunding.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User queryUserLogin(Map<String, Object> paramMap) {
        User user = (User) userMapper.queryUserlogin(paramMap);
        if (user == null){
            throw new LoginFailException("用户账号或密码不正确！");
        }
        return user;
    }
}
