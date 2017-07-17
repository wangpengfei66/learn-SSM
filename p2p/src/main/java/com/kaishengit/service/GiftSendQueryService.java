package com.kaishengit.service;

import java.util.List;

import com.kaishengit.dao.GiftSendQueryDao;
import com.kaishengit.entity.GiftSend;
import com.kaishengit.util.Page;

public class GiftSendQueryService {
	GiftSendQueryDao giftSendQuerydao = new GiftSendQueryDao();
	public void add(int custId, int giftId, int empId) {
		giftSendQuerydao.add(custId,giftId,empId);
	}

	
	public Page<GiftSend> getPage(String key, String value, int currentPageNo) {
		
		int totalNo = giftSendQuerydao.count(key,value);
		
		Page<GiftSend> page = new Page<>(currentPageNo, totalNo);
		List<GiftSend> lists = giftSendQuerydao.getByParams(key,value,page.getPageNoDb(),page.getPageSize());
		page.setItems(lists);
		return page;
	}

}
