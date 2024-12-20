package com.ric.scaffold.core.aop;

import com.ric.scaffold.core.beans.ResultBean;
import com.ric.scaffold.core.consts.ResultCode;
import com.ric.scaffold.core.exceptions.CheckException;
import com.ric.scaffold.core.exceptions.UnloginException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 处理和包装异常
 *
 */
@Aspect
@Component
@Order(-99)
@Slf4j
public class ExceptionAspect {

  @Pointcut("execution(public com.ric.scaffold.core.beans.ResultBean *(..))")
  public void controllerMethod() {
  }

  @Around("controllerMethod()")
  public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
    ResultBean<?> result;
    try {
      result = (ResultBean<?>) pjp.proceed();
    } catch (Throwable e) {
      result = handlerException(pjp, e);
    }

    return result;
  }

  private ResultBean<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
    ResultBean<?> result = new ResultBean<>();

    // 已知异常
    if (e instanceof CheckException) {
      result.setMessage(e.getLocalizedMessage());
      result.setCode(ResultCode.FAIL.getCode());
    }
    // 自己抛出的
    else if (e instanceof UnloginException) {
      result.setMessage("Unlogin");
      result.setCode(ResultCode.NO_LOGIN.getCode());
    }
    //其他定义异常
//    else if (e instanceof AuthorizationException) {
//      result.setMessage("Unlogin");
//      result.setCode(ResultCode.NO_LOGIN.getCode());
//    }
    else {
      log.error(pjp.getSignature() + " error ", e);

      //TODO 未知的异常，应该格外注意，可以发送邮件通知等
      result.setMessage(e.toString());
      result.setCode(ResultCode.FAIL.getCode());
    }

    return result;
  }
}
