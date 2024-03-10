package com.totrade.component.Handler;

import com.alibaba.fastjson.JSON;
import com.totrade.component.ChatServer;
import com.totrade.domain.Message;
import com.totrade.service.IMessageService;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Yun
 * @version 1.0
 * @description: 当客户端选择发送消息操作时执行的操作
 * @date 2024/3/10
 */
@Component
public class SendMessageHandler {
    @Autowired
    IMessageService iMessage;

    //因为此类在websocket句柄中使用new进行创建和调用成员函数
    //因此直接使用自动注入会导致出现空指针
    //以下为针对这个情况的特殊方法
    public static SendMessageHandler sendMessageHandler;

    //要求在spring完成正常组件的初始化后特意初始化这个bean
    @PostConstruct //慎用！复杂的逻辑会拖慢启动
    public void init() {
        sendMessageHandler = this;
    }


    public void execute(Message message) {
        //1.获取发送目标隧道
        Channel channel = ChatServer.USERS.get(message.getTo());
        //2.判断目标是否不存在或已经下线
        if (channel == null || !channel.isActive()) {
            //1.将此条消息标记为未读消息存入数据库
            int i = sendMessageHandler.iMessage.saveMessage(message);
            message.setIfSend(false);
            System.out.println("对方不在线," + i + "条消息已存入数据库");
        }
        //3.目标在线，发送消息
        else {
            //发送消息
            channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message)));
        }
    }
}
