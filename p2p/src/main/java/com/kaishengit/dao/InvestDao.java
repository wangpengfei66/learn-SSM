package com.kaishengit.dao;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.google.common.collect.Lists;
import com.kaishengit.entity.Invest;
import com.kaishengit.util.DbHelp;
import com.kaishengit.util.StringUtils;

public class InvestDao {

	public int saveInvest(Invest invest) {
		String sql = "insert into t_invest (proId,custId,empId,rate,investmoney,month,signdate,enddate,state) values (?,?,?,?,?,?,?,?,?)";
		return DbHelp.insert(sql, new ScalarHandler<Long>(), invest.getProId(),invest.getCustId(),invest.getEmpId(),invest.getRate(),invest.getInvestMoney(),invest.getMonth(),invest.getSignDate(),invest.getEndDate(),invest.getState()).intValue();
	}

	public int countInvest(String key, String value) {
		if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
			//模糊查询的拼装
			value = "%" + value + "%";
			String sql = "select count(*) from t_invest inv,t_customer cust,t_project pro,t_employee emp where inv.proid = pro.id and inv.empid = emp.id and inv.custid = cust.id and " + key + " like ?";
			return DbHelp.executeQuery(sql, new ScalarHandler<Long>(),value).intValue();
		}else{
			String sql = "select count(*) from t_invest";
			return DbHelp.executeQuery(sql, new ScalarHandler<Long>()).intValue();
		}
	}

	public List<Invest> findByParams(String key, String value, int pageNoDb, int pageSize) {
		String sql = "select inv.*,realname,name,projectname from t_invest inv,t_customer cust,t_project pro,t_employee emp where inv.proid = pro.id and inv.empid = emp.id and inv.custid = cust.id";
		List<Object> lists = Lists.newArrayList();
		if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
			value = "%" + value + "%";
			sql += " and " + key + " like ?";
			lists.add(value);
		}
		
		sql += " limit ?,?";
		lists.add(pageNoDb);
		lists.add(pageSize);
		return DbHelp.executeQuery(sql, new BeanListHandler<>(Invest.class),lists.toArray());
	}

	public Invest findById(int investId) {
		String sql = "select * from t_invest where id = ?";
		return DbHelp.executeQuery(sql, new BeanHandler<>(Invest.class), investId);
	}

	public void delByInvestId(int investId) {
		String sql = "delete from t_invest where id = ?";
		DbHelp.executeUpdate(sql, investId);
	}

	public void update(Invest invest) {
		String sql = "update t_invest set state = ? where id = ?";
		DbHelp.executeUpdate(sql, invest.getState(),invest.getId());
	}

	public int countCust(int custId) {
		String sql = "select count(*) from t_invest where custId = ?";
		return DbHelp.executeQuery(sql, new ScalarHandler<Long>(), custId).intValue();
	}


}
