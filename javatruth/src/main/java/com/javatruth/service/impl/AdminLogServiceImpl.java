package com.javatruth.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.javatruth.dao.IAdminLogDao;
import com.javatruth.entity.AdminLog;
import com.javatruth.service.IAdminLogService;
@Service("adminLogService")
public class AdminLogServiceImpl implements IAdminLogService {
	@Resource
	private IAdminLogDao adminLogDao;
	public int deleteByPrimaryKey(Long logId) {
		return adminLogDao.deleteByPrimaryKey(logId);
	}

	public int insert(AdminLog record) {
		return adminLogDao.insert(record);
	}

	public int insertSelective(AdminLog record) {
		return adminLogDao.insertSelective(record);
	}

	public AdminLog selectByPrimaryKey(Long logId) {
		return adminLogDao.selectByPrimaryKey(logId);
	}

	public int updateByPrimaryKeySelective(AdminLog record) {
		return adminLogDao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKeyWithBLOBs(AdminLog record) {
		return adminLogDao.updateByPrimaryKeyWithBLOBs(record);
	}

	public int updateByPrimaryKey(AdminLog record) {
		return adminLogDao.updateByPrimaryKey(record);
	}

}
