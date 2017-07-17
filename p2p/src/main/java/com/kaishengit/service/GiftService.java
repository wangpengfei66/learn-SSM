package com.kaishengit.service;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;

import com.kaishengit.dao.GiftDao;
import com.kaishengit.entity.Gift;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.DbHelp;
import com.kaishengit.util.Page;

public class GiftService {

	GiftDao giftDao = new GiftDao();
	
	public void saveGift(String giftName, String num, String point, String price, String remark) {
		Gift gift = new Gift();
		gift.setGiftName(giftName);
		gift.setNum(Integer.valueOf(num));
		gift.setNeedPoint(Integer.valueOf(point));
		gift.setPrice(Double.valueOf(price));
		gift.setRemark(remark);
		
		giftDao.save(gift);
	}

	public Page<Gift> getPage(String key, String value, int currentPageNo) {
		
		int totalNo = giftDao.countGift(key,value);
		
		Page<Gift> page = new Page<>(currentPageNo, totalNo);
		List<Gift> giftList = giftDao.findByParams(key,value,page.getPageNoDb(),page.getPageSize());
		page.setItems(giftList);
		return page;
	}

	public List<Gift> findAll() {
		return giftDao.findAllGift();
	}

	public Gift findById(String giftId) {
		String sql = "select * from t_gift where id = ?";
		return DbHelp.executeQuery(sql, new BeanHandler<>(Gift.class), giftId);
	}

	public void update(int giftId) {
		Gift gift = giftDao.findById(giftId);
		if(gift.getNum() >= 1) {
			gift.setNum(gift.getNum() - 1);
			giftDao.updateNum(gift);
		}else{
			throw new ServiceException("您所兑换的商品库存不足，请选择其他礼品");
		}
	}

}
