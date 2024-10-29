package com.ric.scaffold.core.filters;

import com.ric.scaffold.core.beans.UserBean;
import com.ric.scaffold.core.utils.UserUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 用户filter，设置当前用户和语言到threadlocal中。
 *
 *
 * @author ric
 */
@WebFilter(filterName = "userFilter", urlPatterns = "/*")
public class UserFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    fillUserInfo((HttpServletRequest) request);

    try {
      chain.doFilter(request, response);
    } finally {
      clearAllUserInfo();
    }
  }


  private void clearAllUserInfo() {
    UserUtil.clearAllUserInfo();
  }

  private void fillUserInfo(HttpServletRequest request) {
    // 用户信息
    UserBean userBean = getUserFromSession(request);
    //FIXME User user = getUserFromSpringSecurity();

    if (userBean != null) {
      UserUtil.setUser(userBean);
    }

    // 语言信息
    String locale = getLocaleFromCookies(request);

    if (locale != null) {
      UserUtil.setLocale(locale);
    }
  }

  private String getLocaleFromCookies(HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();

    if (cookies == null) {
      return null;
    }

    for (Cookie cookie : cookies) {
      if (UserUtil.KEY_LANG.equals(cookie.getName())) {
        return cookie.getValue();
      }
    }

    return null;
  }

  private UserBean getUserFromSession(HttpServletRequest request) {
    HttpSession session = request.getSession();

    //if (session == null) {
    //	return null;
    //}

    // 从session中获取用户信息放到工具类中
    return (UserBean) session.getAttribute(UserUtil.KEY_USER);
  }

  @Override
  public void destroy() {

  }

}
