package com.ming;

import com.ming.annotations.EnableQuartzScheduled;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ming_he
 * @date 2019/6/21 5:33 PM
 */
@SpringBootApplication
@EnableQuartzScheduled(basePackage = "com.ming")
public class SpringBootQuartz {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootQuartz.class, args);
    }
}
