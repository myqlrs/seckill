package com.myq.seckill.controller;

import com.myq.seckill.service.UserService;
import com.myq.seckill.vo.LoginVo;
import com.myq.seckill.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author 孟赟强
 * @date 2021/3/22-23:27
 */
@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {
    @Autowired
    UserService userService;

    //跳转登录页面
    @RequestMapping("/tologin")
    public String toLogin() {
        return "login";
    }

    //执行登录功能
    @RequestMapping("/dologin")
    @ResponseBody
    public RespBean doLogin(@Valid LoginVo loginVo, HttpServletRequest request,HttpServletResponse response) {
        return userService.login(loginVo,request,response);
    }
}
