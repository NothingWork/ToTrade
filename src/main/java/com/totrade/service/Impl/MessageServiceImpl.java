package com.totrade.service.Impl;

import com.totrade.component.ChatServer;
import com.totrade.component.Handler.WebSocketHandler;
import com.totrade.domain.Message;
import com.totrade.domain.Result;
import com.totrade.mapper.MessageMapper;
import com.totrade.service.IMessageService;
import io.netty.channel.Channel;
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
     * @author Yuhn
     * @description: 在消息写入隧道前的预处理，
     * @param: message
     * @return: com.totrade.domain.Result
     * @date: 2024/3/11
     *
     */
    @Override
    public Result sendMessage(Message message){
        //获取接收者信息，检查对方是否在线
        Channel channel = ChatServer.USERS.get(message.getTo());
        if(channel == null || !channel.isActive()){
            //对方不在线或者未注册，信息发送失败，将消息标记为未发送
            message.setIfSend(false);
        }
        else{
            //对方在线直接发送消息
            WebSocketHandler.sendMessage(message,channel);
        }
        //消息存入数据库
        messageMapper.saveMessage(message);
        return new Result(5,"send_success",null);
    }

}
