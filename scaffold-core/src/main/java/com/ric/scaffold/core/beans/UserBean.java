package com.ric.scaffold.core.beans;

import lombok.Data;

@Data
public class UserBean {
  private static final long serialVersionUID = 1L;

  private Long id;

  private String name;

  private String nick;
}
