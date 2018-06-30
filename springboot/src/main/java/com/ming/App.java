package com.ming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author ming_he
 * @date 2018/6/23 21:29
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }
}