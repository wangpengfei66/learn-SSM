package com.kaishengit.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.kaishengit.dao.CompanyDao;
import com.kaishengit.dao.EmployeeDao;
import com.kaishengit.entity.Company;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Config;
import com.kaishengit.util.Page;

public class CompanyService {

	CompanyDao comDao = new CompanyDao();
	EmployeeDao empDao = new EmployeeDao();
	public List<Company> getCompanyList() {
		return comDao.findAll();
	}
	
	public List<String> getComCityList() {
		return Arrays.asList(Config.get("com.list").split(","));
	}

	public List<String> getDeptNameList() {
		return Arrays.asList(Config.get("dept.name.list").split("-"));
	}

	public void save(String comName, String tel, String city,String address, String remark,String head) {
		Company com = comDao.findByName(comName);
		if(com == null) {
			comDao.save(comName,tel,city,address,remark,head);
		}else{
			throw new ServiceException("公司名重复");
		}
	}

	public Page<Company> getCompanyPage(String key, String value, int currentPageNo) {
		Map<String, Object> maps = Maps.newHashMap();
		maps.put("key", key);
		maps.put("value", value);
		int totalNo = comDao.countCompany(maps);
		Page<Company> page = new Page<>(currentPageNo, totalNo);
		maps.put("pageNoDb", page.getPageNoDb());
		maps.put("pageSize", page.getPageSize());
		
		List<Company> comList = comDao.findByParams(maps);
		page.setItems(comList);
		return page;
		
	}
	/**
	 * 通过id进行公司信息修改
	 * @param id
	 * @param comName
	 * @param tel
	 * @param city
	 * @param address
	 * @param remark
	 * @param head
	 */
	public void editComById(String id, String comName, String tel, String city, String address, String remark,
			String head) {
		Company com = comDao.findById(id);
		if(com != null) {
			comDao.editCompany(id,comName,tel,city,address,remark,head);
		}else{
			throw new ServiceException("该公司不存在");
		}
	}
	
	/**
	 * 通过String的id找公司
	 * @param id
	 * @return
	 */
	public Company findById(String id) {
		return comDao.findById(id);
	}
	
	/**
	 * 通过id删除对应的公司，如果公司下边还有员工，不可删除
	 * @param id
	 */
	public void delById(int id) {
		Company com = comDao.findById(id);
		if(com != null) {
			Map<String,Object> maps = Maps.newHashMap();
			maps.put("key", "company_id");
			maps.put("value", com.getId());
			int count = empDao.countEmployee(maps);
			if(count == 0) {
				comDao.delById(id);
			}else{
				throw new ServiceException("公司下有员工，不能删除");
			}
			
		}else{
			throw new ServiceException("参数错误");
		}
	}

}
