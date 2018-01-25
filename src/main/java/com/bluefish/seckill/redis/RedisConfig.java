package com.bluefish.seckill.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Redis服务器相应配置
 *
 * @author bluefish
 */
@Component
@ConfigurationProperties(prefix = "redis")
public class RedisConfig {

    /**
     * redis服务器地址
     */
    private String host;
    /**
     * redis端口
     */
    private int port;
    /**
     * redis连接超时时间
     */
    private int timeout;
    /**
     * redis密码
     */
    private String password;
    /**
     * redis连接池最大连接数
     */
    private int poolMaxTotal;
    /**
     * redis连接池最大空闲数
     */
    private int poolMaxIdle;
    /**
     * redis连接池最大等待时间
     */
    private int poolMaxWait;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPoolMaxTotal() {
        return poolMaxTotal;
    }

    public void setPoolMaxTotal(int poolMaxTotal) {
        this.poolMaxTotal = poolMaxTotal;
    }

    public int getPoolMaxIdle() {
        return poolMaxIdle;
    }

    public void setPoolMaxIdle(int poolMaxIdle) {
        this.poolMaxIdle = poolMaxIdle;
    }

    public int getPoolMaxWait() {
        return poolMaxWait;
    }

    public void setPoolMaxWait(int poolMaxWait) {
        this.poolMaxWait = poolMaxWait;
    }
}
