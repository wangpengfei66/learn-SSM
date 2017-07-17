package com.kaishengit.dao;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.google.common.collect.Lists;
import com.kaishengit.entity.GiftSend;
import com.kaishengit.util.DbHelp;
import com.kaishengit.util.StringUtils;

public class GiftSendQueryDao {

	public void add(int custId, int giftId, int empId) {
		String sql = "insert into t_gift_cust (custid,giftid,empid)values(?,?,?)";
		DbHelp.executeUpdate(sql, custId,giftId,empId);
	}

	public int count(String key, String value) {
		String sql = "SELECT COUNT(*) FROM `t_gift_cust` gc,t_customer cust,t_employee emp,t_gift g WHERE gc.custid = cust.id AND gc.empid = emp.id AND gc.giftid = g.id";
		List<Object> lists = Lists.newArrayList();
		if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
			sql += " and "+ key +" =?";
			lists.add(value);
		}
		
		return DbHelp.executeQuery(sql, new ScalarHandler<Long>(), lists.toArray()).intValue();
	}

	public List<GiftSend> getByParams(String key, String value, int pageNoDb, int pageSize) {
		String sql = "SELECT custid,cust.name,giftname,needpoint,gc.createtime,realname FROM `t_gift_cust` gc,t_customer cust,t_employee emp,t_gift g WHERE gc.custid = cust.id AND gc.empid = emp.id AND gc.giftid = g.id";
		List<Object> lists = Lists.newArrayList();
		if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
			sql += " and "+ key +" =?";
			lists.add(value);
		}
		lists.add(pageNoDb);
		lists.add(pageSize);
		sql += " limit ?,?";
		return DbHelp.executeQuery(sql, new BeanListHandler<>(GiftSend.class), lists.toArray());
	}

}
