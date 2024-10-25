package beans;

import java.io.Serializable;

import consts.ResultCode;
import lombok.Data;

/**
 * @author ric
 */
@Data
public class ResultBean<T> implements Serializable {

  /** 返回码 */
  private Integer code;

  /** 返回信息 */
  private String message;

  /** 返回数据 */
  private T data;

  public ResultBean() {

  }

  public static <T> ResultBean<T> build(Integer code, String message, T data) {
    ResultBean<T> result = new ResultBean<>();

    if (data != null) {
      result.setData(data);
    }

    result.setCode(code);
    result.setMessage(message);

    return result;
  }

  public static <T> ResultBean<T> build(ResultCode resultCode, T data) {
    return build(resultCode.getCode(), resultCode.getMessage(), data);
  }

  public static <T> ResultBean<T> success(T data) {
    return build(ResultCode.SUCCESS, data);
  }

  public static <T> ResultBean<T> success(Integer code, String message) {
    return fail(code, message, null);
  }

  public static <T> ResultBean<T> success(Integer code, String message, T data) {
    return build(code, message, data);
  }

  public static <T> ResultBean<T> fail(T data) {
    return build(ResultCode.FAIL, data);
  }

  public static <T> ResultBean<T> fail(Integer code, String message) {
    return fail(code, message, null);
  }

  public static <T> ResultBean<T> fail(Integer code, String message, T data) {
    return build(code, message, data);
  }

}
