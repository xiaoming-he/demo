package com.ming;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author ming_he
 * @date 2018/6/30 22:51
 */
@SpringCloudApplication
public class ConsumerMovieApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerMovieApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
