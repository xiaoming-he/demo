package com.ming.web.aop;

import com.ming.web.exception.SpittleNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "com.ming.web")
public class AppWideExceptionHandler {

    public final static String VIEW_NOT_FOUND_SPITTLE = "error/notFound";

    @ExceptionHandler(SpittleNotFoundException.class)
    public String handleNotFoundSpittle() {
        return VIEW_NOT_FOUND_SPITTLE;
    }
}
