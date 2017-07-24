package com.kaishengit.crm.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class SaleChance implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 销售机会名称
     */
    private String name;

    /**
     * 客户id
     */
    private Integer custId;

    /**
     * 销售机会价值
     */
    private Float worth;

    /**
     * 销售进度
     */
    private String progress;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后跟进时间
     */
    private Date lastTime;

    /**
     * 雇员id
     */
    private Integer accountId;

    /**
     * 销售机会内容
     */
    private String content;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    private Customer customer;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public Float getWorth() {
        return worth;
    }

    public void setWorth(Float worth) {
        this.worth = worth;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}