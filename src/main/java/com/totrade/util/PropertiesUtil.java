package com.totrade.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Yun
 * @version 1.0
 * @description: 读取配置文件的工具类
 * @date 2024/3/5
 */
public class PropertiesUtil {
    //读取配置文件
    public static Properties loadProperties(){
        InputStream inputStream = org.apache.logging.log4j.util.PropertiesUtil.class.getResourceAsStream("/config/application.yml");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
