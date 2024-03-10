package com.totrade;

import com.totrade.component.ChatServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ToTradeApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(ToTradeApplication.class, args);
        ChatServer.start(); //启动聊天服务器
    }

}
