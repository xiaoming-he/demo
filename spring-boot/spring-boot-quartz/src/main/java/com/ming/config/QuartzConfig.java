package com.ming.config;

import com.ming.job.TestTaskJob1;
import com.ming.job.TestTaskJob2;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.TriggerKey.triggerKey;

/**
 * @author ming_he
 * @date 2019/6/22 2:31 PM
 */
//@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail testQuartz1() {
        return JobBuilder.newJob(TestTaskJob1.class).withIdentity("testTaskJob1").storeDurably().build();
    }

    @Bean
    public Trigger testQuartzTrigger1() {
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5)
                .withRepeatCount(10);
        return newTrigger()
                .forJob(testQuartz1())
                .withIdentity("testTaskJob1")
                .withSchedule(simpleScheduleBuilder)
                .build();
    }

    @Bean
    public JobDetail testQuartz2() {
        return JobBuilder.newJob(TestTaskJob2.class).storeDurably().withIdentity("TestTaskJob2").build();
    }

    @Bean
    public Trigger testQuartzTrigger2() {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("*/5 * * * * ?");
        return newTrigger()
                .forJob(testQuartz2())
                .withIdentity("testTaskJob2")
                .withSchedule(cronScheduleBuilder)
                .build();
    }

    public Trigger testQuartzTrigger3() {
        CalendarIntervalScheduleBuilder calendarIntervalScheduleBuilder = CalendarIntervalScheduleBuilder.calendarIntervalSchedule();
        return newTrigger()
                            .withIdentity(triggerKey("myTrigger", "myTriggerGroup"))
                            .withSchedule(calendarIntervalScheduleBuilder.withIntervalInDays(3))
                            .startAt(DateBuilder.futureDate(10, DateBuilder.IntervalUnit.MINUTE))
                            .build();
    }
}
