package com.ys.atcrowdfunding.manager.service;

import com.ys.atcrowdfunding.bean.User;

import java.util.Map;

public interface UserService {

    User queryUserLogin(Map<String, Object> paramMap);
}
