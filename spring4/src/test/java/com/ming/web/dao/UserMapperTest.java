package com.ming.web.dao;

import com.ming.web.config.RootConfig;
import com.ming.web.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author ming_he
 * @date 2018/6/27 23:47
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void getUserTest() {
       System.out.println(userMapper.getUser(1));
    }
}
