package com.ric.scaffold.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.interceptor.SaAnnotationInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ArrayUtil;
import com.alibaba.fastjson2.JSON;
import com.ric.scaffold.core.beans.ResultBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * sa-token 配置
 */
@Configuration
public class SaTokenConfig  implements WebMvcConfigurer {
//  @Value("sa-token.excludePaths")
//  private static List<String> SaTokenExFilterUrl = new ArrayList<>();
//
//  //开放权限的url
//  private  final String[] excludePaths = {
//      // 静态资源
//      "/favicon.ico", "/static/**",
//      // 登录页或接口
//      "/*/login",
//      // 验证码
//      "/captcha/**",
//      // druid 监控画面
//      "/druid/**",
//      // h2 控制台
//      "/h2-console/**",
//      // swagger
//      "/doc.html", "/swagger-ui.html", "/webjars/**", "/swagger-resources/**",
//      // websocket请求
//      "/websocket",
//      // 任务调度
//      // "/quartz/**",
//    };

  /**
   * 注册 Sa-Token 的注解拦截器，打开注解式鉴权功能
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new SaAnnotationInterceptor()).addPathPatterns("/**");
  }

  /**
   * 注册 [Sa-Token全局过滤器]
   */
//  @Bean
//  public SaServletFilter getSaServletFilter() {
//    String [] str = SaTokenExFilterUrl.toArray(new String[SaTokenExFilterUrl.size()]);
//    //集合转成,字符串
//    str = ArrayUtil.addAll(excludePaths, str);
//    return new SaServletFilter()
//
//        // 指定 拦截路由
//        .addInclude("/**")
//
//        // 指定 放行路由
//        .addExclude(str)
//
//        // 认证函数: 每次请求执行
//        .setAuth(r -> {
//          SaRouter.match("/**", StpUtil::checkLogin);
//        })
//
//        // 异常处理函数：每次认证函数发生异常时执行此函数
//        .setError(e -> {
//          // e.printStackTrace();
//          if(e instanceof NotLoginException) {
//            SaHolder.getResponse().redirect("/admin/login");
//          }
//          return JSON.toJSONString(new ResultBean<>(e));
//        })
//
//        // 前置函数：在每次认证函数之前执行
//        .setBeforeAuth(r -> {
//          // ---------- 设置跨域响应头 ----------
//          SaHolder.getResponse()
//              // 允许指定域访问跨域资源
//              .setHeader("Access-Control-Allow-Origin", "*")
//              // 允许所有请求方式
//              .setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
//              // 有效时间
//              .setHeader("Access-Control-Max-Age", "3600")
//              // 允许的header参数
//              .setHeader("Access-Control-Allow-Headers", "x-requested-with,satoken");
//
//          // 如果是预检请求，直接返回
//          if ("OPTIONS".equals(SaHolder.getRequest().getMethod())) {
//            System.out.println("=======================浏览器发来了OPTIONS预检请求==========");
//            SaRouter.back();
//          }
//        })
//        ;
//  }

}
