package com.kaishengit.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.google.common.collect.Lists;
import com.kaishengit.entity.Project;
import com.kaishengit.util.DbHelp;
import com.kaishengit.util.StringUtils;

public class ProjectDao {

	public void addProject(String projectName, String projectNo, double money, String rate, int month, double restMoney, String signDate,
			String endDate,int state) {
		String sql = "insert into t_project (project_no,project_name,rate,project_money,rest_money,month,start_time,end_time,state)values(?,?,?,?,?,?,?,?,?)";
		DbHelp.executeUpdate(sql, projectNo,projectName,rate,money,restMoney,month,signDate,endDate,state);
	}

	public Project findByProjectName(String projectName) {
		String sql = "select * from t_project where project_name = ?";
		return DbHelp.executeQuery(sql, new BeanHandler<>(Project.class), projectName);
	}

	public Project findByProjectNo(String projectNo) {
		String sql = "select * from t_project where project_no = ?";
		return DbHelp.executeQuery(sql, new BeanHandler<>(Project.class), projectNo);
	}

	public int countProject() {
		String sql = "select count(*) from t_project";
		return DbHelp.executeQuery(sql, new ScalarHandler<Long>()).intValue();
	}
	
	public List<Project> findByParams(Map<String, Object> maps) {
		String sql = "select * from t_project";
		String key = (maps.get("key") == null ? "" : String.valueOf(maps.get("key")));
		String value = (maps.get("value") == null ? "" : String.valueOf(maps.get("value")));
		String where = "";
		List<Object> lists = Lists.newArrayList();
		if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
			where += " where " + key + " =?";
			lists.add(value);
		}
		where += " limit ?,?";
		lists.add(maps.get("pageNoDb"));
		lists.add(maps.get("pageSize"));
		sql += where;
		return DbHelp.executeQuery(sql, new BeanListHandler<>(Project.class,new BasicRowProcessor(new GenerousBeanProcessor())), lists.toArray());
	}
	/**
	 * 找出所有可用的项目，state为1为可用，2为到期，
	 * @return
	 */
	public List<Project> findAll() {
		String sql = "select * from t_project where state = ?";
		return DbHelp.executeQuery(sql, new BeanListHandler<>(Project.class,new BasicRowProcessor(new GenerousBeanProcessor())),1);
	}

	public Project findById(String id) {
		String sql = "select * from t_project where id = ?";
		return DbHelp.executeQuery(sql, new BeanHandler<>(Project.class,new BasicRowProcessor(new GenerousBeanProcessor())), id);
	}

	public void updatRrestMoneyByProId(double restMoney, int proId) {
		String sql = "update t_project set rest_money = ? where id = ?";
		DbHelp.executeUpdate(sql, restMoney,proId);
	}
	/**
	 * 只更新了两个属性，按说应该全部更新
	 * @param pro
	 */
	public void updatRrestMoneyByProId(Project pro) {
		String sql = "update t_project set rest_money = ? where id = ?";
		DbHelp.executeUpdate(sql, pro.getRestMoney(),pro.getId());
	}

	
	
	
}
