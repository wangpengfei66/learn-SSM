package com.kaishengit.crm.service;

import com.kaishengit.crm.entity.Task;

import java.util.List;

public interface TaskService {
    void saveNewTask(Task task, Integer id);

    void saveNewTask(Task task);

    List<Task> findTaskByAccountIdAndCustomerId(Integer accountId, Integer custId);

    List<Task> findTaskByAccountIdAndSaleId(Integer accountId, Integer saleId);

    List<Task> findTaskByAccountId(Integer id, boolean showAll);

    Task findById(Integer id);

    void delById(Integer id);

    void updateById(Task task);
}
