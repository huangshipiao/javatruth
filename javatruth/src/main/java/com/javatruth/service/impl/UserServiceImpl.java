package com.javatruth.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.common.persistence.page.Page;
import com.javatruth.dao.IUserDao;
import com.javatruth.entity.User;
import com.javatruth.service.IUserService;
@Service("userService")
public class UserServiceImpl implements IUserService {
	
	@Resource
	private IUserDao userDao;
	
	public int deleteByPrimaryKey(Long id) {
		
		return userDao.deleteByPrimaryKey(id);
	}

	public int insert(User record) {
		
		return userDao.insert(record);
	}

	public int insertSelective(User record) {
		
		return userDao.insertSelective(record);
	}

	public User selectByPrimaryKey(Long id) {		
		return userDao.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(User record) {
		
		return userDao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(User record) {
		
		return userDao.updateByPrimaryKey(record);
	}

	public Page<User> fingPage(Page<User> page, User user) {
		// 设置分页参数
		user.setPage(page);
		// 执行分页查询
		page.setList(userDao.findList(user));
		return page;
	}

	public User findBySysUserName(String username) {		
		return userDao.findBySysUserName(username);
	}
	
}
