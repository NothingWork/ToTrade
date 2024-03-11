package com.totrade.service.Impl;

import com.totrade.component.ChatServer;
import com.totrade.component.Handler.WebSocketHandler;
import com.totrade.domain.Message;
import com.totrade.domain.Result;
import com.totrade.mapper.MessageMapper;
import com.totrade.mapper.UserMapper;
import com.totrade.service.IMessageService;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Autowired
    UserMapper userMapper;


    /**
     * @author Yun
     * @description: 在消息写入隧道前的预处理，
     * @param: message
     * @return: com.totrade.domain.Result
     * @date: 2024/3/11
     */
    @Override
    public Result sendMessage(Message message) {
        //获取接收者信息，检查对方是否在线
        Channel channel = ChatServer.USERS.get(message.getTo());
        if (channel == null || !channel.isActive()) {
            //对方不在线或者未注册，信息发送失败，将消息标记为未发送
            message.setIfSend(false);
        } else {
            //对方在线直接发送消息
            WebSocketHandler.sendMessage(message, channel);
        }
        //消息存入数据库
        //存入数据库之前先将聊天记录中的name转为id
        String name = message.getFrom();
        message.setFrom(userMapper.getUserIdByName(name));
        name = message.getTo();
        message.setTo(userMapper.getUserIdByName(name));
        messageMapper.saveMessage(message);
        return new Result(301, "send_success", null);
    }

    /**
     * @author Yun
     * @description: 读取未读消息，并将这些未读消息发送出去，最后将他们的状态修改为已发送
     * @param: name
     * @return: com.totrade.domain.Result
     * @date: 2024/3/11
     */
    @Override
    public Result getUnSendMessage(String name) {
        //将未读消息从隧道发送出去
        //查找时将name转为id,使用id查找
        String toId = userMapper.getUserIdByName(name);
        List<Message> list = messageMapper.getUnSendMessage(toId);
        for (Message message : list
        ) {
            //发送前将发送者和接收者的Id转为name
            String id = message.getFrom();
            message.setFrom(userMapper.getNameByUserId(id));
            message.setTo(name);
            WebSocketHandler.sendMessage(message, ChatServer.USERS.get(name));
        }
        //将该用户的所有消息改为已读
        int number = messageMapper.setSend(toId);
        return new Result(302, "success", number);
    }

    /**
     * @author Yun
     * @description: 获取该用户的所有消息，用于渲染聊天记录
     * @param: name
     * @return: com.totrade.domain.Result
     * @date: 2024/3/11
     */
    @Override
    public Result getAllMessages(String name) {
        return new Result(303, "success", messageMapper.getAllMessages(name));
    }

}
