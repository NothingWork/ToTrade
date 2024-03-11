package com.totrade.controller;

import com.totrade.domain.Message;
import com.totrade.domain.Result;
import com.totrade.service.IMessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Yun
 * @version 1.0
 * @description: 处理聊天系统的接口业务
 * @date 2024/3/11
 */
@Tag(name = "消息管理")
@RestController
@RequestMapping("/api")
public class MessageController {
    @Autowired
    IMessageService iMessageService;

    //发送消息接口
    @Operation(summary = "消息发送")
    @PostMapping("/sendMessage")
    public Result sendMessage(
            @RequestParam("from") String from,
            @RequestParam("to") String to,
            @RequestParam("text") String text
    ) {
        //获取并填充消息的发送时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //ifSend默认值为true
        return iMessageService.sendMessage(new Message(from, to, LocalDateTime.now().format(formatter), text, true));
    }

    @Operation(summary = "获取未读消息")
    //获取所有未读消息接口
    @GetMapping("/getUnReadMessages")
    public Result getMessage(
            @Parameter(name = "name", description = "用户名") String name
    ) {
        //对于前端来说是UnRead,对于后端来说是未接收
        return iMessageService.getUnSendMessage(name);
    }

    //获取该用户的所有消息用于渲染聊天记录
    @Operation(summary = "获取消息记录")
    @GetMapping("/getAllMessages")
    public Result getAllMessages(
            @Parameter(name = "name", description = "用户名") String name
    ) {
        return iMessageService.getAllMessages(name);
    }
}
