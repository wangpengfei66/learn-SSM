package com.kaishengit.crm.serviceImpl;

import static com.cronutils.model.field.expression.FieldExpressionFactory.*;

import com.cronutils.builder.CronBuilder;
import com.cronutils.model.Cron;
import com.cronutils.model.CronType;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.kaishengit.crm.entity.Task;
import com.kaishengit.crm.entity.TaskExample;
import com.kaishengit.crm.mapper.TaskMapper;
import com.kaishengit.crm.service.TaskService;
import com.kaishengit.crm.util.StringUtils;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.quartz.jobs.MyJob;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.joda.time.DateTime;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

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
    @Transactional
    public void saveNewTask(Task task) {
        task.setCreateTime(new Date());
        task.setDone(false);
        taskMapper.insert(task);
        //判断是否又提醒时间，如果有提醒时间，则应该添加动态定时任务
        if(StringUtils.isNotEmpty(task.getReminderTime())){
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("to",task.getAccountId());
            jobDataMap.put("message",task.getTaskName());
            //获取调度器
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            //jobDetail
            JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                    .withIdentity(new JobKey("account:"+task.getAccountId()+":"+task.getId(),"weixinGroup"))
                    .setJobData(jobDataMap).build();
            //字符串日期格式转换为JodaTime的DateTime类对象
            DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
            DateTime dateTime = formatter.parseDateTime(task.getReminderTime());
            //根据日期生成cron表达式
            Cron cron = CronBuilder.cron(CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ))
                    .withYear(on(dateTime.getYear()))
                    .withMonth(on(dateTime.getMonthOfYear()))
                    .withDoM(on(dateTime.getDayOfMonth()))
                    .withHour(on(dateTime.getHourOfDay()))
                    .withMinute(on(dateTime.getMinuteOfHour()))
                    .withSecond(on(dateTime.getSecondOfMinute()))
                    .withDoW(questionMark())
                    .instance();

            String cronExpression = cron.asString();
            System.out.print("表达式：" + cronExpression);

            //Trigger
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            Trigger trigger = TriggerBuilder.newTrigger().withSchedule(scheduleBuilder).build();

            try {
                scheduler.scheduleJob(jobDetail,trigger);
                scheduler.start();
            } catch (SchedulerException e) {
                throw new ServiceException("添加定时任务异常",e);
            }
        }
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

    /**
     * 根据accountid查找自己的task列表
     * @param id
     * @param showAll
     * @return
     */
    @Override
    public List<Task> findTaskByAccountId(Integer accountId, boolean showAll) {
        return taskMapper.findTaskByAccountId(accountId,showAll);
    }

    @Override
    public Task findById(Integer id) {
        return taskMapper.selectByPrimaryKey(id);
    }

    /**
     * 删除任务
     * @param id
     */
    @Override
    @Transactional
    public void delById(Integer id) {
        Task task = taskMapper.selectByPrimaryKey(id);
        taskMapper.deleteByPrimaryKey(id);
        //判断删除的Task是否有提醒时间，如果有则删除定时任务
        if(StringUtils.isNotEmpty(task.getReminderTime())) {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            try {
                scheduler.deleteJob(new JobKey("account:"+task.getAccountId() + ":" +task.getId(),"weixinGroup"));
            } catch (SchedulerException e) {
                throw new ServiceException("删除调度任务异常",e);
            }
        }
    }

    /**
     * 更新状态
     * @param task
     */
    @Override
    public void updateById(Task task) {
        taskMapper.updateByPrimaryKeySelective(task);
    }

}
