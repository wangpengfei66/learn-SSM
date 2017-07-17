package com.kaishengit.entity;

public class Result {

	private String state;
	private String message;
	private Object data;
	//当出现错误时调用此构造方法
	public Result(String state,String message){
		this.state = state;
		this.message = message;
	}
	//当返回正常调用此构造
	public Result(String state){
		this.state = state;
	}
	//正常时调用，返回正常的状态和json数据
	public Result(String state,Object data){
		this.data = data;
		this.state = state;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
