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
	
	/**
     * 通过用户名查找管理员用户
     * @param username 用户名
     * @return
     */
	User findBySysUserName(String userName);
	/**
	 * 通过用户名或邮箱或手机号查找用户信息
	 * @param username
	 * @return
	 */
	User findByUserName(String username);
	
	
}
