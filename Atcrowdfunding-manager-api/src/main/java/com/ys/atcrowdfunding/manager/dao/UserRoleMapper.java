package com.ys.atcrowdfunding.manager.dao;

import com.ys.atcrowdfunding.bean.UserRole;
import java.util.List;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRole record);

    UserRole selectByPrimaryKey(Integer id);

    List<UserRole> selectAll();

    int updateByPrimaryKey(UserRole record);
}