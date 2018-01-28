package com.bluefish.seckill.service;

import com.bluefish.seckill.dao.GoodsDao;
import com.bluefish.seckill.model.MiaoshaGoods;
import com.bluefish.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品service
 *
 * @author bluefish 2018/1/28
 * @version 1.0.0
 */
@Service
public class GoodsService {

    @Autowired
    GoodsDao goodsDao;

    /**
     * 查询秒杀商品列表
     *
     * @return
     */
    public List<GoodsVo> listGoods(){
        return goodsDao.listGoods();
    }

    public GoodsVo getGoodsDetailById(long goodsId) {
        return goodsDao.getGoodsDetailById(goodsId);
    }

    public void reduceStock(GoodsVo goods) {
        MiaoshaGoods g = new MiaoshaGoods();
        g.setGoodsId(goods.getId());
        goodsDao.reduceStock(g);
    }
}
