package com.leolab.zerosys.services.uc.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.leolab.zerosys.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
* <p>
    * 用户中心 用户表
    * </p>
*
* @author leo
* @since 2020-05-01
*/
    @Data
        @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    @TableName("uc_user")
    public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

            /**
            * 用户名
            */
    private String username;

            /**
            * 姓名
            */
    private String name;

            /**
            * 密码
            */
    private String password;

            /**
            * 手机号
            */
    private String mobile;

            /**
            * 来自
            */
    private String fromWhere;

    /**
     * 注册时间
     */
    private LocalDateTime registerTime;

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
