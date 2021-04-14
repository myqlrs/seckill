package com.myq.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myq.seckill.entity.User;
import com.myq.seckill.vo.LoginVo;
import com.myq.seckill.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 孟赟强
 * @since 2021-03-22
 */
public interface UserService extends IService<User> {
    //登录
    RespBean login(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);

}
