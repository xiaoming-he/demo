package com.ming.job;

import com.ming.annotations.QuartzScheduled;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author ming_he
 * @date 2019/6/22 2:11 PM
 */
@QuartzScheduled(cron = "*/5 * * * * ?")
public class TestTaskJob1 extends QuartzJobBean {
    private static final Logger logger = LoggerFactory.getLogger(TestTaskJob1.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String localDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        logger.info("TestTaskJob1-->" + localDateTime);

        try {
            Thread.sleep(70000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
