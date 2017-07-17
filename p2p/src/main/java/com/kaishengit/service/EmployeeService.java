package com.kaishengit.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;
import com.kaishengit.dao.CompanyDao;
import com.kaishengit.dao.EmployeeDao;
import com.kaishengit.entity.Company;
import com.kaishengit.entity.Employee;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Config;
import com.kaishengit.util.Page;

public class EmployeeService{

	EmployeeDao employeeDao = new EmployeeDao();
	CompanyDao companyDao = new CompanyDao();
	public void employeeService(String realName,String idNo,String tel,String companyId,String deptName) 
	throws ServiceException{
		//逻辑处理 不能存在手机号码一样的情况
		Employee employee = employeeDao.findByTel(tel);
		if(employee == null) {
			//封装对象
			employee = new Employee();
			employee.setRealName(realName);
			employee.setIdNo(idNo);
			employee.setTel(tel);
			employee.setCompanyId(Integer.parseInt(companyId));
			employee.setDeptName(deptName);
			employee.setPassword(DigestUtils.md5Hex("000000"+ Config.get("password.salt") ));
			
			//调用Dao保存
			employeeDao.save(employee);
		} else {
			throw new ServiceException("电话号码重复异常");
		}
		
	}

	public List<Employee> findAllEmployee() {
		List<Employee> empList = employeeDao.findAllEmployee();
		return empList;
	}
/**
 * 根据id删除，如果找不到，抛出异常
 * @param id
 */
	public void delEmployeeById(int id) throws ServiceException{
		Employee emp = employeeDao.findById(id);
		if(emp == null) {
			throw new ServiceException("参数异常");
		}else{
			employeeDao.deleteEmployeeById(id);
		}
	}
/**
 * 修改对象
 * @param priviligeName 
 * @param tel 
 * @param realName 
 * @param parseInt
 */
	public void editEmployeeById(String id, String state, String company,String deptName) {
		Employee emp = employeeDao.findById(Integer.parseInt(id));
		if(emp != null) {
			emp.setId(Integer.parseInt(id));
			emp.setState(Integer.parseInt(state));
			emp.setCompanyId(Integer.parseInt(company));
			emp.setDeptName(deptName);
			employeeDao.edit(emp);
		}else{
			throw new ServiceException("系统异常");
		}
	}
	/**
	 * ͨ通过id查找对象
	 * @param parseInt
	 * @return
	 */
	public Employee findById(int id) {
		return employeeDao.findById(id);
	}

	
	public Employee login(String tel, String password, String ip) {
		Employee emp = employeeDao.findByTel(tel);
		
		password = DigestUtils.md5Hex(password + Config.get("password.salt"));
		
		if(emp != null && emp.getPassword().equals(password)) {
			emp.setLastLoginIp(ip);
			emp.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
			employeeDao.edit(emp);
			return emp;
		
		}else{
			throw new ServiceException("账号或密码错误");
		}
		
	}
	
	public Page<Employee> findEmployeePage(String key, String value, int currentPageNo) {
		if(StringUtils.isNotEmpty(key) && key.equals("company_id")){
			Company com = companyDao.findByName(value);
			value = String.valueOf(com.getId()); 
		}
		Map<String,Object> maps = Maps.newHashMap();
		maps.put("key", key);
		maps.put("value", value);
		
		int totalNo = employeeDao.countEmployee(maps);
		Page<Employee> page = new Page<>(currentPageNo,totalNo);
		maps.put("pageNoDb", page.getPageNoDb());
		maps.put("pageSize", page.getPageSize());
		List<Employee> empList = employeeDao.findByParams(maps);
		page.setItems(empList);
		return page;
	}

	public boolean validateTel(String tel) {
		Employee emp = employeeDao.findByTel(tel);
		if(emp == null){
			return true;
		}
		return false;
	}

	public boolean validateIdNo(String idNo) {
		Employee emp = employeeDao.findByIdNo(idNo);
		if(emp == null){
			return true;
		}
		return false;
	}

	
}
