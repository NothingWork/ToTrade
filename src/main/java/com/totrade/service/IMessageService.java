package com.totrade.service;

import com.totrade.domain.Message;
import com.totrade.domain.Result;
import org.springframework.stereotype.Service;

@Service
public interface IMessageService {
    Result sendMessage(Message message);

    Result getUnSendMessage(String name);

    Result getAllMessages(String from,String to);

    Result getLastMessages(String name);

    String nameOrId(String name,String id);
}
