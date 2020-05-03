package com.leolab.zerosys.services.auth.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 用户名密码登录请求参数
 */
@Data
public class UPLoginReq {
    @NotNull
    private String username;

    @NotNull
    private String password;
}
