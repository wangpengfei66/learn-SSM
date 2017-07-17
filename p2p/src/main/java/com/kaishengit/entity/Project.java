package com.kaishengit.entity;

import java.sql.Date;

public class Project {
	private int id;
	private String projectName;
	private String projectNo;
	private double projectMoney;
	private double restMoney;
	private double rate;
	private int month;
	private Date signDate;
	private Date endDate;
	private int state = 1;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public double getProjectMoney() {
		return projectMoney;
	}

	public void setProjectMoney(double projectMoney) {
		this.projectMoney = projectMoney;
	}
	
	
	public double getRestMoney() {
		return restMoney;
	}

	public void setRestMoney(double restMoney) {
		this.restMoney = restMoney;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	

	public Date getSignDate() {
		return signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", projectName=" + projectName + ", projectNo=" + projectNo + ", projectMoney="
				+ projectMoney + ", rate=" + rate + ", month=" + month + ", signDate=" + signDate + ", endDate="
				+ endDate + ", state=" + state + "]";
	}
	

}
