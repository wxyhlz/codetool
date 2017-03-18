/*
 * 版　权: alaha Copyright 2013-2015,All rights reserved
 * 
 * 文件名: SysmoduleAction.java
 * 
 */
package com.syscore.system.sysmodule.action;

import java.util.HashMap;
import java.util.Map;

import com.syscore.commons.action.BasAction;
import com.syscore.system.sysmodule.model.SysmoduleInfo;
import com.syscore.system.sysmodule.service.ISysmoduleService;

/**
 * @ClassName : SysmoduleAction
 * 
 * @Description : 模块_ACTION
 * 
 * @author tide
 * 
 * @date 2014-08-23
 * 
 * @version 1.0
 *
 */
public class SysmoduleAction extends BasAction<SysmoduleInfo, ISysmoduleService> {

	/**
 	*
 	*/
	private static final long serialVersionUID = 1L;

	/**
	 * 添加数据
	 * @return
	 */
	public String insertSysmodule(){
		//页面参数
		String id = getParameter("id");

		//参数
		SysmoduleInfo info = new SysmoduleInfo();
		info.setId(id);

		//服务请求
		service.insertModule(info);

		//数据返回
		getBadusResponse().setSuccessed(true);
		return SUCCESS;
	}

	/**
	 *删除数据
	 * @return
	 */
	public String deleteSysmodule(){ 
		//页面参数
		String id = getParameter("id"); 

		//参数
		SysmoduleInfo info = new SysmoduleInfo();
		info.setId(id);

		//服务请求
		service.deleteModule(info);

		//数据返回
		getBadusResponse().setSuccessed(true);
		return SUCCESS;
	}

	/**
	 * 修改数据
	 * @return
	 */
	public String updateSysmodule(){ 
		//页面参数
		String id = getParameter("id");

		//参数
		SysmoduleInfo info = new SysmoduleInfo();
		info.setId(id);

		//服务请求
		service.updateModule(info);

		//数据返回
		getBadusResponse().setSuccessed(true);

		return SUCCESS;
	}

	/**
	 * 数据查询
	 * @return
	 */
	public String findSysmoduleByMap(){
		//页面参数
		String id = getParameter("id");

		//参数
		Map<String, Object> paramMap = new HashMap<String, Object>(); 
		paramMap.put("id", id);

		//服务请求
		Map<String, Object> resultMap = service.findModuleByMap(paramMap);

		//数据返回
		getBadusResponse().setDataMap(resultMap);
		getBadusResponse().setSuccessed(true);

		return SUCCESS;
	}

	
	/**
	 * 分页查询
	 * @return
	 */
	public String findSysmoduleByPage(){
		//页面参数
		String pageNumber = getParameter("pageNumber");
		String pageSize= getParameter("pageSize");

		//参数
		Map<String, Object> paramMap = new HashMap<String, Object>(); 
		paramMap.put("pageNumber", Integer.parseInt(pageNumber));
		paramMap.put("pageSize", Integer.parseInt(pageSize));

		//服务请求
		Map<String, Object> resultMap = service.findModuleByPage(paramMap);

		//数据返回
		getBadusResponse().setDataMap(resultMap);
		getBadusResponse().setSuccessed(true);

		return SUCCESS;
	}
 

}
