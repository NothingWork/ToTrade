package com.totrade.service.Impl;

import com.totrade.domain.Message;
import com.totrade.mapper.MessageMapper;
import com.totrade.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Yun
 * @version 1.0
 * @description: 处理聊天消息数据的操作
 * @date 2024/3/10
 */
@Service
public class MessageServiceImpl implements IMessageService {

    @Autowired
    MessageMapper messageMapper;


    /**
     * @author Yun
     * @description: 想数据库中插入数据，成功返回1
     * @param: message
     * @return: java.lang.Integer
     * @date: 2024/3/10
     */
    @Override
    public Integer saveMessage(Message message) {
        return messageMapper.saveMessage(message);
    }


}
