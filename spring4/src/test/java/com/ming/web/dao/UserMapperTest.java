package com.ming.web.dao;

import com.ming.web.config.RootConfig;
import com.ming.web.domain.User;
import com.ming.web.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author ming_he
 * @date 2018/6/27 23:47
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Test
    public void getUserTest() {
       System.out.println(userMapper.getUser(1));
    }

    @Test
    //@Transactional
    public void addUser() {
        User user = new User(14, "xiaoming1",
                "xiaoming1", 25, new BigDecimal(10000));
        userService.addUser(user);
        User user2 = new User(13, "xiaoming1",
                "xiaoming1", 25, new BigDecimal(10000));
        userService.addUser(user2);
    }
}
