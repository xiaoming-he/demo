package com.ming.web.service;

import com.ming.web.dao.UserMapper;
import com.ming.web.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author ming_he
 * @date 2018/7/15 14:41
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EmployeeService employeeService;

    @Transactional
    public void addUser(User user) {
        userMapper.addUser(user);
        employeeService.addEmployee();
    }

    @Transactional
    public void update() {
        int i = 1/0;
    }
}
