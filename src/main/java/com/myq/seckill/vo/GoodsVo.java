package com.myq.seckill.vo;
import com.myq.seckill.entity.Goods;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品返回对象
 * @author 孟赟强
 * @date 2021/4/18-21:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsVo extends Goods {
    /**
     * 秒杀价
     */
    private BigDecimal seckillPrice;

    /**
     * 库存数量
     */
    private Integer stockCount;

    /**
     * 秒杀开始时间
     */
    private Date startDate;

    /**
     * 秒杀结束时间
     */
    private Date endDate;
}
