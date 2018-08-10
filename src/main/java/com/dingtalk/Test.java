package com.dingtalk;

import java.util.Date;

import static com.dingtalk.DateUtil.addSeconds;
import static com.dingtalk.DateUtil.printDate;

/**
 * @author lanxinghua
 * @date 2018/08/06 19:37
 * @description
 */
public class Test {
    public static void main(String[] args) {
        Date day = new Date();
        System.out.println(printDate(day));
        //启动任务1
        ScheduleUtil.start(new Task("task1"),addSeconds(new Date(),1));
        //启动任务2
        ScheduleUtil.start(new Task("task2"),addSeconds(new Date(),2));
        //启动任务3
        ScheduleUtil.start(new Task("task3"),addSeconds(new Date(),3));
        //取消任务
        ScheduleUtil.cancel(new Task("task1"));
        //修改任务
        ScheduleUtil.reset(new Task("task1"),addSeconds(new Date(),4));
    }
}