package com.ming.dao;


import com.ming.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ming_he
 * @date 2018/6/27 23:37
 */
@Mapper
public interface UserMapper {
    User getUser(long userId);
}
