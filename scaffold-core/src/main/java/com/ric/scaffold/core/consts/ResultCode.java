package com.ric.scaffold.core.consts;

import lombok.Getter;

/**
 * @author ric
 */

@Getter
public enum ResultCode {
  //
  SUCCESS(0, "成功"),
  FAIL(1, "失败"),

  NO_LOGIN(2, "没有登录"),

  NO_ACCESS_PRIVILEGES(2, "没有访问权限"),

  VALID_PARAMS_ERROR(3, "参数校验异常");

  private Integer code;
  private String message;

  private ResultCode(Integer code, String message) {
    this.code = code;
    this.message = message;
  }
}
