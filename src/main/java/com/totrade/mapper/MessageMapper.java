package com.totrade.mapper;

import com.totrade.domain.Message;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Yun
 * @version 1.0
 * @description: 聊天消息数据的存取
 * @date 2024/3/10
 */
@Mapper
@Repository
public interface MessageMapper {
    void saveMessage(Message message);
    List<Message> getUnSendMessage(String name);
    Integer setSend(String name);
    List<Message> getAllMessages(String name);
}
