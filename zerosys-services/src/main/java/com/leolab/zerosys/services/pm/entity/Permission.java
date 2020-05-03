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
    * 权限管理 权限表
    * </p>
*
* @author leo
* @since 2020-05-03
*/
    @Data
        @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    @TableName("pm_permission")
    public class Permission extends BaseEntity {

    private static final long serialVersionUID = 1L;

            /**
            * 权限类型(menu, button, interface)
            */
    private String type;

            /**
            * 名称
            */
    private String name;

            /**
            * 权限代码
            */
    private String code;

            /**
            * 权限内容
            */
    private String content;

            /**
            * 父菜单ID(类型为menu时才有)
            */
    private Integer parentId;

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
