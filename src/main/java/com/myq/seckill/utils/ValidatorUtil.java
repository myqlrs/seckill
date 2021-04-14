package com.myq.seckill.utils;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 手机号码校验
 * @author 孟赟强
 * @date 2021/3/31-12:07
 */
public class ValidatorUtil {
    //正则表达式校验模版
    private static final Pattern mobile_pattern = Pattern.compile("[1]([3-9])[0-9]{9}$");

    public static boolean isMobile(String mobile){
        if(StringUtils.isEmpty(mobile)){
            return false;
        }
        Matcher matcher = mobile_pattern.matcher(mobile);
        //返回校验结果
        return matcher.matches();
    }
}
