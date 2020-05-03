package com.leolab.zerosys.common.constant;

public interface CommonConstant {
	/**
	 * 删除
	 */
	Boolean DELFLAG_Y = true;
	/**
	 * 正常
	 */
	Boolean DELFLAG_N = false;
	/**
	 * 是
	 */
	String YES = "Y";
	/**
	 * 否
	 */
	String NO = "N";
	/**
	 * 正常
	 */
	String STATUS_NORMAL = "NORMAL";

	/**
	 * 锁定
	 */
	String STATUS_LOCK = "9";

	/**
	 * 菜单
	 */
	String MENU = "0";

	/**
	 * 编码
	 */
	String UTF8 = "UTF-8";

	/**
	 * JSON 资源
	 */
	String CONTENT_TYPE = "application/json; charset=utf-8";

	/**
	 * 前端工程名
	 */
	String FRONT_END_PROJECT = "pig-ui";

	/**
	 * 后端工程名
	 */
	String BACK_END_PROJECT = "pig";

	/**
	 * 成功标记
	 */
	Integer SUCCESS = 10000;

	String SUCCESS_MSG = "成功";
	/**
	 * 失败标记
	 */
	Integer FAIL = 10001;

	String FAIL_MSG = "失败";
	
	/**
	 * 参数验证失败
	 */
	Integer PARAMETER_VALIDATION_FAILED = 10002;
	
	/**
	 *  数据库数据更新失败
	 */
	Integer DATABASE_UPDATE_FAILED = 10003;

	/**
	 * 验证码前缀
	 */
	String DEFAULT_CODE_KEY = "DEFAULT_CODE_KEY_";

	/**
	 * 短信验证码前缀
	 */
	String PHONE_CODE_KEY = "phoneCode:";

	/**
	 * 短信验证码前缀
	 */
	String PHONE_USERNAME = "phone:userName:";
}
