package com.leolab.zerosys.services.auth.service;

import com.leolab.zerosys.common.utils.R;
import com.leolab.zerosys.services.auth.dto.UPLoginReq;
import org.springframework.transaction.annotation.Transactional;

public interface AuthService {
    @Transactional
    R usernamePasswordLogin(UPLoginReq req);
}
