package com.kaishengit.entity;

public class Setting {

	public static final int LAST_ID = 1;
	
	private int id;
	private double key1;
	private double value1;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getKey1() {
		return key1;
	}

	public void setKey1(double key1) {
		this.key1 = key1;
	}

	public double getValue1() {
		return value1;
	}

	public void setValue1(double value1) {
		this.value1 = value1;
	}

}
