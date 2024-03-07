package com.totrade.controller;

import com.totrade.domain.Result;
import com.totrade.service.IGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yun
 * @version 1.0
 * @description: 商品的controller层
 * @date 2024/3/6
 */
@RestController
public class GoodController {
    @Autowired
    IGoodService igoodService;
    @GetMapping("/api/getGoods")
    public Result queryGoods() {
    return igoodService.queryGoods();
    }
}
