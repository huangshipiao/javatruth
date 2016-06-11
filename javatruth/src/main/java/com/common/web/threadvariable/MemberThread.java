package com.common.web.threadvariable;

import com.javatruth.entity.User;


/**
 * 前台用户登录信息存放
 * @author hsp
 *
 */
public class MemberThread {
	private static ThreadLocal<User> instance = new ThreadLocal<User>();

	public static User get() {
		return instance.get();
	}

	public static void set(User member) {
		instance.set(member);
	}

	public static void remove() {
		instance.remove();
	}
}
