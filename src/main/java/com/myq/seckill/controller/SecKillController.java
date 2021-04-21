package com.myq.seckill.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.myq.seckill.entity.*;
import com.myq.seckill.service.*;
import com.myq.seckill.vo.GoodsVo;
import com.myq.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 秒杀控制器
 * @author 孟赟强
 * @date 2021/4/18-23:50
 */
@Controller
@RequestMapping("/seckill")
public class SecKillController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private SeckillOrderService seckillOrderService;
    @Autowired
    private OrderService orderService;

    /**
     * 秒杀
     * @param model
     * @param user
     * @param goodsId
     * @return
     */
    @RequestMapping("doSeckill")
    public String doSeckill(Model model, User user,Long goodsId){
        if(user == null){
            return "login";
        }
        model.addAttribute("user",user);
        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);
        //判断库存
        if(goodsVo.getStockCount()<1){
            model.addAttribute("errmsg", RespBeanEnum.EMPTY_ERROR.getMessage());
            return "seckillfail";
        }
        //判断是否重复抢购
        SeckillOrder seckillOrder = seckillOrderService.getOne(new QueryWrapper<SeckillOrder>().eq("user_id", user.getId())
                .eq("goods_id", goodsId));
        if(seckillOrder != null){
            model.addAttribute("errmsg",RespBeanEnum.REPEATE_ERROR.getMessage());
            return "seckillfail";
        }
        Order order = orderService.seckill(user,goodsVo);
        model.addAttribute("orderInfo",order);
        model.addAttribute("goodsVo",goodsVo);
        return "orderdetail";
    }
}
