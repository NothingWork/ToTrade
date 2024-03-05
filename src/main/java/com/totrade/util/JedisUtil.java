package com.totrade.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Properties;

import static com.totrade.util.PropertiesUtil.loadProperties;

/**
 * @author Yun
 * @version 1.0
 * @description: 连接redis的工具类
 * @date 2024/3/5
 */
public class JedisUtil {
//    public static void main(String args[]){
//        getData();
//    }

    private static final JedisPool jedisPool;
    private static final Jedis jedis;
    //建立数据库连接
    static {
        Properties properties = loadProperties(); //包含redis的配置项
        String host = properties.getProperty("host");
        int port = Integer.parseInt(properties.getProperty("port"));
        // JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPool = new JedisPool(host,port);
        jedis = jedisPool.getResource();
    }

public static void getData(){
        jedis.set("msg","hello");
        String msg = jedis.get("msg");
    System.out.println(msg);
    }

}
