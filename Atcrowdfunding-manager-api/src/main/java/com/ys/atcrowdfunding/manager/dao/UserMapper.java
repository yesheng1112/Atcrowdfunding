package com.ys.atcrowdfunding.manager.dao;

import com.ys.atcrowdfunding.bean.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

	User queryUserlogin(Map<String, Object> paramMap);

    List<User> queryList(Map<String,Object> paramMap);

    Integer queryCount(Map<String,Object> paramMap);
}