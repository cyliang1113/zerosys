package com.leolab.zerosys.services.pm.service.impl;

import com.leolab.zerosys.services.pm.dto.PermissionDTO;
import com.leolab.zerosys.services.pm.entity.UserRole;
import com.leolab.zerosys.services.pm.service.PermissionManageService;
import com.leolab.zerosys.services.pm.service.RolePermissionService;
import com.leolab.zerosys.services.pm.service.UserRoleService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 权限管理 service
 */
@Service
public class PermissionManageServiceImpl implements PermissionManageService {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Transactional
    @Override
    public List<PermissionDTO> getInterfacePermissionListByUserId(Integer userId) {
        List<UserRole> userRoleList = userRoleService.getUserRoleListByUserId(userId);
        if (CollectionUtils.isEmpty(userRoleList)) {
            return new LinkedList<>();
        }
        List<Integer> roleIdList = userRoleList.stream().map(item -> item.getRoleId()).collect(Collectors.toList());
        List<PermissionDTO> interfacePermissionListByRoleIdList = rolePermissionService.getInterfacePermissionListByRoleIdList(roleIdList);
        return interfacePermissionListByRoleIdList;
    }
}
