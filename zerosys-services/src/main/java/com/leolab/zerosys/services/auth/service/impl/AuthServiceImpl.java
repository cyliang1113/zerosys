package com.leolab.zerosys.services.auth.service.impl;

import com.leolab.zerosys.common.utils.R;
import com.leolab.zerosys.services.auth.dto.AuthReps;
import com.leolab.zerosys.services.auth.dto.UPLoginReq;
import com.leolab.zerosys.services.auth.service.AuthService;
import com.leolab.zerosys.services.uc.dto.UserDTO;
import com.leolab.zerosys.services.uc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserService userService;

    /**
     * 用户名密码鉴权
     * @param req
     * @return
     */
    @Override
    @Transactional
    public R<AuthReps> usernamePasswordLogin(UPLoginReq req) {
        //验证 用户名密码验证
        R<UserDTO> userDTOR = userService.usernamePasswordVerify(req.getUsername(), req.getPassword());
        if (!userDTOR.isSuccess()) {
            return new R(userDTOR);
        }

        //授权


        return null;
    }
}
