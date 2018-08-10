package com.dingtalk;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTask;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * @author lanxinghua
 * @date 2018/08/06 19:27
 * @description
 */
public class ScheduleUtil {
    private static ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
    private static Map<String, ScheduledFuture<?>> scheduledFutureMap = new HashMap<>();

    static {
        threadPoolTaskScheduler.initialize();
        System.out.println("定时任务线程池启动");
    }

    /**
     * 启动定时任务
     * @param task
     * @param time
     */
    public static void start(Task task, Date time){
        ScheduledFuture<?> schedule = threadPoolTaskScheduler.schedule(task, time);
        scheduledFutureMap.put(task.getId(),schedule);
    }

    /**
     * 取消某定时任务
     * @param task
     */
    public static void cancel(Task task){
        ScheduledFuture<?> scheduledFuture = scheduledFutureMap.get(task.getId());
        if (scheduledFuture !=null && !scheduledFuture.isCancelled()){
            scheduledFuture.cancel(false);
        }
        scheduledFutureMap.remove(task.getId());
        System.out.println("取消定时任务"+task.getId());
    }

    public static void reset(Task task,Date date){
        ScheduledFuture<?> scheduledFuture = scheduledFutureMap.get(task.getId());
        //先取消定时任务
        cancel(task);
        //然后启动新的定时任务
        start(task,date);
        System.out.println("修改成功");
    }




}