package com.ming.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Customer {

    private Dessert dessert;

    @Autowired
    @Soft
    public void setDessert(Dessert dessert) {
        this.dessert = dessert;
    }
}
