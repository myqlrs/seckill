package com.myq.seckill.exception;

import com.myq.seckill.vo.RespBeanEnum;
import lombok.*;

/**
 * @author 孟赟强
 * @date 2021/4/13-21:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalException extends RuntimeException{
    private RespBeanEnum respBeanEnum;
}
