package com.totrade.service.Impl;

import com.totrade.domain.Good;
import com.totrade.domain.Result;
import com.totrade.mapper.GoodMapper;
import com.totrade.service.IGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yun
 * @version 1.0
 * @description: 商品类有关功能的service层
 * @date 2024/3/6
 */
@Service
public class GoodServiceImpl implements IGoodService {
    @Autowired
    GoodMapper goodMapper;
    List<Good> list = new ArrayList<>();

    /**
     * @author Yun
     * @description: 查询所有商品
     * @param: null
     * @return: com.totrade.domain.Result
     * @date: 2024/3/7
     */
    @Override
    public Result queryGoods() {
        list = goodMapper.queryGoods();
//        for(Good good:list){
//            good.setImageBytes(ImageUtil.getImageBytes(good.getPicSrc()));
//        }
        return new Result(101, "success", list);
    }

    /**
     * @author Yun
     * @description:根据商品信息模糊查询
     * @param: goodInfo
     * @return: com.totrade.domain.Result
     * @date: 2024/3/7
     */

    @Override
    public Result queryGoodsByInfo(String goodInfo) {
        list = goodMapper.queryGoodsByInfo(goodInfo);
        return new Result(102, "success", list);
    }
}
