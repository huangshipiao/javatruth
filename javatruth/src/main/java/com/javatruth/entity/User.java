package com.javatruth.entity;

import com.javatruth.entity.base.BaseUser;

public class User extends BaseUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 前台用户类型 0
	 */
	public static final Integer FRONT_USER_TYPE = 1;
	/**
	 * 后台用户类型 1
	 */
	public static final Integer SYS_USER_TYPE = 1;
	
	 /**
     * 失效
     */
    public static final int STUTAS_FAIL = 0;
    
    /**
     * 正常
     */
    public static final int STUTAS_NORMAL = 1;
    
    /**
     * 锁定
     */
    public static final int STUTAS_LOCK = 2;
    
    /**
     * 待审核
     */
    public static final int STUTAS_REVIEW = 3;
    
    /**
     * 黑名单
     */
    public static final int STUTAS_BLACKLIST = 4;
}