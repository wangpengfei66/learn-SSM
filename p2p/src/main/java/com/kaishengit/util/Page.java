package com.kaishengit.util;

import java.util.List;


public class Page<T> {
	//一页五条数据
	private int pageSize = 5;
	//当前页
	private int currentPageNo;
	//总数据条数
	private int totalNo;
	//总页数，也是最后一页
	private int lastPage;
	//数据库中SQL分页查询中的第一个参数
	private int pageNoDb;
	//查询出的list
	private List<T> items;

	public Page(int currentPageNo,int totalNo) {
		int lastPage = totalNo/pageSize;
		//如果数据量不整除，最后一页应+1
		if(totalNo % pageSize != 0) {
			lastPage ++;
		}
		
		
		//防止数据总量为0，那么totalNo就为0
		if(totalNo < 1) {
			totalNo = 1;
		}
		
		if(currentPageNo > lastPage) {
			currentPageNo = lastPage;
		}
		
		if(currentPageNo<1) {
			currentPageNo = 1; 
		}
		
		//lastPage = (lastPage == 0 ? 1 : lastPage);
		//pageNodb是数据库查询的第一个数，即从哪开始
		int pageNoDb = (currentPageNo - 1) * pageSize;
		//当前页码
		this.currentPageNo = currentPageNo;
		//总数据条数
		this.lastPage = lastPage;
		this.pageNoDb = pageNoDb;
		this.totalNo = totalNo;
	}
	
	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public int getTotalNo() {
		return totalNo;
	}

	public void setTotalNo(int totalNo) {
		this.totalNo = totalNo;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public int getPageNoDb() {
		return pageNoDb;
	}

	public void setPageNoDb(int pageNoDb) {
		this.pageNoDb = pageNoDb;
	}
	
	
}
