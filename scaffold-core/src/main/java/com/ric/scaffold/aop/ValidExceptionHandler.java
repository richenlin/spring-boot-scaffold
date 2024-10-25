package com.ric.scaffold.aop;

import com.ric.scaffold.beans.ResultBean;
import com.ric.scaffold.consts.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 处理hibernate valid异常
 *
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class ValidExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResultBean<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

    // 异常信息
    String msg = extractErrorMsg(e.getBindingResult());

    // 返回对象
    ResultBean<Object> resultBean = new ResultBean<>();

    resultBean.setCode(ResultCode.FAIL.getCode());
    resultBean.setMessage(msg);

    return resultBean;
  }


  /**
   * 异常信息
   *
   * @param result
   * @return
   */
  private String extractErrorMsg(BindingResult result) {
    List<FieldError> errors = result.getFieldErrors();

    return errors.stream().map(e -> e.getField()+ ":" + e.getDefaultMessage())
        .reduce((s1, s2) -> s1 + " ; " +s2).orElseGet( ()->"参数非法");
  }
}