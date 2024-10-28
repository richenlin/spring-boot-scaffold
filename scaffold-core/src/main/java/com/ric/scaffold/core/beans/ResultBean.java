package com.ric.scaffold.core.beans;

import java.io.Serializable;

import com.ric.scaffold.core.consts.ResultCode;
import lombok.Data;

/**
 * @author ric
 */
@Data
public class ResultBean<T> implements Serializable {

  private static final long serialVersionUID = 1L;

  private T data;

  public String message = "success";

  public int code = ResultCode.SUCCESS.getCode();

  public ResultBean() {
    super();
  }

  public ResultBean(T data) {
    super();
    this.data = data;
  }

  public ResultBean(Throwable e) {
    super();
    ResultCode errCode = ResultCode.FAIL;
    this.message = e.toString();
    this.code = errCode.getCode();
  }

  public ResultBean(ResultCode code, Throwable e) {
    super();
    this.message = e.toString();
    this.code = code.getCode();
  }

  public ResultBean(ResultCode code, String message) {
    super();
    this.message = message;
    this.code = code.getCode();
  }

}
