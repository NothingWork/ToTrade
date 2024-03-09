package com.totrade.service;

import com.totrade.domain.Result;
import com.totrade.domain.User;

public interface IUserService {
    Result login(String code);

    Boolean register(User user);
}
