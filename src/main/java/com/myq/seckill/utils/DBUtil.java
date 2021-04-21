package com.myq.seckill.utils;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author 孟赟强
 * @date 2021/4/20-23:18
 */
public class DBUtil {

    private static String url = "jdbc:mysql://121.41.4.245:3306/seckill?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
    private static String username = "root";
    private static String password = "123456";
    private static String driver = "com.mysql.cj.jdbc.Driver";

    public static Connection getConn() throws Exception{
        Class.forName(driver);
        return DriverManager.getConnection(url,username, password);
    }
}
