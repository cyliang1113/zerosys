package com.leolab.zerosys.services.pm.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leolab.zerosys.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
* <p>
    * 权限管理 角色表
    * </p>
*
* @author leo
* @since 2020-05-03
*/
    @Data
        @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    @TableName("pm_role")
    public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;

            /**
            * 角色名称
            */
    private String roleName;

            /**
            * 角色代码
            */
    private String roleCode;

            /**
            * 备注
            */
    private String memo;

            /**
            * 创建时间
            */
    private LocalDateTime createTime;

            /**
            * 创建人
            */
    private String createBy;

            /**
            * 修改时间
            */
    private LocalDateTime updateTime;

            /**
            * 修改人
            */
    private String updateBy;

            /**
            * 逻辑删除标记
            */
        @TableLogic
    private Boolean deleteFlag;


}
