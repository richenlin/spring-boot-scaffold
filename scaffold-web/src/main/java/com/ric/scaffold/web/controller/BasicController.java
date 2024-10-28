/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ric.scaffold.web.controller;
import com.ric.scaffold.core.beans.PageReq;
import com.ric.scaffold.core.beans.PageResp;
import com.ric.scaffold.core.beans.ResultBean;
import com.ric.scaffold.domain.user.service.IUserService;
import com.ric.scaffold.web.consts.LogConst;
import com.ric.scaffold.core.annotations.Log;
import com.ric.scaffold.domain.user.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Controller
@Slf4j
public class BasicController {

    @Autowired
    private IUserService userService;

    /**
     * http://127.0.0.1:8080/hello?name=lisi
     * @param name
     * @return
     */
    @RequestMapping("/hello")
    @ResponseBody
    @Log(action = LogConst.ACTION_QUERY, itemType = LogConst.ITEM_TYPE_MODULE, param = "#name")
    public ResultBean<String> hello(@RequestParam(name = "name", defaultValue = "unknown user") String name) {
        return new ResultBean<>("Hello " + name);
    }

    /**
     * http://127.0.0.1:8080/getUser?name=lisi
     * @return
     */
    @GetMapping("/getUser")
    @Log(action = LogConst.ACTION_QUERY, itemType = LogConst.ITEM_TYPE_MODULE, param = "#name")
    public ResultBean<UserDto> getUser(@RequestParam(name = "name", defaultValue = "") String name) {
        return new ResultBean<>(userService.getUser(name));
    }

    @PostMapping(value = "/listUser")
    @Log(action = LogConst.ACTION_QUERY, itemType = LogConst.ITEM_TYPE_MODULE, param = "#param")
    public ResultBean<PageResp<UserDto>> listUser(PageReq param) {
        return new ResultBean<>(userService.listPage(param.toPageable(), param.getKeyword()));
    }

    @PostMapping("/addUser")
    @Log(action = LogConst.ACTION_ADD, itemType = LogConst.ITEM_TYPE_MODULE,  itemId = "#UserDto.name")
    public ResultBean<Long> addUser(@RequestBody @Valid UserDto u) {
        return new ResultBean<>(userService.add(u));
    }

    @PostMapping("/updateUser")
    @Log(action = LogConst.ACTION_DELETE, itemType = LogConst.ITEM_TYPE_MODULE, itemId = "#id")
    public ResultBean<Boolean> updateUser(@RequestParam long id) {
        return new ResultBean<>(userService.update(id));
    }

    @PostMapping("/deleteUser")
    @Log(action = LogConst.ACTION_DELETE, itemType = LogConst.ITEM_TYPE_MODULE, itemId = "#id")
    public ResultBean<Boolean> deleteUser(@RequestParam long id) {
        return new ResultBean<>(userService.delete(id));
    }

}
