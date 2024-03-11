package com.totrade.mapper;

import com.totrade.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Yun
 * @version 1.0
 * @description: 和用户有关的数据库操作
 * @date 2024/3/9
 */
@Mapper
@Repository
public interface UserMapper {
    User getUserByOpenId(String openId);
    String getUserIdByName(String name);
    String getNameByUserId(String id);
    //根据用户名精确查找用户，存在返回true，不存在返回false
    Boolean getUserByName(String userName);
    Boolean register(User user);

}
