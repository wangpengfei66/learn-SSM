package com.kaishengit.service;

import java.util.List;

import com.kaishengit.dao.InterestDao;
import com.kaishengit.entity.Interest;
import com.kaishengit.util.Page;

public class InterestService {

	InterestDao inDao = new InterestDao();
	
	public Page<Interest> getPage(String key, String value, int currentPageNo) {
		int totalNo = inDao.countInterest(key,value);
		
		Page<Interest> page = new Page<>(currentPageNo, totalNo);
		List<Interest> list = inDao.getByParams(key,value,page.getPageNoDb(),page.getPageSize());
		page.setItems(list);
		return page;
	}

}
