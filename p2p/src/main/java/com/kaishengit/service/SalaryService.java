package com.kaishengit.service;

import java.util.List;

import com.kaishengit.dao.SalaryDao;
import com.kaishengit.entity.Employee;
import com.kaishengit.entity.SalarySum;
import com.kaishengit.util.Page;

public class SalaryService {
	
	SalaryDao salDao = new SalaryDao();
	public Page<SalarySum> getPage(String key, String value, int currentPageNo) {
		
		int count = salDao.count(key,value);
		Page<SalarySum> page = new Page<>(currentPageNo, count);
		List<SalarySum> salList = salDao.findByParams(key,value,page.getPageNoDb(),page.getPageSize());
		page.setItems(salList);
		return page;
	}
	
	/**
	 * 获取薪水流水，没有结算的薪水流水
	 * @param key
	 * @param value
	 * @param currentPageNo
	 * @return
	 */
	public Page<Employee> getSalaryPage(String key, String value, int currentPageNo) {
		
		int count = salDao.countSalary(key,value);
		Page<Employee> page = new Page<>(currentPageNo, count);
		List<Employee> salList = salDao.findSalaryByParams(key,value,page.getPageNoDb(),page.getPageSize());
		page.setItems(salList);
		return page;
		
	}

	
}
