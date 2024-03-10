package com.totrade.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.totrade.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yun
 * @version 1.0
 * @description: 微信工具类
 * @date 2024/3/8
 */
public class WeChatUtil {
    @Autowired
    MessageMapper messageMapper;

    public static JSONObject getSessionKeyOrOpenId(String code) {
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, String> param = new HashMap<>();
        param.put("appid", ConstData.APPID);
        param.put("secret", ConstData.SECRET);
        param.put("js_code", code);
        param.put("grant_type", ConstData.GRANT_TYPE);
        return JSON.parseObject(HttpClientUtil.doGet(requestUrl, param));
    }

}
