package com.kaishengit.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		user.setAddress("北京");
		user.setUserName("jiangkun");
		user.setPassword("009999");
		userMapper.save(user);
		sqlSession.commit();
		
		System.out.println(user.getId());
	}
	@Test
	public void findAddLoadDept() {
		List<User> userList = userMapper.findAllLoadDept();
		for(User user : userList) {
			logger.debug("{}-->{}",user.getUserName(),user.getDept().getDeptName());
		}
	}
	
	@Test
	public void findByUserNameAndPassword() {
		
		User user = userMapper.findByUserNameAndPassword("jack", "000000"); 
		System.out.println(user.getId());
	} 
	
	@Test
	public void findByMapParams() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userName", "jack");
		params.put("pwd", "000000");
		
		User user = userMapper.findByMapParams(params); 
		System.out.println(user.getId());
	} 
	@Test
	public void searchByNameAndAddress() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "没有");
		params.put("address", "北京");
		
		List<User> userList = userMapper.searchByNameAndAddress(params); 
		for (User user : userList) {
			System.out.println(user.getId());
		}
	}
	@Test
	public void delByIds() {
		List<Integer> idList = Arrays.asList(7,8);
		
		userMapper.delByIds(idList);
		sqlSession.commit();
	}
	@Test
	public void batchsave() {
		List<User> userList = Arrays.asList(new User("jaxs","伦敦", "989999"),
				new User("rose", "东北", "888888"));
		userMapper.batchsave(userList);
		sqlSession.commit();
	}
	
	@Test
	public void firstLevelCache() {
		User user = userMapper.findById(1);
		User user1 = userMapper.findById(1);
		System.out.println(user.getUserName());
		System.out.println(user1.getUserName());
	}
	@Test
	public void secLevelCache() {
		SqlSession sqlSession1 = MyBatisUtil.getSqlSession();
		UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
		User user1 = userMapper1.findById(1);
		System.out.println(user1.getUserName());
		sqlSession1.close();
		
		SqlSession sqlSession2 = MyBatisUtil.getSqlSession();
		UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
		User user2 = userMapper2.findById(1);
		System.out.println(user2.getUserName());
		sqlSession2.close();
		
		
	}
	
}
