package com.leolab.zerosys.services.auth.util;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static com.leolab.zerosys.common.constant.CommonConstant.ZERO_SYS;

/**
 * 安全工具类
 */
public class SecurityUtils {

	/**
	 * 获取Authentication
	 */
	public static Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	/**
	 * 获取当前用户名
	 */
	public static String getUsername() {
		Authentication authentication = getAuthentication();
		if (authentication == null) {
			return ZERO_SYS;
		}
		return String.valueOf(authentication.getPrincipal());
	}


}
