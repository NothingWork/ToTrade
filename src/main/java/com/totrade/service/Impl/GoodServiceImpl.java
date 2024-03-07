package com.totrade.service.Impl;

import com.totrade.domain.Good;
import com.totrade.domain.Result;
import com.totrade.mapper.GoodMapper;
import com.totrade.service.IGoodService;
import com.totrade.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    /**
     * @author Yun
     * @description: 查询商品
     * @param: good
     * @return: com.totrade.domain.Result
     * @date: 2024/3/6
     */
    @Override
    public Result queryGoods() {
        List<Good> list = goodMapper.queryGoods();
        for(Good good:list){
            good.setImageBytes(ImageUtil.getImageBytes(good.getPicSrc()));
        }
        return new Result(1,"success",list);
    }
}
