package com.totrade.mapper;

import com.totrade.domain.Message;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Yun
 * @version 1.0
 * @description: 聊天消息数据的存取
 * @date 2024/3/10
 */
@Mapper
@Repository
public interface MessageMapper {
    Integer saveMessage(Message message);
}
