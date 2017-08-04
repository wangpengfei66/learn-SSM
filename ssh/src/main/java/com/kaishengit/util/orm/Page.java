package com.kaishengit.util.orm;

import java.util.List;

public class Page<T> {
    //从第几条开始
    private Integer start;
    //总数据条数
    private Integer totalNum;
    //总页数
    private Integer totalPageSize;
    //每页大小
    private Integer pageSize;
    //当前页
    private Integer currentPageNum;
    //页中元素
    private List<T> items;

    public Page() {}

    //new后给出一切 除了list
    public Page(Integer pageSize,int totalNum,Integer currentPageNum) {
        this.totalNum = totalNum;
        //计算总页数
        totalPageSize = totalNum / pageSize;
        if(totalNum % pageSize != 0) {
            totalPageSize ++;
        }

        if(currentPageNum < 1) {
            currentPageNum = 1;
        }
        //当前页大于总页数时，调整当前页码
        if(currentPageNum > totalPageSize) {
            currentPageNum = totalPageSize;
        }
        if(totalPageSize == 0) {
            currentPageNum = 1;
        }
        //计算起始行数
        start = (currentPageNum - 1) * pageSize;
    }


    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getTotalPageSize() {
        return totalPageSize;
    }

    public void setTotalPageSize(Integer totalPageSize) {
        this.totalPageSize = totalPageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPageNum() {
        return currentPageNum;
    }

    public void setCurrentPageNum(Integer currentPageNum) {
        this.currentPageNum = currentPageNum;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
