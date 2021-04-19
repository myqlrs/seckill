package com.myq.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myq.seckill.entity.Order;
import com.myq.seckill.entity.User;
import com.myq.seckill.vo.GoodsVo;
import org.springframework.data.domain.Sort;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 孟赟强
 * @since 2021-04-18
 */
public interface OrderService extends IService<Order> {

    /**
     * 秒杀
     * @param user
     * @param goodsVo
     * @return
     */
    Order seckill(User user, GoodsVo goodsVo);
}
