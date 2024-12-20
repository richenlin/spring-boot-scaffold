package com.ric.scaffold.core.utils;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author：ric
 * @date 2023-04-06 17:30
 * @description SpringContext 上下文工具类
 */
public class SpringContextUtil implements ApplicationContextAware {

  private static ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    SpringContextUtil.applicationContext = applicationContext;
  }

  public static <T> T getBean(Class<T> clazz) {
    checkApplicationContext();
    return applicationContext.getBean(clazz);
  }

  public static <T> T getBean(String beanName) {
    checkApplicationContext();
    return (T) applicationContext.getBean(beanName);
  }

  public static <T> T getBean(String beanName, Class<T> clazz) {
    checkApplicationContext();
    return applicationContext.getBean(beanName, clazz);
  }

  private static void checkApplicationContext() {
    if (applicationContext == null) {
      throw new IllegalStateException("applicationContext 未注入");
    }
  }
}
