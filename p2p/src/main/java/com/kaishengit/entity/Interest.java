package com.kaishengit.entity;

public class Interest {
	//利息已领取
	public static final int INTEREST_STATE_HAD_SEND = 1;
	//利息未到期
	public static final int INTEREST_STATE_NOT_DATE = 2;
	//利息未发放
	public static final int INTEREST_STATE_NOT_SEND = 1;
	
	private Integer id;
	private Integer custId;
	private Integer investId;
	private Integer empId;
	private String interestSendday;
	private Double interestMoney;
	private Integer state;
	private String sendTime;
	private String name;
	private String projectName;
	private Double investMoney;
	private String realName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public Integer getInvestId() {
		return investId;
	}

	public void setInvestId(Integer investId) {
		this.investId = investId;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getInterestSendday() {
		return interestSendday;
	}

	public void setInterestSendday(String interestSendday) {
		this.interestSendday = interestSendday;
	}

	public Double getInterestMoney() {
		return interestMoney;
	}

	public void setInterestMoney(Double interestMoney) {
		this.interestMoney = interestMoney;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Double getInvestMoney() {
		return investMoney;
	}

	public void setInvestMoney(Double investMoney) {
		this.investMoney = investMoney;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	

}
