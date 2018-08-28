package com.ys.atcrowdfunding.manager.service;

import com.ys.atcrowdfunding.bean.User;
import com.ys.atcrowdfunding.util.Page;

import java.util.Map;

public interface UserService {

    User queryUserLogin(Map<String, Object> paramMap);

    Page<User> queryPage(Map<String ,Object> paramMap);

    int saveUser(User user);
}
