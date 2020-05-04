package com.leolab.zerosys.services.pm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.leolab.zerosys.services.pm.dto.PermissionDTO;
import com.leolab.zerosys.services.pm.entity.UserRole;
import com.leolab.zerosys.services.pm.mapper.UserRoleMapper;
import com.leolab.zerosys.services.pm.service.RolePermissionService;
import com.leolab.zerosys.services.pm.service.UserRoleService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 权限管理 用户角色表 服务实现类
 * </p>
 *
 * @author leo
 * @since 2020-05-03
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<UserRole> getUserRoleListByUserId(Integer userId) {
        LambdaQueryWrapper<UserRole> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UserRole::getUserId, userId);
        return userRoleMapper.selectList(wrapper);
    }
}
