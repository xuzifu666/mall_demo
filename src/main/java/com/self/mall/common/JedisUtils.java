package com.self.mall.common;

import com.self.mall.util.PropertiesUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtils {

    public static JedisPool jedisPool;

    private static int maxTotle = Integer.parseInt(PropertiesUtil.getProperty("redis.maxTotle"));

    private static int maxIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.maxIdle"));

    private static int minIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.minIdle"));

    /**
     * 确保获得连接时，该连接可用
     */
    private static boolean testOnBorrow = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.testOnBorrow"));

    /**
     * 确保归还连接时，该连接可用
     */
    private static boolean testOnReturn = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.testOnReturn"));

    public static void initialized(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(maxTotle);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setTestOnBorrow(testOnBorrow);
        poolConfig.setTestOnReturn(testOnReturn);
        poolConfig.setBlockWhenExhausted(true);
        jedisPool = new JedisPool(poolConfig,PropertiesUtil.getProperty("redis.host"),Integer.parseInt(PropertiesUtil.getProperty("redis.port")));
    }

    public static Jedis getRedisConnect(){
        return jedisPool.getResource();
    }


    public static void returnRedisConnect(){
        jedisPool.close();
    }

    static {
        initialized();
    }

//    public static void main(String[] args) {
//        Jedis jedis = jedisPool.getResource();
//        jedis.set("kk","123");
//        jedis.close();
//    }





}
