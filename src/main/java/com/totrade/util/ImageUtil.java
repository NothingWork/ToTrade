package com.totrade.util;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Yun
 * @version 1.0
 * @description: 处理图片传输问题
 * @date 2024/3/6
 */
public class ImageUtil {
    public static byte[] getImageBytes(String path){
            //图片转化为二进制
            byte[] imageBytes = null;
            try (FileInputStream fileInputStream = new FileInputStream(path)) {
                imageBytes = new byte[fileInputStream.available()];
                fileInputStream.read(imageBytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return imageBytes;
    }
}
