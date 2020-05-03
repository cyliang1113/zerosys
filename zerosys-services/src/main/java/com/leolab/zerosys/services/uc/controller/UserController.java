package com.leolab.zerosys.services.uc.controller;


import com.leolab.zerosys.services.uc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

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

}
