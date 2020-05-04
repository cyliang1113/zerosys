package com.leolab.zerosys.services.uc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.leolab.zerosys.common.constant.FailMsgEnum;
import com.leolab.zerosys.common.utils.BeanUtils;
import com.leolab.zerosys.common.utils.R;
import com.leolab.zerosys.services.uc.dto.UserDTO;
import com.leolab.zerosys.services.uc.entity.User;
import com.leolab.zerosys.services.uc.mapper.UserMapper;
import com.leolab.zerosys.services.uc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户中心 用户表 服务实现类
 * </p>
 *
 * @author leo
 * @since 2020-05-01
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    @Override
    @Transactional
    public UserDTO getUserByUsername(String username) {
        User user = getUserByUsernameInner(username);
        UserDTO userDTO = BeanUtils.copyObject(user, UserDTO.class);
        return userDTO;
    }

    /**
     * 用户名密码验证
     * @param username
     * @param password
     * @return
     */
    @Override
    @Transactional
    public R<UserDTO> usernamePasswordVerify(String username, String password) {
        User userByUsernameInner = getUserByUsernameInner(username);
        if (userByUsernameInner != null) {
            PasswordEncoder passwordEncoder = NoOpPasswordEncoder.getInstance();
            if (passwordEncoder.matches(password, userByUsernameInner.getPassword())) {
                UserDTO userDTO = BeanUtils.copyObject(userByUsernameInner, UserDTO.class);
                return new R(userDTO);
            }
        }
        return new R(FailMsgEnum.username_or_password_mistake);
    }

    private User getUserByUsernameInner(String username) {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(User::getUsername, username);
        User user = userMapper.selectOne(wrapper);
        return user;
    }
}
