package com.javatruth.service;

import com.javatruth.entity.MobileVerify;

public interface IMobileVerifyService {
    int deleteByPrimaryKey(Integer id);

    int insert(MobileVerify record);

    int insertSelective(MobileVerify record);

    MobileVerify selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MobileVerify record);

    int updateByPrimaryKey(MobileVerify record);
}