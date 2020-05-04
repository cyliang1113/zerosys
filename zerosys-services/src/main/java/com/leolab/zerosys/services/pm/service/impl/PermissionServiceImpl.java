package com.leolab.zerosys.services.pm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.leolab.zerosys.common.utils.BeanUtils;
import com.leolab.zerosys.services.pm.constant.PermissionConstant;
import com.leolab.zerosys.services.pm.dto.PermissionDTO;
import com.leolab.zerosys.services.pm.entity.Permission;
import com.leolab.zerosys.services.pm.mapper.PermissionMapper;
import com.leolab.zerosys.services.pm.service.PermissionService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 权限管理 权限表 服务实现类
 * </p>
 *
 * @author leo
 * @since 2020-05-03
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 查询接口权限
     * @param permissionIdList
     * @return
     */
    @Transactional
    @Override
    public List<PermissionDTO> getInterfacePermissionListByPermissionIdList(List<Integer> permissionIdList) {
        if (CollectionUtils.isEmpty(permissionIdList)) {
            return new LinkedList<>();
        }
        List<Permission> permissions = getInterfacePermissionListByPermissionIdListInner(permissionIdList);
        List<PermissionDTO> permissionDTOList = permissions.stream()
                .map(item -> BeanUtils.copyObject(item, PermissionDTO.class))
                .collect(Collectors.toList());
        return permissionDTOList;
    }

    private List<Permission> getInterfacePermissionListByPermissionIdListInner(List<Integer> permissionIdList) {
        if (CollectionUtils.isEmpty(permissionIdList)) {
            return new LinkedList<>();
        }
        LambdaQueryWrapper<Permission> wrapper = Wrappers.lambdaQuery();
        wrapper.in(Permission::getId, permissionIdList);
        wrapper.in(Permission::getType, PermissionConstant.PermissionTypeEnum.INTERFACE.name());
        List<Permission> permissions = permissionMapper.selectList(wrapper);
        if (CollectionUtils.isEmpty(permissions)) {
            return new LinkedList<>();
        }
        return permissions;
    }
}
