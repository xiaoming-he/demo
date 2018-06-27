package com.ming.ioc;

import org.springframework.stereotype.Component;

@Component
public class SgtPeppers implements CompactDisc{

    @Override
    public void display() {
        System.out.println("sgtpeppers display");
    }
}
