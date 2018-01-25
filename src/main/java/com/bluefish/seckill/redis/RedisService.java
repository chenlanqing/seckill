package com.bluefish.seckill.redis;

import com.alibaba.fastjson.JSON;
import com.bluefish.seckill.redis.prefix.KeyPrefix;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Redis 操作类
 *
 * @author bluefish 2018/1/14
 * @version 1.0.0
 */
@Service
public class RedisService {

    @Autowired
    private JedisPool jedisPool;

    /**
     * 从redis中获取数据
     *
     * @param prefix 前缀
     * @param key    存储数据的key
     * @param clazz  需要转换的实体类
     * @param <T>
     * @return
     */
    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            // 生成真正的key
            String str = jedis.get(getRealKey(prefix, key));
            T t = strToBean(str, clazz);
            return t;
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 设置数据
     *
     * @param prefix 前缀,包含过期时间
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public <T> boolean set(KeyPrefix prefix, String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if (StringUtils.isBlank(str)) {
                return false;
            }
            String realKey = getRealKey(prefix, key);
            int seconds = prefix.expireSeconds();
            // 如果设置了过期时间,需要调用setex 方法处理
            if (seconds <= 0) {
                jedis.set(realKey, str);
            } else {
                jedis.setex(realKey, seconds, str);
            }
            return true;
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 判断一个key是否存在
     *
     * @param prefix
     * @param key
     * @return
     */
    public boolean exists(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(getRealKey(prefix, key));
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 增加值
     *
     * @param prefix
     * @param key
     * @return
     */
    public Long incre(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.incr(getRealKey(prefix, key));
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 减少值
     *
     * @param prefix
     * @param key
     * @return
     */
    public Long decr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.decr(getRealKey(prefix, key));
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 将redis连接退回到连接池
     *
     * @param jedis redis连接
     */
    private void returnToPool(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    private String getRealKey(KeyPrefix prefix, String key) {
        return prefix.getPrefix() + key;
    }

    /**
     * 将实体类转换为字符串
     *
     * @param value 实体类数据
     * @param <T>
     * @return
     */
    private <T> String beanToString(T value) {
        if (value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return value.toString();
        } else if (clazz == String.class) {
            return (String) value;
        } else if (clazz == long.class || clazz == Long.class) {
            return value.toString();
        } else {
            return JSON.toJSONString(value);
        }
    }

    /**
     * 将对应的字符串转为实体类
     *
     * @param str
     * @param clazz
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    private <T> T strToBean(String str, Class<T> clazz) {
        if (str == null || str.length() <= 0 || clazz == null) {
            return null;
        }
        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(str);
        } else if (clazz == String.class) {
            return (T) str;
        } else if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(str);
        } else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }

}
