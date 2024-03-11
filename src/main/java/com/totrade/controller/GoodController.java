package com.totrade.controller;

import com.totrade.domain.Result;
import com.totrade.service.IGoodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yun
 * @version 1.0
 * @description: 商品的controller层
 * @date 2024/3/6
 */
@Tag(name = "商品管理")
@RestController
public class GoodController {
    @Autowired
    IGoodService igoodService;
    //查询所有商品
    @Operation(summary = "查询所有商品")
    @GetMapping("/api/getGoods")
    public Result queryGoods() {
        return igoodService.queryGoods();
    }

    @Operation(summary = "商品模糊查询")
    //根据商品信息模糊查询
    @GetMapping("/api/searchGoods")
    public Result queryGoodsByInfo(@RequestParam("goodInfo") String goodInfo) {
        return igoodService.queryGoodsByInfo("%" + goodInfo + "%");
    }
}
