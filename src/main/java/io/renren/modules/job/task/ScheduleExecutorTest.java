package io.renren.modules.job.task;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author lanxinghua
 * @date 2018/08/07 12:45
 * @description
 */
public class ScheduleExecutorTest implements Runnable {

    private String jobName="";

    public ScheduleExecutorTest(String jobName) {
        this.jobName = jobName;
    }

    @Override
    public void run() {
        System.out.println("executor "+jobName+Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        ScheduleExecutorTest job1 = new ScheduleExecutorTest("job1");
        service.scheduleAtFixedRate(job1,1,1,TimeUnit.SECONDS);
        service.scheduleAtFixedRate(job1,2,1,TimeUnit.SECONDS);
    }
}