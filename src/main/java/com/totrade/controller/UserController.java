package com.totrade.controller;

import com.totrade.domain.Result;
import com.totrade.service.IUserService;
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
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    IUserService userService;
    @GetMapping("/login")
    public Result login(@RequestParam("code") String code){
        return userService.login(code);
    }
}
