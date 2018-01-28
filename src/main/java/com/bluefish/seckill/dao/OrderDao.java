package com.bluefish.seckill.dao;

import com.bluefish.seckill.model.MiaoshaOrder;
import com.bluefish.seckill.model.OrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface OrderDao {

    MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(@Param("userId") long userId, @Param("goodsId") long goodsId);

    long insert(OrderInfo orderInfo);

    int insertMiaoshaOrder(MiaoshaOrder miaoshaOrder);
}
