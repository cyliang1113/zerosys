package com.leolab.zerosys.services.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis plus配置
 */
@Configuration
@MapperScan({"com.leolab.zerosys.services.uc.mapper", "com.leolab.zerosys.services.pm.mapper"})
public class MybatisPlusConfig {

}
