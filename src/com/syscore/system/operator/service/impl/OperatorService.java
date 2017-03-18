/**   
 * @Package	: com.dotblue.system.operator.service 
 * @Title	: OperatorService.java 
 * @date 	: 2014-1-6 下午04:50:36 
 * @author	: cuiqf
 * @email	: sign1980@163.com
 * @version : V1.0  
 */ 

package com.syscore.system.operator.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.syscore.commons.except.BusinessException;
import com.syscore.system.operator.model.OperatorInfo;
import com.syscore.system.operator.service.IOperatorService;
import com.syscore.system.sysmodule.service.ISysmoduleService;
import com.syscore.system.sysuser.model.SysuserInfo;
import com.syscore.system.sysuser.service.ISysuserService;
import com.util.encrypt.EncryptUtil;

/** 
 * @Description	:  
 * @ClassName: OperatorService 
 * @author	 : cuiqf
 * @email	 : sign1980@163.com
 * @modified :
 * @modfTime : 
 */

@Service
public class OperatorService implements IOperatorService{
	
	/**
	 * 用户service
	 */
	private ISysuserService sysuserService;
	
	/**
	 * 模块service
	 */
	private ISysmoduleService sysmoduleService;
	
	public ISysuserService getSysuserService() {
		return sysuserService;
	}

	public void setSysuserService(ISysuserService sysuserService) {
		this.sysuserService = sysuserService;
	}

	public ISysmoduleService getSysmoduleService() {
		return sysmoduleService;
	}

	public void setSysmoduleService(ISysmoduleService sysmoduleService) {
		this.sysmoduleService = sysmoduleService;
	}

	public Map<String,Object> findLogin(OperatorInfo entityInfo){
		//验证码正确
		if(!entityInfo.getCaptcha().equals(entityInfo.getSessionCaptcha())){
			throw new BusinessException("验证码输入不正确");
		}
		//身份验证
		SysuserInfo userInfo = new SysuserInfo();
		userInfo.setCode(entityInfo.getAccountCode());
		userInfo.setPassword(EncryptUtil.Encrypt(entityInfo.getPassword(), null));
		SysuserInfo vUserInfo = sysuserService.findLogin(userInfo);
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("user", vUserInfo);		
		return resultMap;
	}
	
	public Map<String,Object> findModules(SysuserInfo entityInfo){
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("userid", entityInfo.getId());
		paramMap.put("userCode", entityInfo.getCode());
		//说明：人员 - 角色 - 模块，通过sql表关系关联获取模块权限
		Map<String,Object> resutlMap = sysmoduleService.findLoginModules(paramMap);
		
		return resutlMap;
	}

}
