package com.totrade.mapper;

import com.totrade.domain.Good;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Yun
 * @version 1.0
 * @description: 商品有关的数据库操作
 * @date 2024/3/6
 */
@Mapper
@Repository
public interface GoodMapper {
    List<Good> queryGoods();
    List<Good> queryGoodsByInfo(String goodInfo);
}
