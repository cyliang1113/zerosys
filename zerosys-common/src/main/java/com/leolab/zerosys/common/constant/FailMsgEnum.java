package com.leolab.zerosys.common.constant;

import lombok.Getter;

/**
 * 失败信息
 */
public enum FailMsgEnum {


    username_or_password_mistake(10002, "用户名或密码错误"),

    ;

    FailMsgEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Getter
    private Integer code;
    @Getter
    private String msg;

}
