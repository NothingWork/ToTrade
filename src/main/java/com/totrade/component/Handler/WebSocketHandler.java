package com.totrade.component.Handler;

import com.alibaba.fastjson.JSON;
import com.totrade.component.ChatServer;
import com.totrade.domain.Message;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * @author Yun
 * @version 1.0
 * @description: 处理当服务端接收或发送消息时的处理句柄
 * @date 2024/3/9
 */
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    //监听前端发来的注册消息
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) {
        //将从前端接收注册消息
        try {
            //注册隧道
            System.out.println(textWebSocketFrame.text());
            ChatServer.USERS.put(textWebSocketFrame.text(), channelHandlerContext.channel());
            //获取并填充消息的发送时间
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //将消息通过隧道发送出去
    public static void sendMessage(Message message, Channel channel) {
        //发送消息
        channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message)));
    }
}
