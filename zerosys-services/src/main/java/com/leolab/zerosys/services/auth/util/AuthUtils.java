package com.leolab.zerosys.services.auth.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

import static com.leolab.zerosys.services.auth.constant.AuthConstant.BEARER_TYPE;
import static com.leolab.zerosys.services.auth.constant.AuthConstant.HTTP_HEADER_AUTHORIZATION;

/**
 */
@Slf4j
public class AuthUtils {

	public static String extractHttpHeaderBearerAuthorization(HttpServletRequest request) {
		String header = request.getHeader(HTTP_HEADER_AUTHORIZATION);
		if (header != null && header.startsWith(BEARER_TYPE)) {
			String[] strings = header.split(" ");
			if (strings.length == 2 && BEARER_TYPE.equals(strings[0])) {
				return strings[1];
			}
		}
		return null;
	}
}
