package com.javatruth.service;

import com.javatruth.entity.AdminLog;

public interface IAdminLogService {
    int deleteByPrimaryKey(Long logId);

    int insert(AdminLog record);

    int insertSelective(AdminLog record);

    AdminLog selectByPrimaryKey(Long logId);

    int updateByPrimaryKeySelective(AdminLog record);

    int updateByPrimaryKeyWithBLOBs(AdminLog record);

    int updateByPrimaryKey(AdminLog record);
}