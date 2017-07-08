package com.kaishengit.dao;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2017/7/7.
 */
public class UserDaoTest {
    @Test
    public void save(){
        //创建Spring容器
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //从Spring容器中获取bean
//        UserDao userDao2 = (UserDao) applicationContext.getBean("userDao");
        UserDao userDao = (UserDao) applicationContext.getBean("userDao");
//        userDao2.save();
        userDao.save();
//        System.out.println(userDao == userDao2);

        applicationContext.close();
    }

}
