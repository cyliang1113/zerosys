package com.leolab.zerosys.common.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leolab.zerosys.common.constant.FailMsgEnum;
import lombok.Data;

import java.io.Serializable;

import static com.leolab.zerosys.common.constant.CommonConstant.*;

@Data
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code;

    private String msg;

    private Object data;

    public R() {
    }

    public R(T data) {
        this.code = SUCCESS;
        this.msg = SUCCESS_MSG;
        this.data = data;
    }

    public R(FailMsgEnum failMsgEnum) {
        this.code = failMsgEnum.getCode();
        this.msg = failMsgEnum.getMsg();
    }

    public R(R r) {
        this.code = r.getCode();
        this.msg = r.getMsg();
    }

    @JsonIgnore
    public Boolean isSuccess() {
        if (SUCCESS == this.code) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
