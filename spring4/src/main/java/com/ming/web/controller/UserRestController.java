package com.ming.web.controller;

import com.ming.web.domain.Spittle;
import com.ming.web.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

/**
 * @author ming_he
 * @date 2018/7/21 23:25
 */
@Controller
@RequestMapping("/users")
public class UserRestController {

    @GetMapping(value = "/{id}")
    public @ResponseBody
    User user(@PathVariable long id) {
        User user = new User(12, "xiaoming1",
                "xiaoming1", 25, new BigDecimal(10000));
        return user;
    }
}
