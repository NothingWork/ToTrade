package com.totrade.controller;

import com.totrade.domain.Result;
import com.totrade.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yun
 * @version 1.0
 * @description: 有关用户类的controller层
 * @date 2024/3/8
 */
@Tag(name = "用户管理")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    IUserService userService;
    @Operation(summary = "查询")
    @GetMapping("/login")
    public Result login(@RequestParam("code") String code){
        return userService.login(code);
    }
}
