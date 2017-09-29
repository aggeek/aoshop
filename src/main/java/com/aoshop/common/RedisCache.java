package com.aoshop.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import sun.awt.SunHints;

/**
 * author:liuao
 * description:
 * Date: create on 21:09 2017/9/15
 * modify by:
 */
public class RedisCache {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final JedisPool jedisPool;
    public RedisCache(String ip, int port) {
        jedisPool = new JedisPool(ip, port);
    }

    public String getKey(String key) {
        String value =null;
        //redis操作
        try {

            Jedis jedis = jedisPool.getResource();
            try {

                    if(key!=null){
                        value = jedis.get(key);
                        return value;
                    }



            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return value;
    }

}
