package com.ric.scaffold.domain.service.impl;

import com.ric.scaffold.core.beans.PageResp;
import com.ric.scaffold.domain.dto.UserDto;
import com.ric.scaffold.domain.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceService implements IUserService {
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
