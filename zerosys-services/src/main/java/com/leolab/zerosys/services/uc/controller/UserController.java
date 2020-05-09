package com.leolab.zerosys.services.uc.controller;


import com.leolab.zerosys.common.utils.R;
import com.leolab.zerosys.services.uc.dto.UserDTO;
import com.leolab.zerosys.services.uc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户中心 用户表 前端控制器
 * </p>
 *
 * @author leo
 * @since 2020-05-01
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/uc/userList")
    public R<List<UserDTO>> userList() {
        List<UserDTO> userDTOS = userService.userList();
        return new R(userDTOS);
    }

}
