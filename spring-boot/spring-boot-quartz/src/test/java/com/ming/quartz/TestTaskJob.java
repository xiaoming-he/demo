package com.ming.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author ming_he
 * @date 2019/7/19 20:48
 */
public class TestTaskJob implements Job {
    private static final Logger logger = LoggerFactory.getLogger(TestTaskJob.class);
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String localDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        logger.info("TestTaskJob-->" + localDateTime);
    }
}
