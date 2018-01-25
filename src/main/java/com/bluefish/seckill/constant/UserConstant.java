package com.bluefish.seckill.constant;

/**
 * 用户模块常量类
 *
 * @author bluefish 2018/1/21
 * @version 1.0.0
 */
public interface UserConstant {

    /**
     * token
     */
    String COOKIE_USER_TOKEN = "token";

    /**
     * redis中用户过期时间
     */
    int USER_SESSION_EXPIRE_SECONDS = 2 * 24 * 3600;
}
