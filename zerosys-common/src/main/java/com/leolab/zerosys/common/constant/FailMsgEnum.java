package com.leolab.zerosys.common.constant;

import lombok.Getter;

/**
 * 失败信息
 */
public enum FailMsgEnum {


    common_fail(10001, "失败"),
    username_or_password_mistake(10002, "用户名或密码错误"),
    username_or_password_not_empty(10003, "用户名或密码不能为空"),

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
