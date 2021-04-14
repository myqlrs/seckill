package com.myq.seckill.utils;

import java.util.UUID;

/**
 * @author 孟赟强
 * @date 2021/3/22-15:20
 */
public class UUIDUtil {

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
