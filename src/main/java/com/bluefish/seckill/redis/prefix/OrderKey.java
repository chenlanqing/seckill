package com.bluefish.seckill.redis.prefix;

/**
 * @author bluefish 2018/1/14
 * @version 1.0.0
 */
public class OrderKey extends BasePrefix {

    public OrderKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
}
