package com.kaishengit.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.google.common.collect.Lists;
import com.kaishengit.entity.Customer;
import com.kaishengit.entity.Project;
import com.kaishengit.util.DbHelp;
import com.kaishengit.util.StringUtils;

public class CustomerDao {

	public void addCustomer(String name, String tel, String idNo, String bankNo, String bankName, String birthday,
			String address, String remark) {
		String sql = "insert into t_customer (name,tel,idNo,bankno,bankname,birthday,address,remark)values(?,?,?,?,?,?,?,?)";
		DbHelp.executeUpdate(sql, name,tel,idNo,bankNo,bankName,birthday,address,remark);
	}

	public List<Customer> findAll() {
		String sql = "select * from t_customer";
		return DbHelp.executeQuery(sql, new BeanListHandler<>(Customer.class));
	}

	public Customer findById(int custId) {
		String sql = "select * from t_customer where id = ?";
		return DbHelp.executeQuery(sql, new BeanHandler<>(Customer.class), custId);
	}

	public void updatePoint(Customer cust) {
		String sql = "update t_customer set point = ? where id = ?";
		DbHelp.executeUpdate(sql, cust.getPoint(),cust.getId());
	}

	public int count() {
		String sql = "select count(*) from t_customer";
		return DbHelp.executeQuery(sql, new ScalarHandler<Long>()).intValue();
	}

	public List<Customer> findByParams(Map<String, Object> maps) {
		String sql = "select * from t_customer";
		String key = (maps.get("key") == null ? "" : String.valueOf(maps.get("key")));
		String value = (maps.get("value") == null ? "" : String.valueOf(maps.get("value")));
		String where = "";
		List<Object> lists = Lists.newArrayList();
		if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
			where += " where " + key + " =?";
			lists.add(value);
		}
		where += " limit ?,?";
		lists.add(maps.get("pageNoDb"));
		lists.add(maps.get("pageSize"));
		sql += where;
		return DbHelp.executeQuery(sql, new BeanListHandler<>(Customer.class,new BasicRowProcessor(new GenerousBeanProcessor())), lists.toArray());
	}

	public List<Customer> findBirthByParams(Map<String, Object> maps) {
		String sql = "select * from t_customer where (DAYOFYEAR(birthday) - DAYOFYEAR(NOW()) BETWEEN 0 AND ?)"+
				"or (DAYOFYEAR(birthday) + DAYOFYEAR(CONCAT(YEAR(NOW()),'-12-31')) - DAYOFYEAR(NOW()) < ?) order by birthday limit ?,?";
		
		return DbHelp.executeQuery(sql, new BeanListHandler<>(Customer.class), maps.get("birth"),maps.get("birth"),maps.get("pageNoDb"),maps.get("pageSize"));
	}

	public int countBirth(Map<String, Object> maps) {
		String sql = "select count(*) from t_customer where (DAYOFYEAR(birthday) - DAYOFYEAR(NOW()) BETWEEN 0 AND ?)"+
				"or (DAYOFYEAR(birthday) + DAYOFYEAR(CONCAT(YEAR(NOW()),'-12-31')) - DAYOFYEAR(NOW()) < ?)";
		return DbHelp.executeQuery(sql, new ScalarHandler<Long>(), maps.get("birth"),maps.get("birth")).intValue();
	}

	public Customer findById(String id) {
		String sql = "select * from t_customer where id = ?";
		return DbHelp.executeQuery(sql, new BeanHandler<>(Customer.class), id);
	}

	public void editCustomer(String tel, String bankNo, String bankName, String address, String remark,int id) {
		String sql = "update t_customer set tel = ?,bankno = ?,bankname=?,address = ?,remark=? where id = ?";
		DbHelp.executeUpdate(sql, tel,bankNo,bankName,address,remark,id);
	}

	public void delCustById(String id) {
		String sql = "delet t_customer where id = ?";
		DbHelp.executeUpdate(sql, id);
	}

	
}
