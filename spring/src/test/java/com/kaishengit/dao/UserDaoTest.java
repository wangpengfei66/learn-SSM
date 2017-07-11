package com.kaishengit.dao;

import com.kaishengit.Application;
import com.kaishengit.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

/**
 * Created by Administrator on 2017/7/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserDaoTest {
    @Autowired
    private UserDao userDao;
    @Test
    public void save(){
        User user = new User();
        user.setUser_name("www");
        user.setAddress("焦作");
        user.setPassword("111000");
        user.setDept_id(1);
        userDao.save(user);
    }
    @Test
    public void delete() {
        userDao.delete(15);
    }

    @Test
    public void findById() {
        User user = userDao.findById(1);
        System.out.println(user);
    }
    @Test
    public void findAll(){
        List<User> userList = userDao.findAll();
        for(User user : userList) {
            System.out.println(user);
        }
    }
    @Test
    public void findByAddress() {
        User user = userDao.findByAddress("东北");
        Assert.assertNotNull(user);
    }
    @Test
    public void count() {
        int count = userDao.count();
        System.out.println(count);
    }
}
