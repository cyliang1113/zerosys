package com.leolab.zerosys.services.pm.service;

import com.leolab.zerosys.services.pm.dto.PermissionDTO;

import java.util.List;

/**
 * <p>
 * 权限管理 角色权限表 服务类
 * </p>
 *
 * @author leo
 * @since 2020-05-03
 */
public interface RolePermissionService {

    List<PermissionDTO> getInterfacePermissionListByRoleIdList(List<Integer> roleIdList);
}
