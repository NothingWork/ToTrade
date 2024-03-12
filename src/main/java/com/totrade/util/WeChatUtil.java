package com.totrade.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.totrade.domain.Message;
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

    public static Boolean checkMsg(Message msg1,Message msg2){
        //消息一的发送者和接收者与消息二的发送者与接收者为同一组人，则表示两则消息属于同一会话窗口
        String str = msg1.getFrom()+msg1.getTo();
        return str.equals(msg2.getFrom() + msg2.getTo()) || str.equals(msg2.getTo() + msg2.getFrom());
    }

}
