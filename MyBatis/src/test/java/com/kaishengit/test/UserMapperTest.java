package com.kaishengit.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kaishengit.entity.User;
import com.kaishengit.mapper.UserMapper;
import com.kaishengit.util.MyBatisUtil;

public class UserMapperTest {
	
	private Logger logger = LoggerFactory.getLogger(UserMapperTest.class);
	private SqlSession sqlSession;
	private UserMapper userMapper;
	@Before
	public void before() {
		sqlSession = MyBatisUtil.getSqlSession();
		//MyBatis根据定义的mapper接口动态的生成改接口的实现类
		//从而产生了接口指向实现类
		//动态代理模式
		userMapper = sqlSession.getMapper(UserMapper.class);
	}
	@After
	public void after() {
		sqlSession.close();
	}
	
	@Test
	public void findById() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		//MyBatis根据定义的mapper接口动态的生成改接口的实现类
		//从而产生了接口指向实现类
		//动态代理模式
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = userMapper.findById(1);
		logger.debug("{}",user);
		sqlSession.close();
	}
	@Test
	public void save() {
		User user = new User();
		user.setAddress("焦作");
		user.setUserName("大饼");
		user.setPassword("009900");
		userMapper.save(user);
		sqlSession.commit();
	}
	@Test
	public void findAddLoadDept() {
		List<User> userList = userMapper.findAllLoadDept();
		for(User user : userList) {
			logger.debug("{}-->{}",user.getUserName(),user.getDept().getDeptName());
		}
	}
	
}
