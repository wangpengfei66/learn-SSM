package com.kaishengit.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2017/7/8.
 */
public class UserServiceTest {
    @Test
    public void save(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");



       /* DiDemo diDemo = (DiDemo) applicationContext.getBean("diDemo");
        diDemo.show();*/

        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.save();

    }

}
