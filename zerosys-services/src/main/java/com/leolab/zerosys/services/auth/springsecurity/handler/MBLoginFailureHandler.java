package com.leolab.zerosys.services.auth.springsecurity.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leolab.zerosys.common.constant.FailMsgEnum;
import com.leolab.zerosys.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.leolab.zerosys.common.constant.CommonConstant.CONTENT_TYPE_JSON;

/**
 * 登录失败
 */
@Slf4j
public class MBLoginFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        log.info("MBLoginFailureHandler start ...");
        R<Object> r = new R<>(FailMsgEnum.common_fail);
        r.setMsg(exception.getMessage());
        String s = objectMapper.writeValueAsString(r);
        response.setContentType(CONTENT_TYPE_JSON);
        response.getWriter().write(s);
    }
}
