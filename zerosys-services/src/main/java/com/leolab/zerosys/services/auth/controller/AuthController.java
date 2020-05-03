package com.leolab.zerosys.services.auth.controller;

import com.leolab.zerosys.common.utils.R;
import com.leolab.zerosys.services.auth.dto.AuthReps;
import com.leolab.zerosys.services.auth.dto.UPLoginReq;
import com.leolab.zerosys.services.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/auth/login")
    public R<AuthReps> login(@RequestBody UPLoginReq upLoginReq) {
        return authService.usernamePasswordLogin(upLoginReq);
    }

}
