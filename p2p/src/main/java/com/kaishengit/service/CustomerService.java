package com.kaishengit.service;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.kaishengit.dao.CustomerDao;
import com.kaishengit.entity.Customer;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Page;

public class CustomerService {
	InvestService investService = new InvestService();
	CustomerDao custDao = new CustomerDao();
	public void addCustomer(String name, String tel, String idNo, String bankNo, String bankName, String birthday,
			String address, String remark) {
		custDao.addCustomer(name,tel,idNo,bankNo,bankName,birthday,address,remark);
	}
	
	public List<Customer> findAllCust() {
		return custDao.findAll();
	}

	public Page<Customer> findPage(String key, String value, int currentPageNo) {
		
		Map<String, Object> maps = Maps.newHashMap();
		maps.put("key", key);
		maps.put("value", value);
		int totalNo = custDao.count();
		Page<Customer> page = new Page<>(currentPageNo, totalNo);
		maps.put("pageNoDb", page.getPageNoDb());
		maps.put("pageSize", page.getPageSize());
		List<Customer> custList = custDao.findByParams(maps);
		page.setItems(custList);
		return page;
	}

	public Page<Customer> findPage(String birth, int currentPageNo) {
		Map<String, Object> maps = Maps.newHashMap();
		maps.put("birth", birth);
		int totalNo = custDao.countBirth(maps);
		Page<Customer> page = new Page<>(currentPageNo, totalNo);
		maps.put("pageNoDb", page.getPageNoDb());
		maps.put("pageSize", page.getPageSize());
		List<Customer> custList = custDao.findBirthByParams(maps);
		page.setItems(custList);
		return page;
	}

	public Customer findById(String id) {
		return custDao.findById(id);
	}

	public void updateCustomer(String tel, String bankNo, String bankName, String address, String remark,int id) {
		custDao.editCustomer(tel,bankNo,bankName,address,remark,id);
	}

	public void delCust(String id) {
		int count = investService.count(Integer.parseInt(id));
		if(count > 0) {
			throw new ServiceException("客户还有投资，不能删除");
		}else{
			custDao.delCustById(id);
		}
	}

	public void updatePoint(int point, int custId) {
		Customer cust = custDao.findById(custId);
		cust.setPoint(cust.getPoint() - point);
		custDao.updatePoint(cust);
	}

	
	
	
}
