package com.kaishengit.crm.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Task implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 待办事项名称
     */
    private String taskName;

    /**
     * 需完成的时间
     */
    private String completeTime;

    /**
     * 提醒时间
     */
    private String reminderTime;

    /**
     * 是否完成 0未完成 1完成
     */
    private Boolean done;

    /**
     * 关联机会id
     */
    private Integer saleId;

    /**
     * 关联的客户id
     */
    private Integer customerId;

    /**
     * 雇员id
     */
    private Integer accountId;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * customer
     */
    private Customer customer;
    /**
     * saleChance
     */
    private SaleChance saleChance;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public SaleChance getSaleChance() {
        return saleChance;
    }

    public void setSaleChance(SaleChance saleChance) {
        this.saleChance = saleChance;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(String completeTime) {
        this.completeTime = completeTime;
    }

    public String getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(String reminderTime) {
        this.reminderTime = reminderTime;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}