package com.ming.quartz;

import com.ming.job.TestTaskJob2;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author ming_he
 * @date 2019/7/19 20:25
 */
public class QuartzSimpleDemo {

    public static void main(String[] args) throws SchedulerException {
        //1. 创建Scheduler
        SchedulerFactory sfact = new StdSchedulerFactory();
        Scheduler sched  = sfact.getScheduler();

        //2. 创建job信息
        JobDetail testTaskJob = JobBuilder.newJob(TestTaskJob.class).storeDurably()
                .withIdentity("TestTaskJob").build();
        //3. 创建触发器
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("*/5 * * * * ?");
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .forJob(testTaskJob)
                .withIdentity("testTaskJob")
                .withSchedule(cronScheduleBuilder)
                .build();
        //4. 启动
        sched.scheduleJob(testTaskJob, cronTrigger);
        sched.start();
    }
}
