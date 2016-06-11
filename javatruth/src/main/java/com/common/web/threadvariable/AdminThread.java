package com.common.web.threadvariable;

import com.javatruth.entity.User;
/**
 * 后台登录用户信息存放
 * @author huangshipiao
 *
 */
public class AdminThread {

    private static ThreadLocal<User> instance = new ThreadLocal<User>();


    public static User get() {
        return instance.get();
    }

    public static void set(User group) {
        instance.set(group);
    }

    public static void remove() {
        instance.remove();
    }
}
