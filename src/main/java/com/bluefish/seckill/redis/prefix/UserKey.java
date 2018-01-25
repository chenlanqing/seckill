package com.bluefish.seckill.redis.prefix;

import com.bluefish.seckill.constant.UserConstant;

/**
 * 用户模块生成redis的key
 *
 * @author bluefish 2018/1/14
 * @version 1.0.0
 */
public class UserKey extends BasePrefix {

    public UserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }


    public static final UserKey token = new UserKey(UserConstant.USER_SESSION_EXPIRE_SECONDS, "token");



}
