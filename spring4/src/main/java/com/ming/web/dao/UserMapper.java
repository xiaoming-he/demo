package com.ming.web.dao;

import com.ming.web.domain.User;

/**
 * @author ming_he
 * @date 2018/6/27 23:37
 */
public interface UserMapper {
    User getUser(long userId);
}
