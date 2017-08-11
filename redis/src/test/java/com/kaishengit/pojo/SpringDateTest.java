package com.kaishengit.pojo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-springdate.xml")
public class SpringDateTest {

    private RedisTemplate<String,User> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, User> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.redisTemplate.setKeySerializer(new StringRedisSerializer());
        this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<User>(User.class));
    }

    @Test
    public void saveUserToRedis() {
        User user = new User();
        user.setUserName("蒋思文");
        user.setId(1);
        user.setAddress("北京");
        redisTemplate.opsForValue().set("user:1",user);
    }
    @Test
    public void getUser() {
        User user = redisTemplate.opsForValue().get("user:1");
        System.out.println(user.getUserName());
        System.out.println(user.getId());
        System.out.println(user.getAddress());
    }

   /* @Test
    public void springDataTest() {
        redisTemplate.opsForValue().set("name","kaishengit");
    }

    @Test
    public void get() {
        String name = redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }*/
}
