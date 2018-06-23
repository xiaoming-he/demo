package com.ming.ioc;

import org.springframework.stereotype.Component;

@Component
public class SgtPeppers implements CompactDisc{

    public void display() {
        System.out.println("sgtpeppers display");
    }
}
