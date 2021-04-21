package com.myq.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myq.seckill.entity.User;
import com.myq.seckill.exception.GlobalException;
import com.myq.seckill.mapper.UserMapper;
import com.myq.seckill.service.UserService;
import com.myq.seckill.utils.*;
import com.myq.seckill.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 孟赟强
 * @since 2021-03-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    //登录逻辑
    @Override
    public RespBean login(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        /*if(StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password)){
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }*/
        //根据手机号获取用户
        User user = userMapper.selectById(mobile);
        if(user==null){
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }
        //判断密码是否正确
        if(!MD5Util.formPassToDBPass(password,user.getSalt()).equals(user.getPassword())){
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }
        //生成Cookie
        String ticket = UUIDUtil.uuid();
//        request.getSession().setAttribute(ticket,user);
        redisTemplate.opsForValue().set("user:" + ticket,user);
        CookieUtils.setCookie(request,response,"userTicket",ticket);
        return RespBean.success(ticket);
    }

    @Override
    public User getUserByCookie(String userticket, HttpServletRequest request, HttpServletResponse response) {
        if(StringUtils.isEmpty(userticket)){
            return null;
        }
        User user = (User) redisTemplate.opsForValue().get("user:" + userticket);
        if(user!=null){
            CookieUtils.setCookie(request,response,"userTicket",userticket);
        }
        return user;
    }

}
