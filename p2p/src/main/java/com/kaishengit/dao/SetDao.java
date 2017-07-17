package com.kaishengit.dao;

import org.apache.commons.dbutils.handlers.BeanHandler;

import com.kaishengit.entity.Setting;
import com.kaishengit.util.DbHelp;

public class SetDao {

	public void update(double interestRate, double commissionRate,int id) {
		String sql = "update t_setting set key1 = ?,value1 = ? where id = ?";
		DbHelp.executeUpdate(sql, interestRate,commissionRate,id);
	}

	public Setting findById(int id) {
		String sql = "select * from t_setting where id = ?";
		return DbHelp.executeQuery(sql, new BeanHandler<>(Setting.class), id);
	}

}
