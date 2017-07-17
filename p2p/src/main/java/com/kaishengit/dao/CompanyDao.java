package com.kaishengit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.handlers.AbstractListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.kaishengit.entity.Company;
import com.kaishengit.util.DbHelp;

public class CompanyDao {

	public List<Company> findAll() {
		String sql = "select * from t_company";
		return DbHelp.executeQuery(sql, new BeanListHandler<>(Company.class));
	}

	public Company findByName(String value) {
		String sql = "select * from t_company where name = ?";
		return DbHelp.executeQuery(sql, new BeanHandler<>(Company.class), value);
	}

	public void save(String comName, String tel, String city,String address, String remark,String head) {
		String sql = "insert into t_company (name,city,address,tel,remark,head) values (?,?,?,?,?,?)";
		DbHelp.executeUpdate(sql, comName,tel,city,address,remark,head);
	}
	
	public int countCompany(Map<String, Object> maps) {
		String sql = "select count(*) from t_company";
		String where = "";
		List<String> lists = Lists.newArrayList();
		String key = (maps.get("key") == null ? "" : String.valueOf(maps.get("key")));
		String value = (maps.get("value") == null ? "" : String.valueOf(maps.get("value")));
		if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
			where += " where " + key + " =?";
			lists.add(value);
		}
		sql += where;
		return DbHelp.executeQuery(sql, new ScalarHandler<Long>(),lists.toArray()).intValue();
	}


	public List<Company> findByParams(int pageNoDb, int pageSize) {
		String sql = "select * from t_company limit ?,?";
		return DbHelp.executeQuery(sql, new BeanListHandler<>(Company.class), pageNoDb,pageSize);
	}

	public List<Company> findByParams(Map<String, Object> maps) {
		String sql = "select * from t_company";
		String where = "";
		List<Object> lists = Lists.newArrayList();
		String key = (maps.get("key") == null ? "" : String.valueOf(maps.get("key")));
		String value = (maps.get("value") == null ? "" : String.valueOf(maps.get("value")));
		if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
			where += " where " + key + " =?";
			lists.add(value);
		}
		where += " limit ?,?";
		lists.add(maps.get("pageNoDb"));
		lists.add(maps.get("pageSize"));
		sql += where;
		return DbHelp.executeQuery(sql, new BeanListHandler<>(Company.class), lists.toArray());
	}

	public Company findById(String id) {
		String sql = "select * from t_company where id = ?";
		return DbHelp.executeQuery(sql, new BeanHandler<>(Company.class), id);
	}

	public void editCompany(String id, String comName, String tel, String city, String address, String remark,
			String head) {
		String sql = "update t_company set name = ?,city = ?,address = ?,head = ?,tel = ?,remark = ? where id = ?";
		DbHelp.executeUpdate(sql, comName,city,address,head,tel,remark,id);
	}

	public void delById(int id) {
		String sql = "delete from t_company where id = ?";
		DbHelp.executeUpdate(sql, id);
	}

	public Company findById(int id) {
		String sql = "select * from t_company where id = ?";
		return DbHelp.executeQuery(sql, new BeanHandler<>(Company.class), id);
	}

}
