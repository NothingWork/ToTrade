package com.totrade.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yun
 * @version 1.0
 * @description: 用户实体类
 * @date 2024/3/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String Session_Key;
    private String openId;
    private String userName;
    private String avatarSrc;
}
