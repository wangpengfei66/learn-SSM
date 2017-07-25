package com.kaishengit.crm.serviceImpl;

import com.kaishengit.crm.entity.Task;
import com.kaishengit.crm.entity.TaskExample;
import com.kaishengit.crm.mapper.TaskMapper;
import com.kaishengit.crm.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskMapper taskMapper;
    /**
     * 新增待办事项
     * @param task
     * @param id
     */
    @Override
    public void saveNewTask(Task task, Integer id) {
        task.setCreateTime(new Date());
        task.setAccountId(id);
        task.setDone(false);
        taskMapper.insert(task);
    }
    /**
     * 新增待办事项
     * @param task
     */
    @Override
    public void saveNewTask(Task task) {
        task.setCreateTime(new Date());
        task.setDone(false);
        taskMapper.insert(task);
    }


    /**
     * 查找雇员对应的待办事项传到前端
     * @param accountId
     * @param custId
     * @return
     */
    @Override
    public List<Task> findTaskByAccountIdAndCustomerId(Integer accountId, Integer custId) {
        TaskExample taskExample = new TaskExample();
        taskExample.createCriteria().andAccountIdEqualTo(accountId).andCustomerIdEqualTo(custId);
        return taskMapper.selectByExample(taskExample);
    }

    /**
     * 根据accountId和saleid查找task
     * @param accountId
     * @param saleId
     * @return
     */
    @Override
    public List<Task> findTaskByAccountIdAndSaleId(Integer accountId, Integer saleId) {
        TaskExample taskExample = new TaskExample();
        taskExample.createCriteria().andAccountIdEqualTo(accountId).andSaleIdEqualTo(saleId);
        return taskMapper.selectByExample(taskExample);
    }
}
