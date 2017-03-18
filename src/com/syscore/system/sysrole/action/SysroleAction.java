/*
 * 版　权: alaha Copyright 2013-2015,All rights reserved
 * 
 * 文件名: SysroleAction.java
 * 
 */
package com.syscore.system.sysrole.action;

import java.util.HashMap;
import java.util.Map;

import com.syscore.commons.action.BasAction;
import com.syscore.system.sysrole.model.SysroleInfo;
import com.syscore.system.sysrole.service.ISysroleService;

/**
 * @ClassName : SysroleAction
 * 
 * @Description : 角色_ACTION
 * 
 * @author tide
 * 
 * @date 2014-08-23
 * 
 * @version 1.0
 *
 */
public class SysroleAction extends BasAction<SysroleInfo, ISysroleService> {

	/**
 	*
 	*/
	private static final long serialVersionUID = 1L;

	/**
	 * 增加
	 * @return
	 */
	public String insertSysrole(){
		//页面参数
		String id = getParameter("id");

		//参数
		SysroleInfo info = new SysroleInfo();
		info.setId(id);

		//服务请求
		service.insertSysrole(info);

		//数据返回
		getBadusResponse().setSuccessed(true);
		return SUCCESS;
	}

	/**
	 *删除数据
	 * @return
	 */
	public String deleteSysrole(){ 
		//页面参数
		String id = getParameter("id"); 

		//参数
		SysroleInfo info = new SysroleInfo();
		info.setId(id);

		//服务请求
		service.deleteSysrole(info);

		//数据返回
		getBadusResponse().setSuccessed(true);
		return SUCCESS;
	}

	/**
	 * 修改数据
	 * @return
	 */
	public String updateSysrole(){ 
		//页面参数
		String id = getParameter("id");

		//参数
		SysroleInfo info = new SysroleInfo();
		info.setId(id);

		//服务请求
		service.updateSysrole(info);

		//数据返回
		getBadusResponse().setSuccessed(true);

		return SUCCESS;
	}
	
	/**
	 * 数据查询
	 * @return
	 */
	public String findSysroleByMap(){
		//页面参数
		String id = getParameter("id");

		//参数
		Map<String, Object> paramMap = new HashMap<String, Object>(); 
		paramMap.put("id", id);

		//服务请求
		Map<String, Object> resultMap = service.findSysroleByMap(paramMap);

		//数据返回
		getBadusResponse().setDataMap(resultMap);
		getBadusResponse().setSuccessed(true);

		return SUCCESS;
	}
 
	/**
	 * 单个查询
	 * @return
	 */
	public String findSysroleByEntity(){
		SysroleInfo vEntityInfo = new SysroleInfo();
		SysroleInfo info = service.findSysrole(vEntityInfo);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("data",info);
		getBadusResponse().setDataMap(resultMap);
		getBadusResponse().setSuccessed(true);
		return SUCCESS;
	}
	
	/**
	 * 获取所有模块并标志角色已经选中的模块
	 * @return
	 */
	public String findAllModulesAndMarkCheckedByRole(){
		//获取参数
		String id = getRequest().getParameter("id");
		SysroleInfo vEntityInfo = new SysroleInfo();
		vEntityInfo.setId(id);		
		Map<String,Object> resultMap = service.findAllModulesAndMarkCheckedByRole(vEntityInfo);
		
		getBadusResponse().setDataMap(resultMap);
		getBadusResponse().setSuccessed(true);
		return SUCCESS;
	}
	
	/**
	 * 设置角色分配模块
	 * @return
	 */	
	public String updateModulesToRole(){
		//request.getAttribute()方法返回request范围内存在的对象，
		//而request.getParameter()方法是获取http提交过来的数据
		String roleId = (String) getRequest().getParameter("roleId");
		String moduleIds = (String) getRequest().getParameter("moduleIds");
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("roleId", roleId);
		paramMap.put("moduleIds", moduleIds);
		service.updateModulesToRole(paramMap);
		
		getBadusResponse().setSuccessed(true);
		return SUCCESS;
	}

}
