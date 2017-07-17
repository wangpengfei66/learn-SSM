package com.kaishengit.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;


public class ConnectionManager {

	private static String DRIVER = null; //"com.mysql.jdbc.Driver";
	private static String URL = null;    //"jdbc:mysql:///mydb";
	private static String USER = null;    //"root";
	private static String PASSWORD = null;//"123456";
	static BasicDataSource dataSource = new BasicDataSource();

//	private static Data
	
	static{
		DRIVER = Config.get("jdbc.driver");
		URL = Config.get("jdbc.url");
		USER = Config.get("jdbc.username");
		PASSWORD = Config.get("jdbc.password");
		//设置属性
		dataSource.setDriverClassName(DRIVER);
		dataSource.setUrl(URL);
		dataSource.setUsername(USER);
		dataSource.setPassword(PASSWORD); 
		
		dataSource.setInitialSize(5);    //设置初始连接数
		dataSource.setMaxIdle(20); 		//设置高峰连接数
		dataSource.setMinIdle(5);		//设置最小连接数
		dataSource.setMaxWaitMillis(5000); //设置最长等待时间
	}
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static BasicDataSource getDataSource() {
		return dataSource;
	}
	
	
/*	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			throw new DateAccessException("���ݿ������쳣",e);
		} //����������ⶼ�����ӹص��ˣ����и�������
		return conn;
	}*/
	
}
