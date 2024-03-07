package com.totrade.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yun
 * @version 1.0
 * @description: 商品实体类
 * @date 2024/3/6
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Good {
    private Integer goodId;
    private String price;
    private String picSrc;
    private Integer userId;
    private String goodInfo;
    private byte[] imageBytes;
}
