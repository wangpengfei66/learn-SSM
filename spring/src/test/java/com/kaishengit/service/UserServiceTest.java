package com.kaishengit.service;

import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/7/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserServiceTest {

    @Autowired
    private UserDao userDao;
    @Test
    @Transactional(readOnly = true)//只读事务比正常事务性能要高一些
    public void findById() throws Exception {
        User user = userDao.findById(1);
        System.out.println(user);
    }

    @Test
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void save() throws Exception {
        User user = new User();
        user.setUser_name("nanjing");
        user.setAddress("大海");
        user.setPassword("000899");
        user.setDept_id(1);
        userDao.save(user);
        if(true) {
            throw new RuntimeException("我故意的。。");
        }
        userDao.save(user);
    }

}