package com.kaishengit.quartz.jobs;


import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SpringJob implements Job{

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap dataMap = jobExecutionContext.getMergedJobDataMap();
        String message = dataMap.getString("message");
        System.out.println("Hello,Spring+quartz~~~~~~~" + message);
    }
}
