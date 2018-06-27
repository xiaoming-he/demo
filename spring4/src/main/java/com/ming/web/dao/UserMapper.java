package com.ming.web.dao;

import com.ming.web.domain.User;
import org.apache.ibatis.annotations.Select;

/**
 * @author ming_he
 * @date 2018/6/27 23:37
 */
public interface UserMapper {
    @Select("SELECT * FROM t_user WHERE id = #{userId}")
    User getUser(long userId);
}
