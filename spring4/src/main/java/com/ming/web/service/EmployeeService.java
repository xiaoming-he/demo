package com.ming.web.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.REQUIRED;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

/**
 * @author ming_he
 * @date 2018/7/17 23:06
 */
@Component
public class EmployeeService {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addEmployee() {

    }
}
