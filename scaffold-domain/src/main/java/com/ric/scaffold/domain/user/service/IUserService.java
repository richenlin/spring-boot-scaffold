package com.ric.scaffold.domain.user.service;

import com.ric.scaffold.core.beans.PageResp;
import com.ric.scaffold.domain.user.dto.UserDto;
import org.springframework.data.domain.Pageable;

public interface IUserService {

  PageResp<UserDto> listPage(Pageable pageable, String keyword);

  UserDto getUser(String name);

  Throwable add(UserDto u);

  Throwable update(long id);

  Throwable delete(long id);
}
