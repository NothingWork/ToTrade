package com.totrade.service;

import com.totrade.domain.Message;
import com.totrade.domain.Result;
import org.springframework.stereotype.Service;

@Service
public interface IMessageService {
    Result sendMessage(Message message);
}
