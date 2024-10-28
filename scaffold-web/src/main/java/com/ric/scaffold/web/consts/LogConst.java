package com.ric.scaffold.web.consts;

/**
 *  日志注解里面的常量，自己增加即可
 *
 * @author ric
 */
public interface LogConst {
  /**
   *  操作
   *  为了节省日志文件大小，这些常量可以使用单字母代替
   */
  String ACTION_ADD = "A";

  String ACTION_DELETE = "D";

  String ACTION_UPDATE = "U";

  String ACTION_QUERY= "Q";



  String ITEM_TYPE_MODULE = "Web";

}