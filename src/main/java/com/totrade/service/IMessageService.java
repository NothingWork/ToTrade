package com.totrade.service;

import com.totrade.domain.Message;
import org.springframework.stereotype.Service;

@Service
public interface IMessageService {
    Integer saveMessage(Message message);
}
