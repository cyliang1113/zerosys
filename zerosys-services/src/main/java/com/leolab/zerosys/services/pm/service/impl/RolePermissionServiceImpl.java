package com.leolab.zerosys.services.pm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.leolab.zerosys.services.pm.dto.PermissionDTO;
import com.leolab.zerosys.services.pm.entity.Permission;
import com.leolab.zerosys.services.pm.entity.RolePermission;
import com.leolab.zerosys.services.pm.entity.UserRole;
import com.leolab.zerosys.services.pm.mapper.RolePermissionMapper;
import com.leolab.zerosys.services.pm.service.PermissionService;
import com.leolab.zerosys.services.pm.service.RolePermissionService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 权限管理 角色权限表 服务实现类
 * </p>
 *
 * @author leo
 * @since 2020-05-03
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private PermissionService permissionService;

    @Override
    @Transactional
    public List<PermissionDTO> getInterfacePermissionListByRoleIdList(List<Integer> roleIdList) {
        if (CollectionUtils.isEmpty(roleIdList)) {
            return new LinkedList<>();
        }
        List<RolePermission> rolePermissions = getRolePermissionListByRoleIdList(roleIdList);
        List<Integer> permissionIdList = rolePermissions.stream().map(item -> item.getPermissionId()).collect(Collectors.toList());
        List<PermissionDTO> permissionDTOList = permissionService.getInterfacePermissionListByPermissionIdList(permissionIdList);
        return permissionDTOList;
    }

    private List<RolePermission> getRolePermissionListByRoleIdList(List<Integer> roleIdList) {
        if (CollectionUtils.isEmpty(roleIdList)) {
            return new LinkedList<>();
        }
        LambdaQueryWrapper<RolePermission> wrapper = Wrappers.lambdaQuery();
        wrapper.in(RolePermission::getRoleId, roleIdList);
        List<RolePermission> rolePermissions = rolePermissionMapper.selectList(wrapper);
        if (CollectionUtils.isEmpty(rolePermissions)) {
            return new LinkedList<>();
        }
        return rolePermissions;
    }
}
