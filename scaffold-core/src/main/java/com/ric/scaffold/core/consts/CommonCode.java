package com.ric.scaffold.core.consts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 公共常量定义
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum CommonCode {

  NORMAL("0", "正常"),
  DELETED("1", "删除"),

  ENABLE("0", "启用"),
  DISABLE("1", "禁用"),

  TRACE_ID("TRACE_ID", "日志追踪ID"),
  ;

  /** 编码 */
  private String code;
  /** 属性 */
  private String name;
}
