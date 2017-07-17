package com.kaishengit.dao;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.google.common.collect.Lists;
import com.kaishengit.entity.Interest;
import com.kaishengit.util.DbHelp;
import com.kaishengit.util.StringUtils;

public class InterestDao {

	public void save(Interest interest) {
		String sql = "insert into t_interest (custid,investid,empid,interestsendday,interestmoney,state) values (?,?,?,?,?,?)";
		DbHelp.executeUpdate(sql, interest.getCustId(),interest.getInvestId(),interest.getEmpId(),interest.getInterestSendday(),interest.getInterestMoney(),2);
	}

	public int countByInvestIdAndState(int investId, int interestStateHadSend) {
		String sql = "select count(*) from t_interest where investid = ? and state = ?";
		return DbHelp.executeQuery(sql, new ScalarHandler<Long>(), investId,interestStateHadSend).intValue();
	}

	public void delByInvestId(int investId) {
		String sql = "delete from t_interest where investid = ?";
		DbHelp.executeUpdate(sql, investId);
	}

	public void delByInvestIdAndState(int investId, int interestStateNotDate) {
		String sql = "delet from t_interest where investid = ? and state = ?";
		DbHelp.executeUpdate(sql, investId,interestStateNotDate);
	}

	public int countInterest(String key, String value) {
		String sql = "select count(*) FROM `t_interest` inte,t_invest inv,t_customer cust,t_employee emp,t_project pro WHERE inte.custid=cust.id and inte.empid=emp.id AND inte.investid = inv.id AND inv.proid = pro.id";
		List<Object> lists = Lists.newArrayList();
		if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
			sql += " and "+ key +" =?";
			lists.add(value);
		}
		
		return DbHelp.executeQuery(sql, new ScalarHandler<Long>(), lists.toArray()).intValue();
	}

	public List<Interest> getByParams(String key, String value, int pageNoDb, int pageSize) {
		String sql = "SELECT cust.`name`,pro.projectname,inv.investmoney,emp.realname,inte.* FROM `t_interest` inte,t_invest inv,t_customer cust,t_employee emp,t_project pro WHERE inte.custid=cust.id and inte.empid=emp.id AND inte.investid = inv.id AND inv.proid = pro.id";
		List<Object> lists = Lists.newArrayList();
		if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
			sql += " and "+ key +" =?";
			lists.add(value);
		}
		lists.add(pageNoDb);
		lists.add(pageSize);
		sql += " limit ?,?";
		return DbHelp.executeQuery(sql, new BeanListHandler<>(Interest.class), lists.toArray());
	}

}
