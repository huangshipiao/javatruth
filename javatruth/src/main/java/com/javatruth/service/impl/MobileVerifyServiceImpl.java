package com.javatruth.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.javatruth.dao.IMobileVerifyDao;
import com.javatruth.entity.MobileVerify;
import com.javatruth.service.IMobileVerifyService;
@Service("mobileVerifyServce")
public class MobileVerifyServiceImpl implements IMobileVerifyService {
	@Resource
	private IMobileVerifyDao mobileVerifyDao;
	public int deleteByPrimaryKey(Integer id) {
		return mobileVerifyDao.deleteByPrimaryKey(id);
	}

	public int insert(MobileVerify record) {
		return mobileVerifyDao.insert(record);
	}

	public int insertSelective(MobileVerify record) {
		return mobileVerifyDao.insertSelective(record);
	}

	public MobileVerify selectByPrimaryKey(Integer id) {
		return mobileVerifyDao.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(MobileVerify record) {
		return mobileVerifyDao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(MobileVerify record) {
		return mobileVerifyDao.updateByPrimaryKey(record);
	}

}
