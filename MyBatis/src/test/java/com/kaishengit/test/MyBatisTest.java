package com.kaishengit.test;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kaishengit.entity.User;
import com.kaishengit.util.MyBatisUtil;



public class MyBatisTest {
	
	Logger logger = LoggerFactory.getLogger(MyBatisTest.class);
	@Test
	public void select() throws Exception{
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		//4.操作数据库
		User user = sqlSession.selectOne("com.kaishengit.mapper.UserMapper.findById",1);
		System.out.println(user);
		//5. 关闭sqlSession
		sqlSession.close();	
	}
	@Test
	public void findAll() throws Exception{
		//3.创建sqlSession
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		//4.操作数据库
		List<User> userList = sqlSession.selectList("com.kaishengit.mapper.UserMapper.findAll");
		for(User user : userList) {
			logger.debug("{}",user);
		}
		//5. 关闭sqlSession
		sqlSession.close();	
	}
	@Test
	public void save() throws Exception{
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		//4.操作数据库
		User user = new User();
		user.setUserName("大卫");
		user.setAddress("焦作");
		user.setPassword("000999");
		sqlSession.insert("com.kaishengit.mapper.UserMapper.save",user);
		sqlSession.commit();
		//5. 关闭sqlSession
		sqlSession.close();	
	}
	@Test
	public void update() throws Exception{
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		//4.操作数据库
		User user = sqlSession.selectOne("com.kaishengit.mapper.UserMapper.findById",1);
		user.setUserName("jack");
		user.setAddress("美国");
		user.setPassword("000000");
		sqlSession.update("com.kaishengit.mapper.UserMapper.update",user);
		sqlSession.commit();
		//5. 关闭sqlSession
		sqlSession.close();	
	}
	@Test
	public void delById() throws Exception{
		//1.从class中读取mybatis主配置文件
		Reader reader = Resources.getResourceAsReader("mybatis.xml");
		//2.根据sqlSessionFactoryBuiler构建SQLFactory对象
		SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
		SqlSessionFactory sqlSessionFacory = sqlSessionFactoryBuilder.build(reader);
		//3.创建sqlSession
		SqlSession sqlSession = sqlSessionFacory.openSession();
		//4.操作数据库
		sqlSession.delete("com.kaishengit.mapper.UserMapper.delById",3);
		//执行
		sqlSession.commit();
		//5. 关闭sqlSession
		sqlSession.close();	
	}
	
	
}
