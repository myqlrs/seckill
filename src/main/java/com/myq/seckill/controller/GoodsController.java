package com.myq.seckill.controller;

import com.myq.seckill.entity.User;
import com.myq.seckill.service.*;
import com.myq.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 商品控制器
 * @author 孟赟强
 * @date 2021/4/14-21:58
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private UserService userService;
    @Autowired
    private GoodsService goodsService;

    /**
     * 跳转到商品列表
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String toList(Model model,User user){
        /*if (!StringUtils.isEmpty(ticket)) {
            return "login";
        }
//        User user = (User) session.getAttribute(ticket);
        User user = userService.getUserByCookie(ticket,request,response);
        if(user == null){
            return "login";
        }*/
        model.addAttribute("user",user);
        model.addAttribute("goodList",goodsService.findGoodsVo());
        return "goodslist";
    }

    @RequestMapping("/detail/{goodsId}")
    public String toDetail(Model model,User user,@PathVariable Long goodsId){
        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);
        Date startDate = goodsVo.getStartDate();
        Date endDate = goodsVo.getEndDate();
        Date nowDate = new Date();
        //秒杀状态
        int secKillStatus = 0;
        //秒杀倒计时
        int reSeconds = 0;
        if(nowDate.before(startDate)){
            reSeconds = (int) ((startDate.getTime()-nowDate.getTime())/1000);
        }else if(nowDate.after(endDate)){
            secKillStatus = 2;
            reSeconds = -1;
        }else{
            secKillStatus = 1;
            reSeconds = 0;
        }
        model.addAttribute("user",user);
        model.addAttribute("goods",goodsVo);
        model.addAttribute("seckillStatus",secKillStatus);
        model.addAttribute("reSeconds",reSeconds);
        return "goodsdetail";

    }
}
