package com.ming.web.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Aspect
@Component
public class ErrorsInterceptor {

    @Pointcut(value = "execution(* com.ming.web..*(..))"
            + "&& args(.. , errors)")
    //@Pointcut("execution(* com.ming.web..*(..))")
    public void error(BindingResult errors) {
    }

    @Before("error(errors)")
    public void before(BindingResult errors) {
        if (errors.hasErrors()) {
            throw new RuntimeException(errors.getAllErrors().toString());
        }
    }
}
