package com.myq.seckill.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

/**
 * MD5工具类
 *
 * @author 孟赟强
 * @date 2021/3/22-15:20
 */
@Component
public class MD5Util {
    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    private static final String salt = "19971203";

    public static String inputPassToFormPass(String inputPass) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String formPassToDBPass(String formPass, String salt) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String inputPassToDbPass(String inputPass, String saltDB) {
        //MD5密码生成：salt：19971203
        String formPass = inputPassToFormPass(inputPass);
        //数据库密码生成：salt：19971203
        String dbPass = formPassToDBPass(formPass, saltDB);
        return dbPass;
    }

    public static void main(String[] args) {
        //5924d964180d15ee4c849840986eaf6c
        //8f3ecd26f43061340c10eddc73ddb4a8
        //8f3ecd26f43061340c10eddc73ddb4a8
        System.out.println(inputPassToFormPass("123456"));
        System.out.println(formPassToDBPass("5924d964180d15ee4c849840986eaf6c", "19971203"));
        System.out.println(inputPassToDbPass("123456", "19971203"));
    }

}
