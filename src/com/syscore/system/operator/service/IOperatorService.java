/**   
 * @Package	: com.dotblue.system.operator.service 
 * @Title	: OperatorService.java 
 * @date 	: 2014-1-6 下午04:50:36 
 * @author	: cuiqf
 * @email	: sign1980@163.com
 * @version : V1.0  
 */ 

package com.syscore.system.operator.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.syscore.commons.service.IBasService;
import com.syscore.system.operator.model.OperatorInfo;
import com.syscore.system.sysuser.model.SysuserInfo;
 

/** 
 * @Description	:  
 * @ClassName: OperatorService 
 * @author	 : cuiqf
 * @email	 : sign1980@163.com
 * @modified :
 * @modfTime : 
 */

@Service
public interface IOperatorService extends IBasService<OperatorInfo> { 
	
	/**
	 * 用户登录
	 * @param entityInfo
	 * @return
	 */
	public Map<String,Object> findLogin(OperatorInfo entityInfo);
	
	/**
	 * 用户所能操作的模块
	 * @param entityInfo
	 * @return
	 */
	public Map<String,Object> findModules(SysuserInfo entityInfo);

}
