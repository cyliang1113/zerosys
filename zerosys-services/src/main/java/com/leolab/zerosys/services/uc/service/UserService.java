package com.leolab.zerosys.services.uc.service;

import com.leolab.zerosys.common.utils.R;
import com.leolab.zerosys.services.uc.dto.UserDTO;

/**
 * <p>
 * 用户中心 用户表 服务类
 * </p>
 *
 * @author leo
 * @since 2020-05-01
 */
public interface UserService {

    UserDTO getUserByUsername(String username);

    R<UserDTO> usernamePasswordVerify(String username, String password);
}
