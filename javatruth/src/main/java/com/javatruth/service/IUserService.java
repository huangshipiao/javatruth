package com.javatruth.service;

import com.common.persistence.page.Page;
import com.javatruth.entity.User;

public interface IUserService {
	int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    Page<User> fingPage(Page<User> page, User user);
}
