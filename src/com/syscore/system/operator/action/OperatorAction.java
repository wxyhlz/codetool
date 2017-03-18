/**   
 * @Package	: com.dotblue.system.operator.controller 
 * @Title	: OperatorController.java 
 * @date 	: 2014-1-6 下午04:51:07 
 * @author	: cuiqf
 * @email	: sign1980@163.com
 * @version : V1.0  
 */ 

package com.syscore.system.operator.action;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.syscore.commons.action.BasAction;
import com.syscore.system.operator.model.OperatorInfo;
import com.syscore.system.operator.service.IOperatorService;
import com.syscore.system.sysuser.model.SysuserInfo;

/** 
 * @Description	: 操作员Controller 
 * @ClassName: OperatorController 
 * @author	 : cuiqf
 * @email	 : sign1980@163.com
 * @modified :
 * @modfTime : 
 */
public class OperatorAction extends BasAction<OperatorInfo, IOperatorService>{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户登陆
	 * @param request
	 * @return
	 */
	public String findLogin(){
				
		String accountCode = getParameter("accountCode");
		String password = getParameter("password");
		String captcha = getParameter("captcha").toUpperCase();
		String vCaptcha = (String)getSession("piccode");
		
		OperatorInfo opInfo = new OperatorInfo();
		opInfo.setAccountCode(accountCode);
		opInfo.setPassword(password);
		opInfo.setCaptcha(captcha);
		opInfo.setSessionCaptcha(vCaptcha);
		
		Map<String,Object> resultMap = service.findLogin(opInfo);
		resultMap.put("url",String.valueOf("webframe.action"));
		
		//登陆信息保存到Session
		SysuserInfo user = (SysuserInfo) resultMap.get("user");
		setSession("user", user);
				
		getBadusResponse().setDataMap(resultMap);
		getBadusResponse().setSuccessed(true);		
		return SUCCESS; 
	}
	
	/**
	 * 用户所能操作的模块
	 * @param request
	 * @return
	 */
	public String findModules(){
		SysuserInfo userInfo = (SysuserInfo) getSession("user");
		Map<String,Object> resultMap = service.findModules(userInfo);
		
		getBadusResponse().setDataMap(resultMap);
		getBadusResponse().setSuccessed(true);		
		return SUCCESS;
	}
	
	/**
	 * 账号退出
	 * @param request
	 * @return
	 */ 
	public String findOut(){
		HttpSession session = getRequest().getSession();
		session.removeAttribute("account");
		getBadusResponse().setSuccessed(true);		
		return SUCCESS;
	}

}
