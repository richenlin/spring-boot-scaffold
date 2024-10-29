package com.ric.scaffold.core.aop;

import com.alibaba.fastjson2.JSONObject;
import com.ric.scaffold.core.beans.UserBean;
import com.ric.scaffold.core.config.SensitiveConfig;
import com.ric.scaffold.core.utils.UserUtil;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 *  日志信息处理AOP
 *  把需要的信息从参数中提取出来，转成json字符串放到MDC中使用。
 *
 *  注意：注解不支持重入（就是嵌套的方法里面还有LOG注解），因为我觉得不需要嵌套
 *  如果你项目中有这种使用场景，自己修改一下也非常简单，就是修改前保存起来即可。
 *
 */
@Aspect
@Component
public class LoggingAspect {

  private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
  // 定义 MDC 的键
  private static final String JSON_KEY = "logInfo";
  // 注入配置类
  @Autowired
  private SensitiveConfig sensitiveConfig;

  @Around("execution(* com.ric.scaffold.web.controller..*(..)) || execution(* com.ric.scaffold.api.controller..*(..))")
  @SneakyThrows
  public Object handleLogMethod(ProceedingJoinPoint pjp) {
    long startTime = System.currentTimeMillis();
    Object result;

    try {
      // 获取当前请求
      ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
      assert attributes != null;
      HttpServletRequest request = attributes.getRequest();

      putLogInfo2MDC(request);

      // 仅在记录日志时获取请求参数
      LinkedHashMap<String, Object> params = getRequestParameters(request);

      logger.info("[{}] Request: {}, start: {}", pjp.getSignature(), params, startTime);
      result = pjp.proceed();
      long endTime = System.currentTimeMillis();
      long elapsedTime = endTime - startTime;
      logger.info("[{}] Response: {}, end: {}, elapsed: {}ms", pjp.getSignature(), result, endTime, elapsedTime);
    } finally {
      clearMDC();
    }

    return result;
  }

  private void clearMDC() {
    MDC.remove(JSON_KEY);
  }

  private void putLogInfo2MDC(HttpServletRequest request) {
    JSONObject json = new JSONObject();
    UserBean userBean = UserUtil.getUserIfLogin();

    if (userBean != null) {
      json.put("User", userBean.getName());
    }

    json.put("URL", request.getRequestURL());
    json.put("Method", request.getMethod());
    json.put("Addr", request.getRemoteAddr());

    MDC.put(JSON_KEY, json.toJSONString());
  }

  private LinkedHashMap<String, Object> getRequestParameters(HttpServletRequest request) {
    LinkedHashMap<String, Object> params = new LinkedHashMap<>();

    Enumeration<String> parameterNames = request.getParameterNames();
    while (parameterNames.hasMoreElements()) {
      String paramName = parameterNames.nextElement();
      String paramValue = request.getParameter(paramName);

      // 过滤敏感参数
      for (String sensitiveWord : sensitiveConfig.getWords()) {
        if (sensitiveWord.equals(paramName)) {
          // 隐藏敏感信息
          paramValue = "****";
          break;
        }
      }
      params.put(paramName, paramValue);
    }

    return params;
  }
}