package com.totrade.service.Impl;

import com.totrade.component.ChatServer;
import com.totrade.component.Handler.WebSocketHandler;
import com.totrade.domain.Message;
import com.totrade.domain.Result;
import com.totrade.mapper.MessageMapper;
import com.totrade.mapper.UserMapper;
import com.totrade.service.IMessageService;
import com.totrade.util.WeChatUtil;
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
        message.setFrom(nameOrId(name, null));
        name = message.getTo();
        message.setTo(nameOrId(name, null));
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
        String toId = nameOrId(name, null);
        List<Message> list = messageMapper.getUnSendMessage(toId);
        for (Message message : list
        ) {
            //发送前将发送者和接收者的Id转为name
            String id = message.getFrom();
            message.setFrom(nameOrId(null, id));
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
    public Result getAllMessages(String from, String to) {

        return new Result(303, "success", messageMapper.getAllMessages(nameOrId(from, null),
                nameOrId(to, null)));
    }

    /**
     * @author Yun
     * @description: 获取当前登录用户与所有联系人的最新一条消息记录，渲染消息页面用
     * @param: name
     * @return: com.totrade.domain.Result
     * @date: 2024/3/12
     */
    @Override
    public Result getLastMessages(String name) {
        List<Message> list = messageMapper.getLastMessages(nameOrId(name,null));
        //因为数据库中消息是默认以存入的时间顺序存储消息的，所以数组最后一条消息即为最新消息
        //遍历消息数组，如果此条消息与前一条消息的接收者相同，则移除上一条消息，以此得到最新消息
        for (int i = 1;i< list.size();i++){
            if(WeChatUtil.checkMsg(list.get(i),list.get(i-1))){
                list.remove(i-1);
                i--;
            }
        }
        return new Result(304,"success",list);
    }

    /**
     * @author Yun
     * @description: 对name和id的转换工具
     * @param: name id
     * @return: java.lang.String
     * @date: 2024/3/12
     */
    @Override
    public String nameOrId(String name, String id) {
        if (name == null) {
            return userMapper.getNameByUserId(id);
        }
        return userMapper.getUserIdByName(name);
    }

}
