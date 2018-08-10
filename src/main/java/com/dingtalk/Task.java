package com.dingtalk;

import java.lang.annotation.Target;
import java.util.Date;

import static com.dingtalk.DateUtil.printDate;

/**
 * @author lanxinghua
 * @date 2018/08/06 19:33
 * @description
 */
public class Task implements Runnable {
    private String id;//任务唯一标识码

    public Task() {
    }

    public Task(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public void run() {
        System.out.println("执行...."+getId()+"..."+printDate(new Date()));
    }
}