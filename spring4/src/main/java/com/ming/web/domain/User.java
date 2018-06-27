package com.ming.web.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ming_he
 * @date 2018/6/27 23:38
 */
@Data
public class User {

    private long id;
    private String userName;
    private String realName;
    private int age;
    private BigDecimal balance;
}
