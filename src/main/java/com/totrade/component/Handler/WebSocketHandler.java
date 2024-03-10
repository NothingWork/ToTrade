package com.totrade.component.Handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.totrade.domain.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Yun
 * @version 1.0
 * @description: 处理当服务端接收或发送消息时的处理句柄
 * @date 2024/3/9
 */
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) {
        //将从前端接受到的消息转为message对象,将不包含的属性赋值为null

        try {
            Message message = JSON.parseObject(textWebSocketFrame.text(), Message.class, Feature.IgnoreNotMatch);
            //获取并填充消息的发送时间
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            message.setTime(LocalDateTime.now().format(formatter));
            //初次连接操作，携带注册使用的name
            if (message.getName() != null) {
                //连接操作
                ConnectHandler.execute(message, channelHandlerContext);
            }
            //不是连接操作，是消息发送操作
            else {
                //信息发送
               new SendMessageHandler().execute(message);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
