package com.ming.quartz;

import org.quartz.JobListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author ming_he
 * @date 2019/7/10 00:40
 */
@Configuration
public class QuartzListenerConfiguration {

    @Bean
    public SchedulerFactoryBeanCustomizer listenerCustomizer() {
        return schedulerFactoryBean -> schedulerFactoryBean.setGlobalJobListeners(new QuartzJobLogListener());
    }
}
