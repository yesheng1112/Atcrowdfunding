package com.ys.atcrowdfunding.manager.service.impl;

import com.ys.atcrowdfunding.bean.User;
import com.ys.atcrowdfunding.exception.LoginFailException;
import com.ys.atcrowdfunding.manager.dao.UserMapper;
import com.ys.atcrowdfunding.manager.service.UserService;
import com.ys.atcrowdfunding.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User queryUserLogin(Map<String, Object> paramMap) {
        User user = userMapper.queryUserlogin(paramMap);
        if (user == null){
            throw new LoginFailException("用户账号或密码不正确！");
        }
        return user;
    }


    @Override
    public Page<User> queryPage(Map<String ,Object> paramMap) {
        Page<User> page = new Page<>((Integer) paramMap.get("pageno"),(Integer) paramMap.get("pagesize"));

        Integer startIndex = page.getStartIndex();
        paramMap.put("startIndex",startIndex);

        List<User> datas = userMapper.queryList(paramMap);

        page.setDatas(datas);

        Integer totalsize = userMapper.queryCount(paramMap);

        page.setTotalsize(totalsize);

        return page;
    }

    @Override
    public int saveUser(User user) {
        return userMapper.insert(user);
    }
}
