package com.ming.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author ming_he
 * @date 2019/7/18 19:37
 */
@Configuration
public class DBConfig {

    @Bean
    @QuartzDataSource
    public DataSource quartzDatasource() {
        return DruidDataSourceBuilder.create().build();
    }
}
