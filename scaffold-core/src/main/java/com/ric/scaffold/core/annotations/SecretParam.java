package com.ric.scaffold.core.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author：ric
 * @date 2023-04-28 9:58
 * @description 加解密请求参数注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SecretParam {

  boolean encrypt() default true;

  boolean decrypt() default true;
}
