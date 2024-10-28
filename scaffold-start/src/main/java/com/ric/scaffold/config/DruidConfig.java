package com.ric.scaffold.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DruidConfig {
  @Value("${druid.loginUsername}")
  private String loginUsername;
  @Value("${druid.loginPassword}")
  private String loginPassword;

  @Bean
  public ServletRegistrationBean<StatViewServlet> druidStatViewServlet() {
    ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
    // 设置Druid控制台的登录用户名和密码
    servletRegistrationBean.addInitParameter("loginUsername", loginUsername);
    servletRegistrationBean.addInitParameter("loginPassword", loginPassword);
    servletRegistrationBean.addInitParameter("resetEnable", "false");
    return servletRegistrationBean;
  }

  @Bean
  public FilterRegistrationBean<WebStatFilter> druidWebStatFilter() {
    FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<>(new WebStatFilter());
    filterRegistrationBean.addUrlPatterns("/*");
    filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
    return filterRegistrationBean;
  }
}