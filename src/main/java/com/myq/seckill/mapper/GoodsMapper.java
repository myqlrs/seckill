package com.myq.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myq.seckill.entity.Goods;
import com.myq.seckill.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 孟赟强
 * @since 2021-04-18
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    /**
     * 获取商品列表
     * @return
     */
    List<GoodsVo> findGoodsVo();

    /**
     * 获取商品详情
     * @param goodsId
     * @return
     */
    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
