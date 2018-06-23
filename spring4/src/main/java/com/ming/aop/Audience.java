package com.ming.aop;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Audience {

    @Pointcut("execution(* com.ming.aop.Performance.*(..))")
    public void perform(){
    }

    @Before("perform()")
    public void takeSeats() {
        System.out.println("perform before take seats");
    }

    @AfterReturning("perform()")
    public void applause() {
        System.out.println("CLAP CLAP CLAP");
    }

    @AfterThrowing("perform()")
    public void demandRefund() {
        System.out.println("demanding a refund");
    }
}
