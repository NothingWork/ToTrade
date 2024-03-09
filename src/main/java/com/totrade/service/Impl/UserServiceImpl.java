package com.totrade.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.totrade.domain.Result;
import com.totrade.domain.User;
import com.totrade.mapper.UserMapper;
import com.totrade.service.IUserService;
import com.totrade.util.ConstData;
import com.totrade.util.RandomUtil;
import com.totrade.util.WeChatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Yun
 * @version 1.0
 * @description: 和用户业务有关的service层
 * @date 2024/3/8
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public Result login(String code) {
        //判断登录结果
        JSONObject jsonObject = WeChatUtil.getSessionKeyOrOpenId(code);
        if(jsonObject.containsKey("errcode")){
            //登录失败，返回错误信息
            return new Result(-1,"log_fail",jsonObject);
        }
        String openId = (String) jsonObject.get("openid");
        User user = userMapper.getUserByOpenId(openId);
        //登录成功，检查数据库进行注册
        if(user==null){
            //数据库不存在此用户，进行注册
            //1.生成不重复的随机用户名
            String userName = RandomUtil.getRandomString();
            while(userMapper.getUserByName(userName)){
                userName = RandomUtil.getRandomString();
            }
            //2.构建User对象
            user = new User(openId,(String)jsonObject.get("sessionKey"),
                    userName, ConstData.defultSrc);
            //3.注册 根据返回结果返回成功与否
             if(register(user)){
                 return new Result(4,"reg_success",user);
             }
             else{
                 return new Result(-2,"reg_fail",null);
             }
        }
        return new Result(3,"log_success", user);
    }

    @Override
    public Boolean register(User user) {
        return userMapper.register(user);
    }
}
