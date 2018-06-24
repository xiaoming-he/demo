package com.ming.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping({"/","/homePage"})
public class HomeController {

    @RequestMapping(method = GET)
    public String home() {
        return "Hello Spring Boot";
    }
}
