package com.bluefish.seckill.redis.prefix;

/**
 * Redis 中 key的前缀
 *
 * @author bluefish 2018/1/14
 * @version 1.0.0
 */
public interface KeyPrefix {

    /**
     * 过期时间,如果为0或者负数,表示永不过期
     *
     * @return
     */
    int expireSeconds();

    /**
     * 获取前缀
     *
     * @return
     */
    String getPrefix();

}
