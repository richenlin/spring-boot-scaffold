package com.ric.scaffold.beans;

import lombok.Data;

@Data
public class User {
  private static final long serialVersionUID = 1L;

  private Long id;

  private String name;

  private String nick;
}
