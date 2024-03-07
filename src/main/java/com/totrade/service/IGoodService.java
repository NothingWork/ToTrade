package com.totrade.service;

import com.totrade.domain.Result;
import org.springframework.stereotype.Service;

@Service
public interface IGoodService {
    Result queryGoods();
    Result queryGoodsByInfo(String goodInfo);
}
