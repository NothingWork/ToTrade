package com.totrade.component.Handler;

import com.totrade.component.ChatServer;
import com.totrade.domain.Message;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author Yun
 * @version 1.0
 * @description: 当客户端选择连接到服务端时执行的操作
 * @date 2024/3/10
 */
public class ConnectHandler {
    public static void execute(Message message, ChannelHandlerContext channelHandlerContext){
        //注册隧道
        ChatServer.USERS.put(message.getName(),channelHandlerContext.channel());
        //查找数据库中的未读消息
    }
}
