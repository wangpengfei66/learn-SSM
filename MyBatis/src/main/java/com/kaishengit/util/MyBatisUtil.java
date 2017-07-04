package com.kaishengit.util;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	/**
	 * 静态的对象永远只有一份 单例模式
	 */
	private static SqlSessionFactory sqlSessionFactory = builderSessionFactory();

	private static SqlSessionFactory builderSessionFactory() {
		try {
			//1.从class中读取mybatis主配置文件
			Reader reader = Resources.getResourceAsReader("mybatis.xml");
			//2.根据sqlSessionFactoryBuiler构建SQLFactory对象
			SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
			return sqlSessionFactoryBuilder.build(reader);
			
		} catch (IOException e) {
			throw new RuntimeException("读取MyBatis配置文件异常",e);
		}
	} 
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	
	
	public static SqlSession getSqlSession() {
		return getSqlSessionFactory().openSession();
	}
	
}
