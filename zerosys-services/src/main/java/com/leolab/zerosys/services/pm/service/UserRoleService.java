package com.leolab.zerosys.services.pm.service;

import com.leolab.zerosys.services.pm.dto.PermissionDTO;
import com.leolab.zerosys.services.pm.entity.UserRole;

import java.util.List;

/**
 * <p>
 * 权限管理 用户角色表 服务类
 * </p>
 *
 * @author leo
 * @since 2020-05-03
 */
public interface UserRoleService {

    List<UserRole> getUserRoleListByUserId(Integer userId);
}
