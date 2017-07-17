package com.kaishengit.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kaishengit.exception.DateAccessException;

public class DbHelp {
	
	private static Logger logger = LoggerFactory.getLogger(DbHelp.class);
	public static void executeUpdate(String sql,Object... params) {
		QueryRunner runner = new QueryRunner(ConnectionManager.getDataSource());
		try {
			runner.update(sql, params);
			logger.debug("SQL:{},params:{}",sql,params);
		} catch (SQLException e) {
			throw new DateAccessException("执行" + sql + "异常",e);
		}
		
		
	}
	public static <T> T executeQuery(String sql,ResultSetHandler<T> rsh,Object... params) throws DateAccessException{
		T t = null;
		QueryRunner runner = new QueryRunner(ConnectionManager.getDataSource());
		try {
			t = runner.query(sql, rsh, params);
			logger.debug("SQL:{},params:{}", sql,params);
		} catch (SQLException e) {
			throw new DateAccessException("执行" + sql + "异常",e);
		}
		return t;
	}
	
	
	

	private static void close(Connection conn) {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static <T> T insert(String sql, ResultSetHandler<T> rsh,Object... params) {
		QueryRunner runner = new QueryRunner(ConnectionManager.getDataSource());
		T t = null;
		try {
			t = runner.insert(sql, rsh, params);
			logger.debug("SQL:{},params:{}", sql,params);
		} catch (SQLException e) {
			throw new DateAccessException("执行" + sql + "异常",e);
		}
		return t;
	}
	
	
}
