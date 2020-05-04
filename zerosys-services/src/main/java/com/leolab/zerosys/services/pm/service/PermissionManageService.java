package com.leolab.zerosys.services.pm.service;

import com.leolab.zerosys.services.pm.dto.PermissionDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 权限管理 权限表 服务类
 * </p>
 *
 * @author leo
 * @since 2020-05-03
 */
public interface PermissionManageService {

    List<PermissionDTO> getInterfacePermissionListByUserId(Integer userId);
}
