package com.javatruth.dao;

import java.util.List;

import com.javatruth.entity.User;

public interface IUserDao {
	int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	List<User> findList(User user);
}
