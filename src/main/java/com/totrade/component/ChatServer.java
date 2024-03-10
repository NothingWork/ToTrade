package com.totrade.component;

import com.totrade.component.Handler.WebSocketHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Yun
 * @version 1.0
 * @description: 聊天系统服务端搭建,每当有用户连接到这个服务器时都会建立一条隧道绑定这个用户
 * @date 2024/3/9
 */
@Component
public class ChatServer {

    public static final Map<String,Channel>  USERS = new ConcurrentHashMap<>(1024);

    public static void start() throws InterruptedException {

        //1.建立两个线程
        EventLoopGroup boss  = new NioEventLoopGroup();
        EventLoopGroup worker  = new NioEventLoopGroup();

        //2.绑定端口
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss,worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) {
                        ChannelPipeline channelPipeline = socketChannel.pipeline();
                        //添加http编码解码器
                        channelPipeline.addLast(new HttpServerCodec())
                                //支持大数据流
                                .addLast(new ChunkedWriteHandler())
                                //对http消息做聚合操作.FullHttpRequest、FullHttpResponse
                                .addLast(new HttpObjectAggregator(1024*64))
                                // webSocket
                                .addLast(new WebSocketServerProtocolHandler("/"))
                                .addLast(new WebSocketHandler());
                    }
                });
        //服务端绑定的端口
        bootstrap.bind(8088).sync();
    }
}
