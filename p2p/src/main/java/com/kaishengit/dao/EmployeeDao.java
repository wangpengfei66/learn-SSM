package com.kaishengit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.handlers.AbstractListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.kaishengit.entity.Company;
import com.kaishengit.entity.Employee;
import com.kaishengit.util.DbHelp;

public class EmployeeDao {

	public void save(Employee employee) {
		String sql = "insert into t_employee (realname,idNo,tel,password,company_id,dept_name,state) values (?,?,?,?,?,?,?)";
		DbHelp.executeUpdate(sql, employee.getRealName(),employee.getIdNo(),employee.getTel(),employee.getPassword(),employee.getCompanyId(),employee.getDeptName(),employee.getState());
	}
	
	public Employee findByTel(String tel) {
		String sql = "select * from t_employee where tel = ?";
		return DbHelp.executeQuery(sql, new BeanHandler<>(Employee.class,new BasicRowProcessor(new GenerousBeanProcessor())), tel);
	}

	public List<Employee> findAllEmployee() {
		String sql = "select * from t_employee";
		return DbHelp.executeQuery(sql, new BeanListHandler<>(Employee.class,new BasicRowProcessor(new GenerousBeanProcessor())));
	}

	public Employee findById(int id) {
		String sql = "select * from t_employee where id = ?";
		return DbHelp.executeQuery(sql, new BeanHandler<>(Employee.class,new BasicRowProcessor(new GenerousBeanProcessor())), id);
	}

	public void deleteEmployeeById(int id) {
		String sql = "delete from t_employee where id = ?";
		DbHelp.executeUpdate(sql, id);
	}

	public void edit(Employee emp) {
		String sql = "update t_employee set realname = ?,idNo=?,tel = ?,company_id = ?,dept_name = ?,state = ?,last_login_time = ?,last_login_ip=? where id = ?";
		DbHelp.executeUpdate(sql, emp.getRealName(),emp.getIdNo(),emp.getTel(),emp.getCompanyId(),emp.getDeptName(),emp.getState(),emp.getLastLoginTime(),emp.getLastLoginIp(),emp.getId());
	}

	public List<Employee> findByParams(Map<String, Object> maps) {
		String sql = "select e.*,c.name companyName from t_employee e left join t_company c on e.company_id = c.id";
		List<Object> lists = Lists.newArrayList();
		String where = "";
		String key = (maps.get("key") == null ? "" : String.valueOf(maps.get("key")));
		String value = (maps.get("value") == null ? "" : String.valueOf(maps.get("value")));
		if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
			where += " where " + key + " = ?";
			lists.add(value);
		}
		where += " limit ?,?";
		lists.add(maps.get("pageNoDb"));
		lists.add(maps.get("pageSize"));
		sql += where;
		return DbHelp.executeQuery(sql, new AbstractListHandler<Employee>() {
			
			@Override
			protected Employee handleRow(ResultSet rs) throws SQLException {
				Employee emp = new GenerousBeanProcessor().toBean(rs, Employee.class);
				Company com = new Company();
				com.setName(rs.getString("companyName"));
				emp.setCompany(com);
				return emp;
			}
		},lists.toArray());
	}

	public int countEmployee(Map<String, Object> maps) {
		String sql = "select count(*) from t_employee";
		String where = "";
		List<Object> lists = Lists.newArrayList(); 
		String key = (maps.get("key") == null ? "" : String.valueOf(maps.get("key")));
		String value = (maps.get("value") == null ? "" : String.valueOf(maps.get("value")));
		
		if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
			where += " where " + key + " = ?";
			lists.add(value);
		}
		/*if(maps.get("key") !=null && maps.get("value") != null) {
			String key = String.valueOf(maps.get("key"));
			where += " where " + key + " = ?";//这样可能会导致500异常
			lists.add(maps.get("value"));
		}*/
		sql += where;
		return DbHelp.executeQuery(sql, new ScalarHandler<Long>(),lists.toArray()).intValue();
	}

	public Employee findByIdNo(String idNo) {
		String sql = "select * from t_employee where idNo = ?";
		return DbHelp.executeQuery(sql, new BeanHandler<>(Employee.class), idNo);
	}


}
