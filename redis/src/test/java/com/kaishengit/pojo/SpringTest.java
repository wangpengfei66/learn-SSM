package com.kaishengit.pojo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-rediscluster.xml")
public class SpringTest {

    /*@Autowired
    private JedisPool jedisPool;*/
    @Autowired
    private JedisCluster jedisCluster;

  /*  @Test
    public void setAndGetTest() {
        Jedis jedis = jedisPool.getResource();
        String name = jedis.get("name");
        System.out.println(name);
        jedis.close();
    }*/

    @Test
    public void setAndGetTest() throws IOException {
        jedisCluster.set("spring","hello");
        jedisCluster.close();
    }

}
