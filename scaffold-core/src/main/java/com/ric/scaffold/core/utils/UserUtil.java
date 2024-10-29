package com.ric.scaffold.core.utils;

import com.ric.scaffold.core.beans.UserBean;
import com.ric.scaffold.core.exceptions.UnloginException;
import org.slf4j.MDC;

import java.util.Locale;

/**
 * 用户工具类
 *
 * @author ric
 */
public class UserUtil {

  private final static ThreadLocal<UserBean> TL_USER = new ThreadLocal<UserBean>();

  private final static ThreadLocal<Locale> TL_LOCALE = new ThreadLocal<Locale>() {
    @Override
    protected Locale initialValue() {
      // 语言的默认值
      return Locale.CHINESE;
    }

    ;
  };

  public static final String KEY_LANG = "lang";

  public static final String KEY_USER = "user";

  public static void setUser(UserBean userBean) {
    TL_USER.set(userBean);

    // 把用户信息放到log4j
    MDC.put(KEY_USER, userBean.getName());
  }

  /**
   * 如果没有登录，返回null
   *
   * @return
   */
  public static UserBean getUserIfLogin() {
    return TL_USER.get();
  }

  /**
   * 如果没有登录会抛出异常
   *
   * @return
   */
  public static UserBean getUser() {
    UserBean userBean = TL_USER.get();

    if (userBean == null) {
      throw new UnloginException();
    }

    return userBean;
  }

  public static long getUserId() {
    return getUser().getId();
  }

  public static void setLocale(String locale) {
    setLocale(new Locale(locale));
  }

  public static void setLocale(Locale locale) {
    TL_LOCALE.set(locale);
  }

  public static Locale getLocale() {
    return TL_LOCALE.get();
  }

  public static void clearAllUserInfo() {
    TL_USER.remove();
    TL_LOCALE.remove();

    MDC.remove(KEY_USER);
  }
}
