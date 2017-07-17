package com.kaishengit.dao;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.google.common.collect.Lists;
import com.kaishengit.entity.Gift;
import com.kaishengit.util.DbHelp;
import com.kaishengit.util.StringUtils;

public class GiftDao {

	public void save(Gift gift) {
		String sql = "insert into t_gift (giftname,price,needpoint,num,remark) values (?,?,?,?,?)";
		DbHelp.executeUpdate(sql, gift.getGiftName(),gift.getPrice(),gift.getNeedPoint(),gift.getNum(),gift.getRemark());
	}

	public int countGift(String key, String value) {
		if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
			String sql = "select count(*) from t_gift where " + key + " = " + value;
			return DbHelp.executeQuery(sql, new ScalarHandler<Long>(), key,value).intValue();
		}else{
			String sql = "select count(*) from t_gift";
			return DbHelp.executeQuery(sql, new ScalarHandler<Long>()).intValue();
		}
	}

	public List<Gift> findByParams(String key, String value, int pageNoDb, int pageSize) {
		String sql = "select * from t_gift";
		List<Object> lists = Lists.newArrayList();
		if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
			sql += " where "+ key +" =?";
			lists.add(value);
		}
		sql += " limit ?,?";
		lists.add(pageNoDb);
		lists.add(pageSize);
		
		return DbHelp.executeQuery(sql, new BeanListHandler<>(Gift.class), lists.toArray());
	}

	public List<Gift> findAllGift() {
		String sql = "select * from t_gift";
		return DbHelp.executeQuery(sql, new BeanListHandler<>(Gift.class));
	}

	public Gift findById(int giftId) {
		String sql = "select * from t_gift where id = ?";
		return DbHelp.executeQuery(sql, new BeanHandler<>(Gift.class), giftId);
	}

	public void updateNum(Gift gift) {
		String sql = "update t_gift set num = ? where id = ?";
		DbHelp.executeUpdate(sql, gift.getNum(),gift.getId());
	}
	
	

}
