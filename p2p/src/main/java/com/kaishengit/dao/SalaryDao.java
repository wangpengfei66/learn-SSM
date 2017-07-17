package com.kaishengit.dao;

import java.util.List;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.google.common.collect.Lists;
import com.kaishengit.entity.Employee;
import com.kaishengit.entity.SalarySum;
import com.kaishengit.entity.Setting;
import com.kaishengit.util.DbHelp;
import com.kaishengit.util.StringUtils;

public class SalaryDao {
	/**
	 * 得出数据条数
	 * @param key
	 * @param value
	 * @return
	 */
	public int count(String key, String value) {
		String sql = "SELECT COUNT(*) FROM(SELECT SUM(commission),empid,DATE_FORMAT(createtime,'%y-%m') FROM `t_sal` GROUP BY empid,DATE_FORMAT(createtime,'%y-%m')";
		List<Object> lists = Lists.newArrayList();
		if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
			value = " %" + value + "% ";
			sql += "HAVING empid IN (SELECT id FROM t_employee WHERE "+ key +" LIKE ?)";
			lists.add(value);
		}
		sql += " ) sumsal";
		return DbHelp.executeQuery(sql, new ScalarHandler<Long>(),lists.toArray()).intValue();
	}

	public List<SalarySum> findByParams(String key, String value, int pageNoDb, int pageSize) {
		String sql = "SELECT emp.realname,emp.tel,sumsal.sal,sumsal.date FROM t_employee emp,(SELECT SUM(commission) sal,empid,DATE_FORMAT(createtime,'%y-%m') date FROM `t_sal` GROUP BY empid,DATE_FORMAT(createtime,'%y-%m')";
		List<Object> lists = Lists.newArrayList();
		if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
			value = "%" + value + "%";
			sql += " HAVING empid IN (SELECT id FROM t_employee WHERE "+ key +" LIKE ?)";
			lists.add(value);
		}
		sql +=") sumsal WHERE sumsal.empid = emp.id";
		lists.add(pageNoDb);
		lists.add(pageSize);
		sql += " limit ?,?";
		//TODO 查询不到数据
		return DbHelp.executeQuery(sql, new BeanListHandler<>(SalarySum.class,new BasicRowProcessor(new GenerousBeanProcessor())), lists.toArray());
	}
	/**
	 * 获得薪水流水个数
	 * @param key
	 * @param value
	 * @return
	 */
	public int countSalary(String key, String value) {
		int value1 = Setting.LAST_ID;
		String sql = "SELECT COUNT(*) FROM `t_sal` sal,t_setting s,t_customer cust,t_employee emp,t_invest inv WHERE sal.empid = emp.id AND sal.investid = inv.id and inv.custid=cust.id AND s.id=?";
		List<Object> lists = Lists.newArrayList();
		lists.add(value1);
		if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
			sql += " where "+ key +" =?";
			lists.add(value);
		}
		return DbHelp.executeQuery(sql, new ScalarHandler<Long>(),lists.toArray()).intValue();
	}
	/**
	 * 获取薪水流水的列表
	 * @param key
	 * @param value
	 * @param pageNoDb
	 * @param pageSize
	 * @return
	 */
	public List<Employee> findSalaryByParams(String key, String value, int pageNoDb, int pageSize) {
		int value1 = Setting.LAST_ID;
		String sql = "SELECT emp.id,emp.realname,cust.`name`,inv.investmoney,s.value1,sal.commission,inv.createtime FROM `t_sal` sal,t_setting s,t_customer cust,t_employee emp,t_invest inv WHERE sal.empid = emp.id AND sal.investid = inv.id and inv.custid=cust.id AND s.id=?";
		List<Object> lists = Lists.newArrayList();
		lists.add(value1);
		if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
			value = "%" + value + "%";
			sql += " HAVING empid IN (SELECT id FROM t_employee WHERE "+ key +" LIKE ?)";
			lists.add(value);
		}
		lists.add(pageNoDb);
		lists.add(pageSize);
		sql += " limit ?,?";
		//TODO 查询不到数据
		return DbHelp.executeQuery(sql, new BeanListHandler<>(Employee.class,new BasicRowProcessor(new GenerousBeanProcessor())), lists.toArray());
	}

	
	
}
