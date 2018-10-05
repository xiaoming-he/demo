package com.ming.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    @Autowired
    private DiscoveryClient client;
    @Autowired
    private Registration registration;

    @GetMapping("/hello")
    public String hello() throws InterruptedException {
        int sleepTime = new Random().nextInt(3000);
        logger.info("sleepTime:{}", sleepTime);
        Thread.sleep(sleepTime);
        logger.info("/hello, host:{}, service_id:{}, port:{}", registration.getHost() ,registration.getServiceId(), registration.getPort());
        return "hello world";
    }
}
