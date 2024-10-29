package com.ric.scaffold.domain.user.service.impl;

import com.ric.scaffold.core.beans.PageResp;
import com.ric.scaffold.domain.user.dto.UserDto;
import com.ric.scaffold.domain.user.mapper.UserMapper;
import com.ric.scaffold.domain.user.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService implements IUserService {

  /**
   * @param pageable
   * @param keyword
   * @return
   */
  @Override
  public PageResp<UserDto> listPage(Pageable pageable, String keyword) {
    return null;
  }

  /**
   * @param name
   * @return
   */
  @Override
  public UserDto getUser(String name) {
    return null;
  }

  /**
   * @param u
   * @return
   */
  @Override
  public Throwable add(UserDto u) {
    return null;
  }

  /**
   * @param id
   * @return
   */
  @Override
  public Throwable update(long id) {
    return null;
  }

  /**
   * @param id
   * @return
   */
  @Override
  public Throwable delete(long id) {
    return null;
  }
}
