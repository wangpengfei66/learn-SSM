package com.kaishengit.dao;

import com.kaishengit.Application;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * Created by Administrator on 2017/7/7.
 */
public class UserDaoTest {
    @Test
    public void save(){
        //创建Spring容器
        //ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Application.class);
        //从Spring容器中获取bean
        UserDao userDao = (UserDao) applicationContext.getBean("userDao");
        userDao.save();

        applicationContext.close();
    }

}
