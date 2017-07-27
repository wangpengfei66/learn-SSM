package com.kaishengit.test;

import com.kaishengit.quartz.jobs.MyJob;
import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;

public class QuartzTest {

    @Test
    public void sendMessage() throws SchedulerException, IOException {
        //自定义数据
        JobDataMap dataMap = new JobDataMap();
        dataMap.put("to","tom");
        dataMap.put("message","打电话给Alex");
        //准备要执行的任务
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class).setJobData(dataMap).build();
        //定义触发器
        SimpleScheduleBuilder ssb = SimpleScheduleBuilder.simpleSchedule();
        //定义触发器规则
        ssb.withIntervalInSeconds(3);
        ssb.repeatForever();
        Trigger trigger = TriggerBuilder.newTrigger().withSchedule(ssb).build();

        //调度器
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.start();
    }
    @Test
    public void cornTrigger() throws SchedulerException, IOException {
        //自定义数据
        JobDataMap dataMap = new JobDataMap();
        dataMap.put("to","tom");
        dataMap.put("message","打电话给Alex");
        //准备要执行的任务
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class).setJobData(dataMap).build();
        //定义Cron触发器
        CronScheduleBuilder ssb = CronScheduleBuilder.cronSchedule("0/5 * * * * ? *");
        Trigger cronTrigger = TriggerBuilder.newTrigger().withSchedule(ssb).build();
        //定义触发器规则
        //调度器
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.scheduleJob(jobDetail,cronTrigger);
        scheduler.start();
    }


}
