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
    /**
     * @author Yun
     * @description: 从数据库中查询商品
     * @param: good
     * @return: java.util.List<com.totrade.domain.Good>
     * @date: 2024/3/6
     */
    List<Good> queryGoods();
}
