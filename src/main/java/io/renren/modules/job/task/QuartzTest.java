package io.renren.modules.job.task;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * @author lanxinghua
 * @date 2018/08/07 14:09
 * @description
 */
public class QuartzTest implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        System.out.println(jobDetail.getKey());
        System.out.println(jobDetail.getJobDataMap().get("type"));
    }


    public static void main(String[] args) throws SchedulerException {
        //创建一个Scheduler
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        //创建JobDetail，name,gruopname,类名
        JobDetail jobDetail = JobBuilder.newJob(QuartzTest.class)
                .withIdentity("task1")
                .requestRecovery()
                .build();
        jobDetail.getJobDataMap().put("type","FULL");
        //创建一个每周触发的Trigger
        SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger()
                .withIdentity("triggerName")
                .startAt(new Date())
                .build();
        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.start();
        TriggerKey triggerKey = TriggerKey.triggerKey("task1");
        System.out.println(triggerKey);
        scheduler.getTrigger(triggerKey);
    }
}