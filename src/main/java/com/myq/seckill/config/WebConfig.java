package com.myq.seckill.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @author 孟赟强
 * @date 2021/4/18-1:11
 * 自定参数解析器, 作用：改变SpringMVC的Controller传入参数，
 * 实现可以User替换Token做为参数从登陆页面传到商品列表页面,
 * 并且不需要每次都写校验用户信息的代码
 */
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    UserArgumentResolver userArgumentResolver;

    /**
     * SpringMVC框架回调addArgumentResolvers，然后给Controller的参数赋值
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userArgumentResolver);
    }
}