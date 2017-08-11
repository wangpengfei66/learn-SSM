package com.kaishengit.pojo;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisTest {
    @Test
    public void HelloRedis() {
        Jedis jedis = new Jedis("192.168.209.128",6379);
        jedis.set("name","kaishengit");
        String name = jedis.get("name");
        System.out.println(name);
        jedis.close();
    }
    @Test
    public void poolTest() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(10);
        config.setMinIdle(5);
        JedisPool jedisPool = new JedisPool(config,"192.168.209.128",6379);
        Jedis jedis = jedisPool.getResource();
        String name = jedis.get("name");
        System.out.println(name);
        jedisPool.destroy();
    }

}
