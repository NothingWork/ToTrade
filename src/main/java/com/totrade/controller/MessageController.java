package com.totrade.controller;

import com.totrade.domain.Message;
import com.totrade.domain.Result;
import com.totrade.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Yun
 * @version 1.0
 * @description: 处理聊天系统的接口业务
 * @date 2024/3/11
 */
@RestController
@RequestMapping("/api")
public class MessageController {
    @Autowired
    IMessageService iMessageService;
    //发送消息接口
    @RequestMapping("/sendMessage")
    public Result sendMessage(
            @RequestParam("from")String from,
            @RequestParam("to")String to,
            @RequestParam("text")String text
    ){
        //获取并填充消息的发送时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //ifSend默认值为true
        return iMessageService.sendMessage(new Message(from,to,LocalDateTime.now().format(formatter),text,true));
    }
    //获取所有未读消息接口
    @GetMapping("/getUnReadMessages")
    public Result getMessage(
            @RequestParam("name")String name
    ){
        //对于前端来说是UnRead,对于后端来说是未接收
        return iMessageService.getUnSendMessage(name);
    }
}
