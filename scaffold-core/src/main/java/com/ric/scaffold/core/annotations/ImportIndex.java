package com.ric.scaffold.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author：ric
 * @date 2023-05-09 16:59
 * @description  导入注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ImportIndex {

  /** 索引 */
  int index();
}
