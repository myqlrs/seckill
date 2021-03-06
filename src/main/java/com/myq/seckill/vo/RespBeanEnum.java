package com.myq.seckill.vo;

import lombok.*;

/**
 * @author 孟赟强
 * @date 2021/3/28-18:00
 */
@ToString
@Getter
@AllArgsConstructor
public enum RespBeanEnum {
    //通用
    SUCCESS(200,"成功"),
    ERROR(500,"服务端异常"),
    //登录模块5002xx
    LOGIN_ERROR(500210,"用户名或密码错误"),
    MOBILE_ERROR(500211,"手机号码格式不正确"),
    BIND_ERROR(500212,"参数校验异常"),
    //秒杀模块5005xx
    EMPTY_ERROR(500500,"库存不足"),
    REPEATE_ERROR(500501,"该商品每人限购一件"),
    ;
    private final Integer code;
    private final String message;
}
