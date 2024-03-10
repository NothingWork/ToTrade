package com.totrade.component.Handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.totrade.component.ChatServer;
import com.totrade.domain.Message;
import io.netty.channel.Channel;
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
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame){
        //将从前端接受到的消息转为message对象,将不包含的属性复制为null
        try {
            Message message = JSON.parseObject(textWebSocketFrame.text(),Message.class, Feature.IgnoreNotMatch);
            //获取并填充消息的发送时间
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            message.setTime(LocalDateTime.now().format(formatter));
            //初次连接操作，携带注册使用的name
            if(message.getName()!=null){
                //注册隧道
                ChatServer.USERS.put(message.getName(),channelHandlerContext.channel());
            }
            //不是注册隧道操作，消息发送
            else{
            //信息发送
            //1.获取发送目标隧道
            Channel channel = ChatServer.USERS.get(message.getTo());
            //2.判断目标不存在或已经下线
            if(channel == null || !channel.isActive()){
                System.out.println("待完善");
            }
            //3.目标在线，发送消息
            else{
                //发送消息
                channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message)));
            }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
