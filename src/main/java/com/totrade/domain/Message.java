package com.totrade.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yun
 * @version 1.0
 * @description: 在隧道中写入的消息,要封装成TextWebSocketFrame对象
 * @date 2024/3/9
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private String from; //消息的发送者
    private String to; //消息的接收者
    private String time; //消息的发送时间
    private String text; //消息的文本内容

    private boolean ifSend; //消息是否是未读消息

}
