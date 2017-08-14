package com.kaishengit.pojo;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import redis.clients.jedis.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

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
    @Test
    public void jedisCluster() throws IOException {
        Set<HostAndPort> hostAndPortSet = new HashSet<HostAndPort>();
        hostAndPortSet.add(new HostAndPort("192.168.209.128",6000));
        hostAndPortSet.add(new HostAndPort("192.168.209.128",6001));
        hostAndPortSet.add(new HostAndPort("192.168.209.128",6002));
        hostAndPortSet.add(new HostAndPort("192.168.209.128",6003));
        hostAndPortSet.add(new HostAndPort("192.168.209.128",6004));
        hostAndPortSet.add(new HostAndPort("192.168.209.128",6005));

        JedisCluster jedisCluster = new JedisCluster(hostAndPortSet,1000,1000);
        jedisCluster.set("jedis","rose");
        jedisCluster.close();
    }

}
