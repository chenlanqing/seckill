package com.bluefish.seckill.dao;

import com.bluefish.seckill.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author bluefish 2018/1/28
 * @version 1.0.0
 */
@Mapper
public interface GoodsDao {

    List<GoodsVo> listGoods();

    GoodsVo getGoodsDetailById(long goodsId);
}
