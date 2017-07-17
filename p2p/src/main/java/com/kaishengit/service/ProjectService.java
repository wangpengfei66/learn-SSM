package com.kaishengit.service;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.kaishengit.dao.ProjectDao;
import com.kaishengit.entity.Project;
import com.kaishengit.util.Page;

public class ProjectService {

	ProjectDao proDao = new ProjectDao();
	public void addProject(String projectName, String projectNo, double money, double restMoney, String rate, int month,
			String signDate, String endDate) {
		int state = 1;//状态初始值为1,表示项目正常，0为项目结束
		proDao.addProject(projectName,projectNo,money,rate,month,restMoney,signDate,endDate,state);
	}
	public boolean validateProjectName(String projectName) {
		Project pro = proDao.findByProjectName(projectName);
		if(pro == null) {
			return true;
		}
		return false;
	}
	public boolean validateProjectNo(String projectNo) {
		Project pro = proDao.findByProjectNo(projectNo);
		if(pro == null) {
			return true;
		}
		return false;
	}
	/**
	 * 封装对应的page对象并返回
	 * @param key
	 * @param value
	 * @param currentPageNo
	 * @return
	 */
	public Page<Project> findProjectPage(String key, String value, int currentPageNo) {
		Map<String, Object> maps = Maps.newHashMap();
		maps.put("key", key);
		maps.put("value", value);
		int totalNo = proDao.countProject();
		Page<Project> page = new Page<>(currentPageNo, totalNo);
		maps.put("pageNoDb", page.getPageNoDb());
		maps.put("pageSize", page.getPageSize());
		List<Project> proList = proDao.findByParams(maps);
		
		Project pro = proList.get(0);
		System.out.println(pro);
		page.setItems(proList);
		return page;
		
	}
	public List<Project> findAllPro() {
		return proDao.findAll();
	}
	
	public Project findById(String id) {
		return proDao.findById(id);
	}

	
	
	
}
