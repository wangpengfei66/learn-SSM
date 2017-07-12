package com.kaishengit.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class UserServiceTest {
	@Autowired
	private UserService userService;
	@Test
	public void save() {
		User user = new User("质问","duzhuangcun","10101",2);
		userService.save(user);
	}
	@Test
	public void findByPage() {
		PageInfo<User> pageInfo = userService.findByPage(1, 5);
		List<User> userList = pageInfo.getList();
		for(User user : userList) {
			System.out.println(user);
		}
	}

	
}
