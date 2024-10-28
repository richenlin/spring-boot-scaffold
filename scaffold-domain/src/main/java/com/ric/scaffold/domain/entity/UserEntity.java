package com.ric.scaffold.domain.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ric.scaffold.core.entity.AbstractEntity;
import lombok.Data;

@Data
@TableName("sys_user")
public class UserEntity extends AbstractEntity {

  @TableField("username")
  private String username;

  @TableField("password")
  private String password;

  @TableField("nick_name")
  private String nickName;

  @TableField("phone")
  private String phone;

  @TableField("email")
  private String email;
}
