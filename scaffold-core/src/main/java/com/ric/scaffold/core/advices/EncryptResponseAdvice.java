package com.ric.scaffold.core.advices;


import com.ric.scaffold.core.annotations.SecretParam;
import com.ric.scaffold.core.beans.ResultBean;
import com.alibaba.fastjson2.JSON;
import com.ric.scaffold.core.consts.ResultCode;
import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import com.ric.scaffold.core.utils.RSAUtil;

/**
 * @author：ric
 * @date 2023-04-28 10:33
 * @description 响应参数加密
 */
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class EncryptResponseAdvice implements ResponseBodyAdvice<Object> {

  @Override
  public boolean supports(MethodParameter methodParameter, Class aClass) {
    return methodParameter.hasMethodAnnotation(SecretParam.class);
  }

  @Override
  public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
    if (methodParameter.hasMethodAnnotation(SecretParam.class)) {
      SecretParam secretParam = methodParameter.getMethodAnnotation(SecretParam.class);
      if (secretParam.encrypt()) {
        ResultBean result = (ResultBean) body;
        if (result.getCode() == ResultCode.SUCCESS.getCode()) {
          String responseString = JSON.toJSONString(result.getData());
          String encryptResponseString = RSAUtil.encrypt(responseString, "");
          result.setData(encryptResponseString);

          body = result;
        }
      }
    }
    return body;
  }
}
