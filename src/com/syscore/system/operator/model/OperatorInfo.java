/**   
 * @Package	: com.dotblue.system.operator.model 
 * @Title	: OperatorInfo.java 
 * @date 	: 2014-1-7 上午09:02:13 
 * @author	: cuiqf
 * @email	: sign1980@163.com
 * @version : V1.0  
 */ 

package com.syscore.system.operator.model;

import com.syscore.commons.model.BasInfo;

/** 
 * @Description	:  
 * @ClassName: OperatorInfo 
 * @author	 : cuiqf
 * @email	 : sign1980@163.com
 * @modified :
 * @modfTime : 
 */

public class OperatorInfo extends BasInfo {

	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 账号
	 */
	private String accountCode;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 验证码
	 */
	private String captcha;
	
	/**
	 * Session验证码
	 */
	private String sessionCaptcha;

	/**
	 * @return the accountCode
	 */
	public String getAccountCode() {
		return accountCode;
	}

	/**
	 * @param accountCode the accountCode to set
	 */
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the captcha
	 */
	public String getCaptcha() {
		return captcha;
	}

	/**
	 * @param captcha the captcha to set
	 */
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	/**
	 * @return the sessionCaptcha
	 */
	public String getSessionCaptcha() {
		return sessionCaptcha;
	}

	/**
	 * @param sessionCaptcha the sessionCaptcha to set
	 */
	public void setSessionCaptcha(String sessionCaptcha) {
		this.sessionCaptcha = sessionCaptcha;
	}
	
	

}
