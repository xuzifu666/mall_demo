package com.self.mall.common;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

@Slf4j
public class RedisUtils {

    public static void set(String key,String value){
        Jedis redisConnect = null;
        try {
            redisConnect = JedisUtils.getRedisConnect();
            redisConnect.set(key, value);
        }catch (Exception e){
            log.error("redis set method key is {}  value is {} error",key,value,e);
            redisConnect.close();
        }
        redisConnect.close();

    }

    public static void expire(String key , int times){
        Jedis redisConnect = null;
        try {
            redisConnect = JedisUtils.getRedisConnect();
            redisConnect.expire(key,times);
        }catch (Exception e){
            log.error("redis set method key is {}  error",key,e);
            redisConnect.close();
        }
        redisConnect.close();
    }

    public static void setnx(String key,String value){
        Jedis redisConnect = null;
        try {
            redisConnect = JedisUtils.getRedisConnect();
            redisConnect.setnx(key,value);
        }catch (Exception e){
            log.error("redis setnx method key is {} value is {} error",key,value,e);
            redisConnect.close();
        }
        redisConnect.close();
    }

    public static String get(String key){
        Jedis redisConnect = null;
        String value = null;
        try {
            redisConnect = JedisUtils.getRedisConnect();
            value = redisConnect.get(key);
        }catch (Exception e){
            log.error("redis get method is error , key is {}",key,e);
            redisConnect.close();
            return null;
        }
        redisConnect.close();
        return value;
    }

    public static void del(String key){
        Jedis redisConnect = null;
        try {
            redisConnect = JedisUtils.getRedisConnect();
            redisConnect.del(key);
        }catch (Exception e){
            log.error("redis del is error , key is {}",key,e);
            redisConnect.close();
        }
        redisConnect.close();
    }


    public static void main(String[] args) {
        RedisUtils.del("kk");

    }

}
