package com.bluefish.seckill.controller;

import com.bluefish.seckill.model.MiaoshaUser;
import com.bluefish.seckill.service.GoodsService;
import com.bluefish.seckill.service.UserService;
import com.bluefish.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author bluefish 2018/1/21
 * @version 1.0.0
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    UserService userService;

    @Autowired
    GoodsService goodsService;


    @RequestMapping("/to_list")
    public String list(Model model, MiaoshaUser user) {
        model.addAttribute("user", user);
        List<GoodsVo> goodsVos = goodsService.listGoods();
        model.addAttribute("goodsList", goodsVos);
        return "goods_list";
    }

    @RequestMapping("/to_detail/{goodsId}")
    public String detail(Model model, MiaoshaUser user, @PathVariable("goodsId") long goodsId) {
        model.addAttribute("user", user);

        GoodsVo goods = goodsService.getGoodsDetailById(goodsId);

        model.addAttribute("goods", goods);

        long startAt = goods.getStartDate().getTime();
        long endAt = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();

        int miaoshaStatus = 0;
        int remainSeconds = 0;
        long miaoshaPeriod = 0;
        if(now < startAt ) {
            //秒杀还没开始，倒计时
            miaoshaStatus = 0;
            remainSeconds = (int)((startAt - now )/1000);
        }else  if(now > endAt){
            //秒杀已经结束
            miaoshaStatus = 2;
            remainSeconds = -1;
        }else {
            //秒杀进行中
            miaoshaStatus = 1;
            remainSeconds = 0;
            miaoshaPeriod = (endAt - startAt) / 1000;
        }
        model.addAttribute("miaoshaStatus", miaoshaStatus);
        model.addAttribute("remainSeconds", remainSeconds);
        model.addAttribute("miaoshaPeriod", miaoshaPeriod);
        return "goods_detail";
    }
}
