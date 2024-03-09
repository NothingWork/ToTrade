package com.totrade.util;

import java.util.Random;

/**
 * @author Yun
 * @version 1.0
 * @description: 生成六位随机数的工具类
 * @date 2024/3/7
 */
public class RandomUtil {
    public static String getRandomString() {
        String sources = "0123456789abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder flag = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            flag.append(sources.charAt(random.nextInt(35)));
        }
        return flag.toString();
    }
}
